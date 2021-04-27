package org.mrj.trainmanager.domain.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @作者 s.1228
 * @日期 2021/4/13 13:34
 * @Version 1.0
 */
@Data
public class UpdateStatusParam {

    @NotBlank
    private String status;



}
