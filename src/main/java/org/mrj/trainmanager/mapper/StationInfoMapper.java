package org.mrj.trainmanager.mapper;

import org.mrj.trainmanager.domain.entity.StationInfoDO;
import org.mrj.trainmanager.domain.entity.TrainInfoDO;
import org.mrj.trainmanager.domain.param.StationInfoParam;
import org.mrj.trainmanager.domain.param.TrainInfoParam;


import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/10 19:20
 * @Version 1.0
 */

public interface StationInfoMapper {

    /**
     * 获取所有车站信息
     * @return 所有车站信息
     */
    List<StationInfoDO> selectAllStationInfo(String stationName, Integer pageNum, Integer pageSize);
    /**
     * 获取所有车站信息的总数
     * @return 总数
     */
    Long getTotal();

    /**
     * 根据id获取单个车站信息
     * @param id
     * @return stationInfoDO
     */
    StationInfoDO getStationInfoById(Long id);

    /**
     * 检查车站信息
     * @param stationInfoParam 车站内容
     */
    StationInfoDO checkStationInfo(StationInfoParam stationInfoParam);

    /**
     * 新增车站信息
     * @param stationInfoDO 前端传来的trainInfo
     */
    void insertStationInfo(StationInfoDO stationInfoDO);

    /**
     * 根据id删除车站信息
     * @param id
     */
    void deleteStationInfoById(Long id);

    /**
     * 修改车站信息
     * @param stationInfoDO 前端传来的trainInfo
     */
    void updateStationInfo(StationInfoDO stationInfoDO);

}
