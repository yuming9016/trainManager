package org.mrj.trainmanager.domain.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @作者 s.1228
 * @日期 2021/4/13 15:08
 * @Version 1.0
 */
@Data
public class UserRoleIdParam {

    @NotBlank
    private String id;

    @NotBlank
    private String roleId;

}
