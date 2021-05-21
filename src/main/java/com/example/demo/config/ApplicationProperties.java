package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/21 23:07
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
@Data
public class ApplicationProperties {

    private OSS oss;

    @Data
    public class OSS{

        private  String name;


    }

}
