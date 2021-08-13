package com.kingsley.binlog;

import com.kingsley.binlog.listener.BinLogListener;
import com.kingsley.binlog.service.BinLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author: zhangtao552
 * @time: 2021/8/13 12:48
 * @description
 */
@SpringBootTest(classes = BinLogApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ContextTest {

    @Autowired
    private BinLogListener binLogListener;

    @Test
    public void testBinLogListener(){
        try {
            binLogListener.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
