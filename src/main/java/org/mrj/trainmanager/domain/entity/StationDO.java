package org.mrj.trainmanager.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @作者 s.1228
 * @日期 2021/3/25 16:42
 * @Version 1.0
 */


@SuperBuilder//继承父类属性
@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//无参构造
public class StationDO extends BaseDO{

    /**
     * 车站名称
     */
    private String stationName;

    /**
     * 车次
     */
    private String trainNumber;

    /**
     * 到达时间
     */
    private Date arrivalTime;

    /**
     * 车站名称
     */
    private Date departureTime;

    /**
     * 日期
     */
    private Date date;



}
