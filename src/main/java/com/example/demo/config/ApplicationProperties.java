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

    private Oss oss;

    private Jwt jwt;

    private Rabbit rabbit;

    @Data
    public static class Jwt{
        /** Request Headers ： Authorization */
        private String header;

        /** 令牌前缀，最后留个空格 Bearer */
        private String tokenStartWith;

        /** Base64对该令牌进行编码 */
        private String base64Secret;

        /** 令牌过期时间 此处单位/毫秒 */
        private Long tokenValidityInSeconds;


    }

    @Data
    public static class Oss{

        private  String name;


    }

    @Data
    public static   class Rabbit{

        private String host;
        private Integer port;
        private String username;
        private String password;
        private String virtualHost;

    }

}
