package org.mrj.trainmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"org.mrj.trainmanager.mapper"})//mybatis会根据这些接口名和.xml里的namespace来动态生成接口的实现类
public class TrainManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainManagerApplication.class, args);
    }

}
