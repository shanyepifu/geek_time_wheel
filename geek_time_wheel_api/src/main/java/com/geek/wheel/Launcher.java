package com.geek.wheel;

import com.geek.wheel.annotations.EnableTimeWheel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @DESCRIPTION: API 启动器
 * @CLASSNAME: Launcher
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/1/29 14:22
 */
@SpringBootApplication
@EnableSwagger2
@EnableTimeWheel
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}
