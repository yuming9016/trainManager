package org.mrj.trainmanager.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mrj.trainmanager.domain.dto.TrainDTO;
import org.mrj.trainmanager.domain.entity.TrainDO;
import org.mrj.trainmanager.domain.param.TrainParam;
import org.mrj.trainmanager.domain.vo.TrainVO;

import java.util.List;


/**
 * @作者 s.1228
 * @日期 2021/4/5 12:23
 * @Version 1.0
 */

@Mapper
public interface TrainEntityMapper {

    //    Mappers.getMapper方法会帮我们自动构造TrainEntityMapperImpl实现类
//    这样一来，我们就可以通过TrainDO.INSTANCE.trainDo2Dto(trainDO)来使我们的DO向DTO转化了
    TrainEntityMapper INSTANCE = Mappers.getMapper(TrainEntityMapper.class);

    TrainDTO trainDo2Dto(TrainDO trainDO);

    TrainDO trainDto2Do(TrainDTO trainDTO);

    TrainDTO trainParam2Dto(TrainParam trainParam);

    List<TrainDTO> trainDoList2DtoList(List<TrainDO> trainDOList);

    TrainVO trainDto2Vo(TrainDTO trainDTO);

    List<TrainVO> trainDtoList2VoList(List<TrainDTO> trainDTOList);


}
