package org.mrj.trainmanager.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @作者 s.1228
 * @日期 2021/3/25 16:47
 * @Version 1.0
 */

@SuperBuilder//继承父类属性
@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//无参构造
public class StationInfoDO {

    private Long id;

    /**
     * 车站名称
     */
    private String stationName;

    /**
     * 值班人员
     */
    private String peopleOnDuty;

    /**
     * 服务电话
     */
    private String serviceTelephone;



}
