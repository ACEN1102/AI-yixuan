package com.digitalchina.aiyixuan.model.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserDTO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
}
