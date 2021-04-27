package org.mrj.trainmanager.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @作者 s.1228
 * @日期 2021/4/14 10:50
 * @Version 1.0
 */

@Data
public class PageDAO {

    /**
     * 名字
     */
    private String name;

    /**
     * 车次
     */
    private String trainNumber;

    /**
     * 火车名称
     */
    private String trainName;

    /**
     * 出发站名称
     */
    private String stationStartName;

    /**
     * 到达站名称
     */
    private String stationEndName;

    /**
     * 车站名称
     */
    private String stationName;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 尺寸
     */
    private  Integer pageSize;

    /**
     * 数据列表
     */
    private List<?> data;

    /**
     * 总数
     */
    private  Long total;

}
