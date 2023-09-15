package com.digitalchina.aiyixuan.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @ClassName WeatherDTO
 * @Description 天气
 * @Author nieyifan
 * @Date 2023/9/5 23:05
 */
@Data
public class WeatherDTO {
    /**
     * 最高温度
     */
    @JsonProperty("最高温度")
    private String high;
    /**
     * 最低温度
     */
    @JsonProperty("最低温度")
    private String low;
    /**
     * 风向
     */
    @JsonProperty("风向")
    private String fx;
    /**
     * 风级
     */
    @JsonProperty("风级")
    private String fl;
    /**
     * 天气
     */
    @JsonProperty("天气")
    private String type;
}
