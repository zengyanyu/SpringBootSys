package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户信息
 *
 * @author zengyanyu
 * @since 2024-11-18
 */
@Getter
@Setter
@Entity
@TableName("user_info")
@ApiModel(value = "用户信息", description = "用户信息")
public class UserInfo extends BaseEntity {

    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("token")
    private String token;

}
