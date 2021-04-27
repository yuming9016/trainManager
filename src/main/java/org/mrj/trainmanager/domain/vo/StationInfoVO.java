package org.mrj.trainmanager.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @作者 s.1228
 * @日期 2021/4/10 19:25
 * @Version 1.0
 */

@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//所有的无参构造
public class StationInfoVO implements Serializable {

    /**
     * id
     */
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
