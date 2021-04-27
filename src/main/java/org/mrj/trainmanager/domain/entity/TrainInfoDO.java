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
 * @日期 2021/3/25 16:28
 * @Version 1.0
 */

@SuperBuilder//继承父类属性
@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//无参构造
public class TrainInfoDO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 火车名称
     */
    private String trainName;

    /**
     * 车厢数
     */
    private long railwayCarriageNumber;

    /**
     * 座位数
     */
    private long seatNumber;

    /**
     * 出厂时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp factoryTime;

    /**
     * 状态
     */
    private String status;

}
