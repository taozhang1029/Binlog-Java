package com.kingsley.binlog.listener;

import com.alibaba.fastjson.JSON;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.kingsley.binlog.service.BinLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: zhangtao552
 * @time: 2021/8/11 21:02
 * @description BinLog 监听
 * 测试 Mysql binlog 监控
 * Mysql8 连接提示 Client does not support authentication protocol requested by server; consider upgrading MySQL client 解决方法
 * USE mysql;
 * ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'zhangtao552';
 * FLUSH PRIVILEGES;
 */
@Slf4j
@Component
public class BinLogListener {

    @Autowired
    private BinaryLogClient client;

    @Autowired
    private BinLogService binLogService;

    private String binlogFilename;

    private long binlogPosition;

    public String getBinlogFilename() {
        return binlogFilename;
    }

    public void setBinlogFilename(String binlogFilename) {
        this.binlogFilename = binlogFilename;
    }

    public long getBinlogPosition() {
        return binlogPosition;
    }

    public void setBinlogPosition(long binlogPosition) {
        this.binlogPosition = binlogPosition;
    }

    /**
     * --------Update-----------
     * UpdateRowsEventData{tableId=90, includedColumnsBeforeUpdate={0, 1, 2, 3, 4, 5, 6, 7},
     * includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
     * {before=[11, 10, Test Bin Log, 1, Tue Jun 25 08:00:00 CST 2019,Tue Jun 25 08:00:00 CST 2019, Tue Jun 25 08:00:00 CST 2019, Tue Jun 25 08:00:00 CST 2019],
     * after=[11, 10, zhangpan test Binlog, 1, Tue Jun 25 08:00:00 CST 2019, Tue Jun 25 08:00:00 CST 2019, Tue Jun 25 08:00:00 CST 2019, Tue Jun 25 08:00:00 CST 2019]
     * }
     * ]}
     * --------Insert-----------
     * WriteRowsEventData{tableId=91, includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
     * [10, 11, ad unit test binlog, 1, 0, 1236.7655, Thu Jun 27 08:00:00 CST 2019, Thu Jun 27 08:00:00 CST 2019]
     * ]}
     */
    public void listen() throws IOException {

        // 设置需要读取的Binlog的文件以及位置，默认会从"头"开始读取Binlog并监听
        client.setBinlogFilename(binlogFilename);
        client.setBinlogPosition(binlogPosition);

        //给客户端注册监听器，实现对Binlog的监听和解析
        //event 就是监听到的Binlog变化信息，event包含header & data 两部分
        try {
            client.registerEventListener(event -> {
                log.info(JSON.toJSONString(event.getHeader()));
                EventData data = event.getData();
                if (data instanceof UpdateRowsEventData) {
                    binLogService.onUpdate(data);
                } else if (data instanceof WriteRowsEventData) {
                    binLogService.onInsert(data);
                } else if (data instanceof DeleteRowsEventData) {
                    binLogService.onDelete(data);
                }
            });
            client.connect();
        } finally {
            client.disconnect();
        }
    }

}

