package org.mrj.trainmanager.service;

import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.dto.TrainDTO;
import org.mrj.trainmanager.domain.dto.TrainInfoDTO;
import org.mrj.trainmanager.domain.dto.UserDTO;
import org.mrj.trainmanager.domain.param.TrainInfoParam;
import org.mrj.trainmanager.domain.param.UserParam;


import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/9 14:27
 * @Version 1.0
 */

public interface TrainInfoService {

    /**
     * 根据火车名称获得所有火车信息
     * @return TrainInfo
     */
    PageDAO getAllTrainInfo(String trainName, Integer pageNum, Integer pageSize);

    /**
     * 根据id获得火车信息
     */
    TrainInfoDTO getTrainInfo(Long id);

    /**
     * 检查火车名称是否存在
     * @param trainInfoParam 新增内容
     */
    TrainInfoDTO checkTrainInfo(TrainInfoParam trainInfoParam);

    /**
     * 新增火车信息
     * @param trainInfoDTO 新增火车信息
     */
    void addTrainInfo(TrainInfoDTO trainInfoDTO);

    /**
     * 根据id删除火车信息
     * @param id
     */
    void deleteTrainInfo(Long id);

    /**
     * 修改火车信息
     * @param trainInfoDTO 修改火车信息
     */
    void updateTrainInfo(TrainInfoDTO trainInfoDTO);

}
