package org.mrj.trainmanager.controller;

import org.mrj.trainmanager.domain.PageDAO;
import org.mrj.trainmanager.domain.dto.StationInfoDTO;
import org.mrj.trainmanager.domain.dto.TrainDTO;
import org.mrj.trainmanager.domain.dto.TrainInfoDTO;
import org.mrj.trainmanager.domain.mapper.StationInfoEntityMapper;
import org.mrj.trainmanager.domain.mapper.TrainEntityMapper;
import org.mrj.trainmanager.domain.mapper.TrainInfoEntityMapper;
import org.mrj.trainmanager.domain.param.StationInfoParam;
import org.mrj.trainmanager.domain.param.TrainInfoParam;
import org.mrj.trainmanager.domain.vo.StationInfoVO;
import org.mrj.trainmanager.domain.vo.TrainVO;
import org.mrj.trainmanager.response.Response;
import org.mrj.trainmanager.service.StationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/10 19:36
 * @Version 1.0
 */

@RestController
@RequestMapping(value = "/trainmanager/station")
public class StationController {

    @Autowired
    private StationInfoService stationInfoService;

    //查询所有车站信息
    @RequestMapping(value = "/selectAllStationInfoByPage",method = RequestMethod.POST)
    @ResponseBody
    public Response selectAllStationInfoByPage(@RequestBody @Valid PageDAO pageDAO) {

        return Response.success(stationInfoService.getAllStationInfo(pageDAO.getStationName(),pageDAO.getPageNum(),pageDAO.getPageSize()));

    }

    //查询单个车次时刻
    @RequestMapping(value = "/selectStationInfoById",method = RequestMethod.GET)
    @ResponseBody
    public Response selectStationInfoById(String id) {

        StationInfoDTO stationInfoDTO = stationInfoService.getStationInfo(Long.parseLong(id));

        StationInfoVO stationInfoVO = StationInfoEntityMapper.INSTANCE.stationInfoDto2Vo(stationInfoDTO);

        return Response.success(stationInfoVO);

    }

    //判断车站名称是否重复
    @RequestMapping(value = "/checkStationName",method = RequestMethod.POST)
    @ResponseBody
    public Response checkStationName(@RequestBody @Valid StationInfoParam stationInfoParam) {
        stationInfoService.checkStationInfo(stationInfoParam);
        return Response.success(true);
    }

    //增加车站信息
    @RequestMapping(value = "/addStationInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response addStationInfo(@RequestBody @Valid StationInfoParam stationInfoParam) {

        StationInfoDTO stationInfoDTO = StationInfoEntityMapper.INSTANCE.stationInfoParam2Dto(stationInfoParam);//param转DTO

        stationInfoService.addStationInfo(stationInfoDTO);//调用新增函数

        return Response.success(stationInfoDTO);
    }

    //删除车站信息
    @RequestMapping(value = "/removeStationInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response removeStationInfo(Long id) {

        stationInfoService.deleteStationInfo(id);

        return Response.success(true);

    }

    //修改车站信息
    @RequestMapping(value = "/updateStationInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response updateStationInfo(@RequestBody @Valid StationInfoParam stationInfoParam) {

        StationInfoDTO stationInfoDTO = StationInfoEntityMapper.INSTANCE.stationInfoParam2Dto(stationInfoParam);

        stationInfoService.updateStationInfo(stationInfoDTO);

        return Response.success(true);

    }

}
