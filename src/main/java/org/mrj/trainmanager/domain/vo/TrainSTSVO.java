package org.mrj.trainmanager.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @作者 s.1228
 * @日期 2021/4/11 21:54
 * @Version 1.0
 */

@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//所有的无参构造
public class TrainSTSVO implements Serializable {

    /**
     * 车次
     */
    private String trainNumber;

    /**
     *  出发车站
     */
    private String stationName1;

    /**
     * 到站时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp arrivalTime1;

    /**
     *  到达车站
     */
    private String stationName2;

    /**
     * 到站时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp arrivalTime2;

}
