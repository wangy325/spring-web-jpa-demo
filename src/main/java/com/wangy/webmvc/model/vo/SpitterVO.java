package com.wangy.webmvc.model.vo;

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
@ApiModel("用户实体")
public class SpitterVO {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户firstName")
    private String firstName;

    @ApiModelProperty(value = "用户lastName")
    private String lastName;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;
}
