package org.mrj.trainmanager.service.impl;

import org.mrj.trainmanager.common.enums.ErrorCodeEnums;
import org.mrj.trainmanager.common.exception.TrainException;
import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.dto.StationInfoDTO;
import org.mrj.trainmanager.domain.dto.TrainInfoDTO;
import org.mrj.trainmanager.domain.entity.StationInfoDO;
import org.mrj.trainmanager.domain.entity.TrainInfoDO;
import org.mrj.trainmanager.domain.mapper.StationInfoEntityMapper;
import org.mrj.trainmanager.domain.mapper.TrainInfoEntityMapper;
import org.mrj.trainmanager.domain.param.StationInfoParam;
import org.mrj.trainmanager.mapper.StationInfoMapper;
import org.mrj.trainmanager.service.StationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/10 19:33
 * @Version 1.0
 */

@Service
public class StationInfoServiceImpl implements StationInfoService {

    @Autowired
    private StationInfoMapper stationInfoMapper;


    //获得所有车站信息
    @Override
    public PageDAO getAllStationInfo(String stationName, Integer pageNum, Integer pageSize) {

        if(pageNum!=null&&pageSize!=null){
            pageNum=(pageNum-1)*pageSize;
        }

        List<StationInfoDO> stationInfoDOList = stationInfoMapper.selectAllStationInfo(stationName, pageNum,pageSize);

        Long total = stationInfoMapper.getTotal();

        if (stationInfoDOList.isEmpty()) {//如果没查到对应车站信息，则抛出异常
            throw new TrainException(ErrorCodeEnums.STATION_NAME_NOT_EXIST);
        }
        PageDAO pageDAO = new PageDAO();
        pageDAO.setData(stationInfoDOList);
        pageDAO.setTotal(total);

        return pageDAO;

    }

    //根据id获取车站信息
    @Override
    public StationInfoDTO getStationInfo(Long id) {

        StationInfoDO stationInfoDO = stationInfoMapper.getStationInfoById(id);

        return StationInfoEntityMapper.INSTANCE.stationInfoDo2Dto(stationInfoDO);
    }

    //检测车站名称是否重复
    @Override
    public StationInfoDTO checkStationInfo(StationInfoParam stationInfoParam) {
        StationInfoDO stationInfoDO = stationInfoMapper.checkStationInfo(stationInfoParam);

        if (stationInfoDO != null){
            //如果查到对应，则抛出异常
            throw new TrainException(ErrorCodeEnums.STATION_NAME_EXIST);//在异常类新增字段
        }
        return null;
    }

    //新增车站信息
    @Override
    public void addStationInfo(StationInfoDTO stationInfoDTO) {
        stationInfoMapper.insertStationInfo(StationInfoEntityMapper.INSTANCE.stationInfoDto2Do(stationInfoDTO));
    }

    //删除车站信息
    @Override
    public void deleteStationInfo(Long id) {
        stationInfoMapper.deleteStationInfoById(id);
    }

    //修改车站信息
    @Override
    public void updateStationInfo(StationInfoDTO stationInfoDTO) {
        stationInfoMapper.updateStationInfo(StationInfoEntityMapper.INSTANCE.stationInfoDto2Do(stationInfoDTO));
    }
}
