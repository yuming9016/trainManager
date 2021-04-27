package org.mrj.trainmanager.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mrj.trainmanager.domain.dto.StationInfoDTO;
import org.mrj.trainmanager.domain.entity.StationInfoDO;
import org.mrj.trainmanager.domain.param.StationInfoParam;
import org.mrj.trainmanager.domain.vo.StationInfoVO;
import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/10 19:26
 * @Version 1.0
 */


@Mapper
public interface StationInfoEntityMapper {

    //    Mappers.getMapper方法会帮我们自动构造TrainEntityMapperImpl实现类
//    这样一来，我们就可以通过TrainDO.INSTANCE.trainDo2Dto(trainDO)来使我们的DO向DTO转化了
    StationInfoEntityMapper INSTANCE = Mappers.getMapper(StationInfoEntityMapper.class);

    StationInfoDTO stationInfoDo2Dto(StationInfoDO stationInfoDO);

    StationInfoDO stationInfoDto2Do(StationInfoDTO stationInfoDTO);

    StationInfoDTO stationInfoParam2Dto(StationInfoParam stationInfoParam);

    List<StationInfoDTO> stationInfoDoList2DtoList(List<StationInfoDO> stationInfoDOList);

    StationInfoVO stationInfoDto2Vo(StationInfoDTO stationInfoDTO);

    List<StationInfoVO> stationInfoDtoList2VoList(List<StationInfoDTO> stationInfoDTOList);

}
