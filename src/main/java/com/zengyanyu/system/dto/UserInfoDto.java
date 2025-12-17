package com.zengyanyu.system.dto;

import com.zengyanyu.system.entity.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zengyanyu
 */
@Getter
@Setter
@ApiModel("用户信息dto对象")
public class UserInfoDto extends UserInfo {

    @ApiModelProperty("权限集合")
    private List<PermissionDto> permissions;

    @ApiModelProperty("账号类型（1：用户名和密码、2：手机号和验证码）")
    private String accountType;

    @ApiModelProperty("厂商名称")
    private String companyName;

    @ApiModelProperty("验证码")
    private String verificationCode;

    @ApiModelProperty("角色")
    private List<String> roles;

    @ApiModelProperty("用户认证状态")
    private String approveStatus;

}
