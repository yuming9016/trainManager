package org.mrj.trainmanager.mapper;

import org.mrj.trainmanager.domain.dto.TrainDTO;
import org.mrj.trainmanager.domain.entity.TrainDO;
import org.mrj.trainmanager.domain.param.TrainParam;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/5 12:04
 * @Version 1.0
 */

public interface TrainMapper {

    /**
     * 通过车次获取车次时刻
     * @param trainNumber 车次
     * @return 车次时刻
     */
    List<TrainDO> getTrainTimeByTrainNumber(String trainNumber, Integer pageNum, Integer pageSize);

    /**
     * 获取所有火车信息的总数
     * @return 总数
     */
    Long getTotal();

    /**
     * 根据车次时刻id获取车次时刻
     * @param id id
     * @return trainDO
     */
    TrainDO getTrainTimeById(Long id);


    /**
     * 新增车次时刻
     * @param trainDO 前端传来的train
     */
    void insertTrainTime(TrainDO trainDO);

    /**
     * 删除车次时刻
     * @param trainDO 前端传来的train
     */
    void deleteTrainTime(TrainDO trainDO);
    /**
     * 根据id删除车次时刻
     * @param id
     */
    void deleteTrainTimeById(Long id);

    /**
     * 修改车次时刻的信息
     * @param trainDO 前端传来的train
     */
    void updateTrainTime(TrainDO trainDO);

    /**
     * 根据车站名称获取车次时刻
     * @param stationName 车站名称
     * @return 车次时刻
     */
    List<TrainDO> getAllTrainTimeByStationName(String stationName);

    /**
     * 根据车站名称获取车次时刻
     * @param stationName 车站名称
     * @return 车次时刻
     */
    List<TrainDO> getTrainTimeByStationName(String stationName, Integer pageNum, Integer pageSize);

    /**
     * 根据出发站和到达站名称获取到达站的车次时刻
     * @param stationStartName 出发车站名称
     * @param stationEndName 到达车站名称
     * @return 车次
     */
    List<TrainDO> getTrainTimeEndBySTS(String stationStartName, String stationEndName, Integer pageNum, Integer pageSize);

    /**
     * 根据出发站和到达站名称获取出发站的车次时刻
     * @param stationStartName 出发车站名称
     * @param stationEndName 到达车站名称
     * @return 车次
     */
    List<TrainDO> getTrainTimeStartBySTS(String stationStartName, String stationEndName, Integer pageNum, Integer pageSize);


}
