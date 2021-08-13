package com.kingsley.binlog.service.impl;

import com.github.shyiko.mysql.binlog.event.EventData;
import com.kingsley.binlog.service.BinLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhangtao552
 * @time: 2021/8/13 11:34
 * @description
 */
@Slf4j
@Service
public class BinLogServiceImpl implements BinLogService {

    @Override
    public void onInsert(EventData data) {
        log.info("--------Insert-----------: {}", data);
    }

    @Override
    public void onUpdate(EventData data) {
        log.info("--------Update-----------: {}", data);
    }

    @Override
    public void onDelete(EventData data) {
        log.info("--------Delete-----------: {}", data);
    }
}
