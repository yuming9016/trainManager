package org.mrj.trainmanager.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @作者 s.1228
 * @日期 2021/4/5 13:01
 * @Version 1.0
 */

@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//所有的无参构造
public class TrainVO implements Serializable {

    /**
     * 车次id
     */
    private Long id;

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
     *  是否过期
     */
    private Integer outageOrNot;

}
