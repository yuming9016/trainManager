package org.mrj.trainmanager.domain.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @作者 s.1228
 * @日期 2021/4/13 12:44
 * @Version 1.0
 */
@Data
public class UpdatePasswordParam {


    @NotBlank
    private String newPassword;

    @NotBlank
    private String password;


}
