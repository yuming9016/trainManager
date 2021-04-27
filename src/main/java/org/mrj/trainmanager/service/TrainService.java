package org.mrj.trainmanager.service;

import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.dto.TrainDTO;
import org.mrj.trainmanager.domain.entity.TrainDO;
import org.mrj.trainmanager.domain.param.TrainParam;

import java.util.List;


/**
 * @作者 s.1228
 * @日期 2021/4/5 12:12
 * @Version 1.0
 */


public interface TrainService {

    /**
     * 根据车次查询车次时刻
     * @param trainNumber 车次
     * @return 车次时刻
     */
    PageDAO getTrainTime(String trainNumber, Integer pageNum, Integer pageSize);

    /**
     * 根据id查询车次时刻
     * @param id
     * @return trainDTO
     */
    TrainDTO getTrainTimeById(Long id);

    /**
     * 新增车次时刻
     * @param trainDTO 新增车次时刻
     */
    void addTrainTime(TrainDTO trainDTO);

    /**
     * 删除车次时刻
     * @param trainDTO 删除车次时刻
     */
    void removeTrainTime(TrainDTO trainDTO);
    /**
     * 根据id删除车次时刻
     * @param id
     */
    void removeTrainTimeById(Long id);

    /**
     * 修改车次时刻的信息
     * @param trainDTO 修改车次时刻的信息
     */
    void updateTrainTime(TrainDTO trainDTO);

    /**
     * 根据车站名称查询所有车次时刻
     * @param stationName 车站名称
     * @return 车次时刻
     */
    List<TrainDTO> getAllTrainTimeByStationName(String stationName);

    /**
     * 根据车站名称查询车次时刻
     * @param stationName 车站名称
     * @return 车次时刻
     */
    PageDAO getTrainTimeByStationName(String stationName, Integer pageNum, Integer pageSize);

    /**
     * 根据出发车站和到达车站名称查询车次时刻
     * @param stationStartName 出发车站名称
     * @param stationEndName 到达车站名称
     * @return 车次时刻
     */
    PageDAO getTrainTimeBySTS(String stationStartName, String stationEndName, Integer pageNum, Integer pageSize);


}
