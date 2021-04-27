package org.mrj.trainmanager.mapper;

import org.apache.ibatis.annotations.Param;
import org.mrj.trainmanager.domain.entity.TrainDO;
import org.mrj.trainmanager.domain.entity.TrainInfoDO;
import org.mrj.trainmanager.domain.entity.UserDO;
import org.mrj.trainmanager.domain.mapper.TrainEntityMapper;
import org.mrj.trainmanager.domain.param.TrainInfoParam;
import org.mrj.trainmanager.domain.param.UserParam;


import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/9 14:25
 * @Version 1.0
 */

public interface TrainInfoMapper {

    /**
     * 获取所有火车信息
     * @return 所有火车信息
     */
    List<TrainInfoDO> selectAllTrainInfo(String trainName, Integer pageNum, Integer pageSize);
    /**
     * 获取所有火车信息的总数
     * @return 总数
     */
    Long getTotal();

    /**
     * 根据id获取单个火车信息
     * @param id
     * @return trainInfoDO
     */
    TrainInfoDO getTrainInfoById(Long id);

    /**
     * 检查火车信息
     * @param trainInfoParam 用户内容
     */
    TrainInfoDO checkTrainInfo(TrainInfoParam trainInfoParam);

    /**
     * 新增火车信息
     * @param trainInfoDO 前端传来的trainInfo
     */
    void insertTrainInfo(TrainInfoDO trainInfoDO);

    /**
     * 根据id删除火车信息
     * @param id
     */
    void deleteTrainInfoById(Long id);

    /**
     * 修改火车信息
     * @param trainInfoDO 前端传来的trainInfo
     */
    void updateTrainInfo(TrainInfoDO trainInfoDO);


}
