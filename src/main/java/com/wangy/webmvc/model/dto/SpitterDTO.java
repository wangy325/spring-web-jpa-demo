package com.wangy.webmvc.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wangy
 * @version 1.0
 * @date 2021/1/5 / 23:39
 */
@Data
@ApiModel("用户实体类")
public class SpitterDTO {

    @ApiModelProperty(value = "用户id", name = "id", hidden = true)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 20, message = "${firstName.size}")
    @ApiModelProperty(value = "用户firstName", required = true)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20, message = "{lastName.size}")
    @ApiModelProperty(value = "用户lastName", required = true)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 16, message = "{userName.size}")
    @ApiModelProperty(value = "用户名", name = "username", required = true)
    private String username;

    @NotNull
    @Size(min = 4, max = 30, message = "{password.size}")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
