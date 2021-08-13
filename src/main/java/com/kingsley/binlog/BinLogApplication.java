package com.kingsley.binlog;

import com.kingsley.binlog.utils.ConnectionPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zhangtao552
 * @time: 2021/8/13 11:30
 * @description
 */
@SpringBootApplication(scanBasePackages = "com.kingsley.binlog")
public class BinLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BinLogApplication.class, args);
    }
}
