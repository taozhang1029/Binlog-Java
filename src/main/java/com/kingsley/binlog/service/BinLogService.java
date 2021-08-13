package com.kingsley.binlog.service;

import com.github.shyiko.mysql.binlog.event.EventData;

/**
 * @author: zhangtao552
 * @time: 2021/8/13 11:22
 * @description
 */
public interface BinLogService {

    void onInsert(EventData data);

    void onUpdate(EventData data);

    void onDelete(EventData data);

}
