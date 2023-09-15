package com.digitalchina.aiyixuan.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CityInfo
 * @Description 城市编码
 * @Author nieyifan
 * @Date 2023/9/5 23:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityInfo {
    private int id;
    private int pid;
    private String city_code;
    private String city_name;
    private String post_code;
    private String area_code;
    private String ctime;
}
