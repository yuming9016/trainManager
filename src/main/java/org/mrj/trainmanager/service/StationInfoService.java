package org.mrj.trainmanager.service;

import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.dto.StationInfoDTO;
import org.mrj.trainmanager.domain.dto.TrainInfoDTO;
import org.mrj.trainmanager.domain.param.StationInfoParam;
import org.mrj.trainmanager.domain.param.TrainInfoParam;


import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/10 19:31
 * @Version 1.0
 */

public interface StationInfoService {

    /**
     * 根据车站名称获得所有车站信息
     * @return StationInfo
     */
    PageDAO getAllStationInfo(String stationName, Integer pageNum, Integer pageSize);

    /**
     * 根据id获得车站信息
     */
    StationInfoDTO getStationInfo(Long id);

    /**
     * 检查车站名称是否存在
     * @param stationInfoParam 新增内容
     */
    StationInfoDTO checkStationInfo(StationInfoParam stationInfoParam);

    /**
     * 新增车站信息
     * @param stationInfoDTO 新增火车信息
     */
    void addStationInfo(StationInfoDTO stationInfoDTO);

    /**
     * 根据id删除车站信息
     * @param id
     */
    void deleteStationInfo(Long id);

    /**
     * 修改车站信息
     * @param stationInfoDTO 修改火车信息
     */
    void updateStationInfo(StationInfoDTO stationInfoDTO);

}
