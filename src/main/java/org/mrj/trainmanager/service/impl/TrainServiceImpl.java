package org.mrj.trainmanager.service.impl;

import org.mrj.trainmanager.common.enums.ErrorCodeEnums;
import org.mrj.trainmanager.common.exception.TrainException;
import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.dto.TrainDTO;
import org.mrj.trainmanager.domain.entity.TrainDO;
import org.mrj.trainmanager.domain.mapper.TrainEntityMapper;
import org.mrj.trainmanager.domain.mapper.TrainInfoEntityMapper;
import org.mrj.trainmanager.domain.param.TrainParam;
import org.mrj.trainmanager.domain.vo.STSVO;
import org.mrj.trainmanager.mapper.TrainMapper;
import org.mrj.trainmanager.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/5 12:18
 * @Version 1.0
 */

@Service
public class TrainServiceImpl implements TrainService {

    //注入mapper
    @Autowired
    private TrainMapper trainMapper;

    //通过车次查找车次时刻
    @Override
    public PageDAO getTrainTime(String trainNumber, Integer pageNum, Integer pageSize) {


        if(pageNum!=null&&pageSize!=null){
            pageNum=(pageNum-1)*pageSize;
        }
        List<TrainDO> trainDO = trainMapper.getTrainTimeByTrainNumber(trainNumber, pageNum, pageSize);

        Long total = trainMapper.getTotal();//获得车次时刻总数

        if (trainDO.isEmpty()) {//如果没查到对应车次时刻，则抛出异常
            throw new TrainException(ErrorCodeEnums.TRAIN_NUMBER_NOT_EXIST);
        }

        PageDAO pageDAO = new PageDAO();
        pageDAO.setData(trainDO);
        pageDAO.setTotal(total);

        //查到了DO转DTO
        return pageDAO;
    }

    @Override
    public TrainDTO getTrainTimeById(Long id) {

        TrainDO trainDO = trainMapper.getTrainTimeById(id);

        return TrainEntityMapper.INSTANCE.trainDo2Dto(trainDO);
    }

    //新增车次时刻
    @Override
    public void addTrainTime(TrainDTO trainDTO) {

        trainMapper.insertTrainTime(TrainEntityMapper.INSTANCE.trainDto2Do(trainDTO));//DTO转DO，并加入

    }

    //删除车次时刻
    @Override
    public void removeTrainTime(TrainDTO trainDTO) {

        trainMapper.deleteTrainTime(TrainEntityMapper.INSTANCE.trainDto2Do(trainDTO));

    }
    //根据id删除车次时刻
    @Override
    public void removeTrainTimeById(Long id) {
        trainMapper.deleteTrainTimeById(id);
    }

    //修改车次时刻
    @Override
    public void updateTrainTime(TrainDTO trainDTO) {

        trainMapper.updateTrainTime(TrainEntityMapper.INSTANCE.trainDto2Do(trainDTO));

    }

    //根据车站名称获取所有车次时刻
    @Override
    public List<TrainDTO> getAllTrainTimeByStationName(String stationName) {

        List<TrainDO> trainDOList = trainMapper.getAllTrainTimeByStationName(stationName);

        if (trainDOList.isEmpty()) {
            throw new TrainException(ErrorCodeEnums.TRAIN_NUMBER_NOT_EXIST);
        }

        return TrainEntityMapper.INSTANCE.trainDoList2DtoList(trainDOList);
    }

    //根据车站名称获取车次时刻
    @Override
    public PageDAO getTrainTimeByStationName(String stationName, Integer pageNum, Integer pageSize) {

        if(pageNum!=null&&pageSize!=null){
            pageNum=(pageNum-1)*pageSize;
        }

        System.out.println("pageNum = " + pageNum);
        List<TrainDO> trainDOList = trainMapper.getTrainTimeByStationName(stationName, pageNum, pageSize);

        Long total = trainMapper.getTotal();//获得车次时刻总数

        if (trainDOList.isEmpty()) {//如果没查到对应车次时刻，则抛出异常
            throw new TrainException(ErrorCodeEnums.TRAIN_NUMBER_NOT_EXIST);
        }

        PageDAO pageDAO = new PageDAO();
        pageDAO.setData(trainDOList);
        pageDAO.setTotal(total);

        return pageDAO;
    }

    //根据出发车站和到达车站名称获取车次时刻
    @Override
    public PageDAO getTrainTimeBySTS(String stationStartName, String stationEndName,  Integer pageNum, Integer pageSize) {

        if(pageNum!=null&&pageSize!=null){
            pageNum=(pageNum-1)*pageSize;
        }

        //获得符合条件的出发站车次时刻
        List<TrainDO> trainDOListStart = trainMapper.getTrainTimeStartBySTS(stationStartName, stationEndName, pageNum, pageSize);

        //获得符合条件的到达站车次时刻
        List<TrainDO> trainDOListSEnd = trainMapper.getTrainTimeEndBySTS(stationStartName, stationEndName, pageNum, pageSize);

        //将两张表的信息打包到stsvo
        List<STSVO> stsvoList = new ArrayList<>();
        for (int i=0 ; i<trainDOListStart.size() ; i++) {

            STSVO stsvo = new STSVO();

            //将车次传入
            stsvo.setTrainNumber(trainDOListStart.get(i).getTrainNumber());
            //将出发站传入
            stsvo.setStationStartName(trainDOListStart.get(i).getStationName());
            //将出发站的到站时间传入
            stsvo.setArrivalStartTime(trainDOListStart.get(i).getArrivalTime());

            //将到达站传入
            stsvo.setStationEndName(trainDOListSEnd.get(i).getStationName());
            //将到达站的到站时间传入
            stsvo.setArrivalEndTime(trainDOListSEnd.get(i).getArrivalTime());

            //打包进List
            stsvoList.add(stsvo);
        }

        if (stsvoList.isEmpty()) {//如果没查到对应车次，则抛出异常
            throw new TrainException(ErrorCodeEnums.TRAIN_NUMBER_NOT_EXIST);
        }

        Long total = trainMapper.getTotal();//获得车次时刻总数

        PageDAO pageDAO = new PageDAO();
        pageDAO.setData(stsvoList);
        pageDAO.setTotal(total);

        return pageDAO;
    }


}
