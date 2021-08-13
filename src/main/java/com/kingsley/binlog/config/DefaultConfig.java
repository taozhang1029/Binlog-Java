package com.kingsley.binlog.config;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: zhangtao552
 * @time: 2021/8/13 9:43
 * @description 默认配置
 */
@Configuration
@PropertySource(value = "classpath:dbconfig.properties")
public class DefaultConfig {
    /**
     * @author: zhangtao552
     * @time: 2021/8/13 11:18
     * @description
     */
    @Value("${jdbc.hostname}")
    private String hostname;

    @Value("${jdbc.port}")
    private int port;

    @Value("${jdbc.schema}")
    private String schema;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 构造BinaryLogClient，填充mysql链接信息
     */
    @Bean
    public BinaryLogClient getBinaryLogClient() {
        return new BinaryLogClient(hostname, port, schema, username, password);
    }
}
