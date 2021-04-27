package org.mrj.trainmanager.controller;

import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.dto.TrainDTO;
import org.mrj.trainmanager.domain.dto.TrainInfoDTO;
import org.mrj.trainmanager.domain.mapper.TrainEntityMapper;
import org.mrj.trainmanager.domain.mapper.TrainInfoEntityMapper;
import org.mrj.trainmanager.domain.param.TrainInfoParam;
import org.mrj.trainmanager.domain.param.TrainParam;
import org.mrj.trainmanager.domain.param.UserParam;
import org.mrj.trainmanager.domain.vo.TrainInfoVO;
import org.mrj.trainmanager.domain.vo.TrainSTSVO;
import org.mrj.trainmanager.domain.vo.TrainVO;
import org.mrj.trainmanager.response.Response;
import org.mrj.trainmanager.service.TrainInfoService;
import org.mrj.trainmanager.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/5 12:28
 * @Version 1.0
 */

@RestController
@RequestMapping(value = "/trainmanager/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private TrainInfoService trainInfoService;

    //查询所有车次时刻
    @RequestMapping(value = "/selectAllTrainByPage",method = RequestMethod.POST)
    @ResponseBody
    public Response selectAllTrainByPage(@RequestBody @Valid PageDAO pageDAO) {

        return Response.success(trainService.getTrainTime(pageDAO.getTrainNumber(),pageDAO.getPageNum(),pageDAO.getPageSize()));
    }

    //增加车次时刻
    @RequestMapping(value = "/addTrainTime",method = RequestMethod.POST)
    @ResponseBody
    public Response addTrainTime(@RequestBody @Valid TrainParam trainParam) {

        TrainDTO trainDTO = TrainEntityMapper.INSTANCE.trainParam2Dto(trainParam);//param转DTO

        trainService.addTrainTime(trainDTO);//调用新增函数

        return Response.success(trainDTO);
    }

    //查询单个车次时刻
    @RequestMapping(value = "/selectTrainById",method = RequestMethod.GET)
    @ResponseBody
    public Response selectTrainById(String id) {

        TrainDTO trainDTO = trainService.getTrainTimeById(Long.parseLong(id));

        TrainVO trainVO = TrainEntityMapper.INSTANCE.trainDto2Vo(trainDTO);

        return Response.success(trainVO);

    }

    //删除车次时刻
    @RequestMapping(value = "/removeTrainTimeById",method = RequestMethod.POST)
    @ResponseBody
    public Response removeTrainTimeById(Long id) {

        trainService.removeTrainTimeById(id);

        return Response.success(true);

    }

    //修改车次时刻
    @RequestMapping(value = "/updateTrainTime",method = RequestMethod.POST)
    @ResponseBody
    public Response updateTrainTime(@RequestBody @Valid TrainParam trainParam) {

        TrainDTO trainDTO = TrainEntityMapper.INSTANCE.trainParam2Dto(trainParam);

        trainService.updateTrainTime(trainDTO);

        return Response.success(true);

    }

    //查询所有火车信息
    @RequestMapping(value = "/selectAllTrainInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response selectAllTrainInfo(@RequestBody @Valid PageDAO pageDAO) {

        return Response.success(trainInfoService.getAllTrainInfo(pageDAO.getTrainName(),pageDAO.getPageNum(),pageDAO.getPageSize()));

    }

    //查询单个火车信息
    @RequestMapping(value = "/selectTrainInfoById",method = RequestMethod.GET)
    @ResponseBody
    public Response selectTrainInfoById(String id) {

        TrainInfoDTO trainInfoDTO = trainInfoService.getTrainInfo(Long.parseLong(id));

        TrainInfoVO trainInfoVO = TrainInfoEntityMapper.INSTANCE.trainInfoDto2Vo(trainInfoDTO);

        return Response.success(trainInfoVO);

    }

    //增加火车信息
    @RequestMapping(value = "/addTrainInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response addTrainInfo(@RequestBody @Valid TrainInfoParam trainInfoParam) {

        TrainInfoDTO trainInfoDTO = TrainInfoEntityMapper.INSTANCE.trainInfoParam2Dto(trainInfoParam);//param转DTO

        trainInfoService.addTrainInfo(trainInfoDTO);//调用新增函数

        return Response.success(trainInfoDTO);
    }

    //判断火车名称是否重复
    @RequestMapping(value = "/checkTrainName",method = RequestMethod.POST)
    @ResponseBody
    public Response checkTrainName(@RequestBody @Valid TrainInfoParam trainInfoParam) {
        trainInfoService.checkTrainInfo(trainInfoParam);
        return Response.success(true);
    }

    //删除火车信息
    @RequestMapping(value = "/removeTrainInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response removeTrainInfo(Long id) {

        trainInfoService.deleteTrainInfo(id);

        return Response.success(true);

    }

    //修改火车信息
    @RequestMapping(value = "/updateTrainInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response updateTrainInfo(@RequestBody @Valid TrainInfoParam trainInfoParam) {

        TrainInfoDTO trainInfoDTO = TrainInfoEntityMapper.INSTANCE.trainInfoParam2Dto(trainInfoParam);

        trainInfoService.updateTrainInfo(trainInfoDTO);

        return Response.success(true);

    }


    //根据车站名称查询车次时刻
    @RequestMapping(value = "/selectTrainTimeByStationName",method = RequestMethod.POST)
    @ResponseBody
    public Response selectTrainTimeByStationName(@RequestBody @Valid PageDAO pageDAO) {

        return Response.success(trainService.getTrainTimeByStationName(pageDAO.getStationName(),pageDAO.getPageNum(),pageDAO.getPageSize()));
    }

    //根据出发车站和到达车站名称查询车次时刻
    @RequestMapping(value = "/selectTrainTimeBySTS",method = RequestMethod.POST)
    @ResponseBody
    public Response selectTrainTimeBySTS(@RequestBody @Valid PageDAO pageDAO) {

        String stationStartName = pageDAO.getStationStartName();
        String stationEndName = pageDAO.getStationEndName();



        //前端分页的操作
        /*List<TrainSTSVO> trainSTSVOList = new ArrayList<>();//创建传到前端的临时对象

        //到达 出发站 的火车时刻
        List<TrainDTO> trainDTOList1 =  trainService.getAllTrainTimeByStationName(stationName1);
        //到达 到达站 的火车时刻
        List<TrainDTO> trainDTOList2 = trainService.getAllTrainTimeByStationName(stationName2);



        for (int i=0 ; i<trainDTOList1.size() ; i++) {

            TrainSTSVO trainSTSVO = new TrainSTSVO();

            for (int y=0 ; y<trainDTOList2.size() ; y++) {
                if (trainDTOList1.get(i).getTrainNumber().equals(trainDTOList2.get(y).getTrainNumber())) {
                    //如果 出发车站的车次 与 到达车站的车次 一致

                    //把出发车站的车次赋给临时trainSTSVO
                    trainSTSVO.setTrainNumber(trainDTOList1.get(i).getTrainNumber());

                    //把出发车站的名称赋给临时trainSTSVO
                    trainSTSVO.setStationName1(trainDTOList1.get(i).getStationName());

                    //把出发车站的车次到达时间赋给临时trainSTSVO
                    trainSTSVO.setArrivalTime1(trainDTOList1.get(i).getArrivalTime());

                    //把到达车站的名称名称赋给临时trainSTSVO
                    trainSTSVO.setStationName2(trainDTOList2.get(y).getStationName());

                    //把到达车站的车次到达时间赋给临时trainSTSVO
                    trainSTSVO.setArrivalTime2(trainDTOList2.get(y).getArrivalTime());
                }

            }
            if (trainSTSVO.getTrainNumber() != null) {//判空
                trainSTSVOList.add(trainSTSVO);
            }


        }*/

        return Response.success(trainService.getTrainTimeBySTS(stationStartName, stationEndName, pageDAO.getPageNum(),pageDAO.getPageSize() ));
    }
}
