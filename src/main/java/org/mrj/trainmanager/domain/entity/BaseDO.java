package org.mrj.trainmanager.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @作者 s.1228
 * @日期 2021/3/20 18:54
 * @Version 1.0
 */

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseDO {

    protected Long id;

    protected Date gmtCreate;

    protected Date gmtModified;

}
