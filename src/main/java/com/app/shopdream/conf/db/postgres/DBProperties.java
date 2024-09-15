package com.app.shopdream.conf.db.postgres;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "db")
@Configuration
public class DBProperties {
    private String url;
    private String username;
    private String password;
    private String driver;
}
