package com.digitalchina.aiyixuan.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("answer")
public class Answer {
    @TableId
    private String id;

    @TableField("answer_option")
    private String answerOption;
}
