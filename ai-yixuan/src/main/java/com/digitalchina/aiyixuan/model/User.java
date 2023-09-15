package com.digitalchina.aiyixuan.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /** 用户昵称 */
    @TableField("user_name")
    //@JsonProperty("姓名")
    private String userName;

    /** 性别 */
    @TableField("gender")
    //@JsonProperty("性别")
    private String gender;//1男 2女

    /** 生日 */
    @TableField("birthday")
    //@JsonProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /** 爱好 */
    @TableField("hobby")
    //@JsonProperty("爱好")
    private String hobby;

    /** 幸运色 */
    @TableField("lucky_color")
    //@JsonProperty("幸运色")
    private String luckyColor;

    /** 位置 */
    @TableField("location")
    //@JsonProperty("位置")
    private String location;

    /** 收入 */
    @TableField("income")
    //@JsonProperty("收入")
    private String income;

    /** 职业 */
    @TableField("occupation")
    //@JsonProperty("职业")
    private String occupation;

    /** 喜欢的食物 */
    @TableField("favor_food")
    //@JsonProperty("喜欢的食物")
    private String favorFood;

    /** 年龄 */
    @TableField("age")
    //@JsonProperty("年龄")
    private String age;

    /** 体重 */
    @TableField("weight")
    //@JsonProperty("体重")
    private String weight;

    /** 身高 */
    @TableField("height")
    //@JsonProperty("身高")
    private String height;

}
