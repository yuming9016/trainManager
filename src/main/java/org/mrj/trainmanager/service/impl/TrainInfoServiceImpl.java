package org.mrj.trainmanager.service.impl;

import org.mrj.trainmanager.common.enums.ErrorCodeEnums;
import org.mrj.trainmanager.common.exception.TrainException;
import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.dto.TrainInfoDTO;
import org.mrj.trainmanager.domain.entity.TrainInfoDO;
import org.mrj.trainmanager.domain.entity.UserDO;
import org.mrj.trainmanager.domain.mapper.TrainInfoEntityMapper;
import org.mrj.trainmanager.domain.param.TrainInfoParam;
import org.mrj.trainmanager.mapper.TrainInfoMapper;
import org.mrj.trainmanager.service.TrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/9 14:38
 * @Version 1.0
 */

@Service
public class TrainInfoServiceImpl implements TrainInfoService {

    @Autowired
    private TrainInfoMapper trainInfoMapper;

    //获得所有火车信息
    @Override
    public PageDAO getAllTrainInfo(String trainName, Integer pageNum, Integer pageSize) {

        if(pageNum!=null&&pageSize!=null){
            pageNum=(pageNum-1)*pageSize;
        }

        List<TrainInfoDO> trainInfoDOList = trainInfoMapper.selectAllTrainInfo(trainName, pageNum, pageSize);

        Long total = trainInfoMapper.getTotal();

        if (trainInfoDOList.isEmpty()) {//如果没查到对应火车信息，则抛出异常
            throw new TrainException(ErrorCodeEnums.TRAIN_NAME_NOT_EXIST);
        }
        PageDAO pageDAO = new PageDAO();
        pageDAO.setData(trainInfoDOList);
        pageDAO.setTotal(total);

        return pageDAO;
    }

    //获取单个火车信息
    @Override
    public TrainInfoDTO getTrainInfo(Long id) {

        TrainInfoDO trainInfoDO = trainInfoMapper.getTrainInfoById(id);

        return TrainInfoEntityMapper.INSTANCE.trainInfoDo2Dto(trainInfoDO);
    }

    //检测火车名称是否存在
    @Override
    public TrainInfoDTO checkTrainInfo(TrainInfoParam trainInfoParam) {
        TrainInfoDO trainInfoDO = trainInfoMapper.checkTrainInfo(trainInfoParam);

        if (trainInfoDO != null){
            //如果查到对应user，则抛出异常
            throw new TrainException(ErrorCodeEnums.TRAIN_NAME_EXIST);//在异常类新增字段
        }
        return null;
    }

    //新增火车信息
    @Override
    public void addTrainInfo(TrainInfoDTO trainInfoDTO) {
        trainInfoMapper.insertTrainInfo(TrainInfoEntityMapper.INSTANCE.trainInfoDto2Do(trainInfoDTO));
    }

    //删除火车信息
    @Override
    public void deleteTrainInfo(Long id) {
        trainInfoMapper.deleteTrainInfoById(id);
    }

    //修改火车信息
    @Override
    public void updateTrainInfo(TrainInfoDTO trainInfoDTO) {
        trainInfoMapper.updateTrainInfo(TrainInfoEntityMapper.INSTANCE.trainInfoDto2Do(trainInfoDTO));
    }
}
