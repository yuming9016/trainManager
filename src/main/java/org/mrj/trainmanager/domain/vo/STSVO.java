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
 * @日期 2021/4/17 9:22
 * @Version 1.0
 */

@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//所有的无参构造
public class STSVO implements Serializable {

    /**
     * 车次
     */
    private String trainNumber;

    /**
     *  出发站名称
     */
    private String stationStartName;

    /**
     *  到达站名称
     */
    private String stationEndName;

    /**
     * 出发站到站时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp arrivalStartTime;

    /**
     * 到达站到站时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp arrivalEndTime;

}
