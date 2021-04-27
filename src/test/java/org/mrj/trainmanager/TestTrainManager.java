package org.mrj.trainmanager;

import org.junit.Test;
import org.mrj.trainmanager.domain.dto.TrainDTO;
import org.mrj.trainmanager.domain.dto.UserDTO;
import org.mrj.trainmanager.domain.entity.UserDO;
import org.mrj.trainmanager.domain.mapper.UserEntityMapper;
import org.mrj.trainmanager.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/2/23 23:13
 * @Version 1.0
 */

public class TestTrainManager {

    //用于测试
    @Test
    public void test1() {

    }

    @Test
    public void test2() {

        String str = "D2811";
        System.out.println("D2811" ==  str);
    }
}
