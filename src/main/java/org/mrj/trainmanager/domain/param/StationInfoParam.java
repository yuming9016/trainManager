package org.mrj.trainmanager.domain.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @作者 s.1228
 * @日期 2021/4/10 19:24
 * @Version 1.0
 */

@Data//get、set方法
@AllArgsConstructor//所有的有参构造
@NoArgsConstructor//无参构造
public class StationInfoParam {

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
