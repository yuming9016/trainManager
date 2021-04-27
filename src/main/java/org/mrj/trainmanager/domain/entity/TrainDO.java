package org.mrj.trainmanager.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @作者 s.1228
 * @日期 2021/3/19 22:29
 * @Version 1.0
 */

@SuperBuilder//继承父类属性
@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//无参构造
public class TrainDO extends BaseDO{


    /**
     * 车次
     */
    private String trainNumber;

    /**
     *  车站名称
     */
    private String stationName;

    /**
     * 到站时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp arrivalTime;

    /**
     * 出站时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp departureTime;

    /**
     *  日期
     */

    private Date date;

    /**
     *  是否是发车站
     */
    private Integer isOriginalStation;

    /**
     *  是否是中间站
     */
    private Integer isMiddleStation;

    /**
     *  是否是终点站
     */
    private Integer isEndStation;

    /**
     *  是否过期
     */
    private Integer outageOrNot;





}
