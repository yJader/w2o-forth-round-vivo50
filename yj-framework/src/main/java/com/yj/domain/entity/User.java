package com.yj.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2023-02-18 21:28:48
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
@ApiModel("用户表")
public class User  {
    /**
    * 用户id
    */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("用户id")
    private Long id;
    /**
    * 用户名
    */    
    @ApiModelProperty("用户名")
    private String userName;
    /**
    * 昵称
    */    
    @ApiModelProperty("昵称")
    private String nickName;
    /**
    * 密码
    */    
    @ApiModelProperty("密码")
    private String password;
    /**
    * 账号状态（0正常 1停用）
    */    
    @ApiModelProperty("账号状态（0正常 1停用）")
    private String status;
    /**
    * 邮箱
    */    
    @ApiModelProperty("邮箱")
    private String email;
    /**
    * 手机号
    */    
    @ApiModelProperty("手机号")
    private String phonenumber;
    /**
    * 用户性别（0男，1女，2未知）
    */    
    @ApiModelProperty("用户性别（0男，1女，2未知）")
    private String sex;
    /**
    * 头像
    */    
    @ApiModelProperty("头像")
    private String avatar;
    /**
    * 用户积分
    */
    @ApiModelProperty("用户积分")
    private Integer points;
    /**
    * 用户累积积分
    */
    @ApiModelProperty("用户累积积分")
    private Integer cumulativePoints;
    /**
    * 用户最后一次签到的日期
    */
    @ApiModelProperty("用户最后一次签到的日期")
    private Date lastSignInDate;
    /**
    * 删除标志（0代表未删除，1代表已删除）
    */    
    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;

    public User(Long id, Integer points) {
        this.id = id;
        this.points = points;
    }

    public User(Long id, Date lastSignInDate) {
        this.id = id;
        this.lastSignInDate = lastSignInDate;
    }
}

