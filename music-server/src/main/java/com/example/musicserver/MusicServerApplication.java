package com.example.musicserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *热更新热加载
 * 设置-》高级设置-》auto make勾选
 * 2.快捷键ctrl+f9可以热更新
 */
@SpringBootApplication
@MapperScan("com.example.musicserver.mapper")
public class MusicServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicServerApplication.class, args);
    }

}
