package com.digitalchina.aiyixuan.service.Impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.digitalchina.aiyixuan.model.DTO.CityInfo;
import com.digitalchina.aiyixuan.model.DTO.WeatherDTO;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WeatherService {

    public WeatherDTO getWeather(String city) {
        List<CityInfo> cityInfos = getCityInfo();
        CityInfo cityInfo = cityInfos.stream().filter(o -> city.equals(o.getCity_name())).findFirst().orElse(CityInfo.builder().city_name("武汉").city_code("101200101").build());
        HttpResponse execute = HttpUtil.createGet("http://t.weather.itboy.net/api/weather/city/" + cityInfo.getCity_code()).execute();
        WeatherDTO weatherDTO = new WeatherDTO();
        if (execute.isOk()) {
            JSONObject jsonObject = JSON.parseObject(execute.body(), JSONObject.class);
            JSONObject data = jsonObject.getObject("data", JSONObject.class);
            JSONArray forecasts = data.getJSONArray("forecast");
            JSONObject forecast = JSON.parseObject(forecasts.get(0).toString(), JSONObject.class);
            weatherDTO.setHigh(forecast.getString("high").substring(3));
            weatherDTO.setLow(forecast.getString("low").substring(3));
            weatherDTO.setFx(forecast.getString("fx"));
            weatherDTO.setFl(forecast.getString("fl"));
            weatherDTO.setType(forecast.getString("type"));
        }
        return weatherDTO;
    }

    public List<CityInfo> getCityInfo() {
        List<CityInfo> cityInfos = new ArrayList<>();
        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("config/city.json")).getPath().substring(1);
        String content = "[]";
        try {
            // 读取文件内容为字节数组
            byte[] bytes = Files.readAllBytes(Path.of(path));

            // 将字节数组转换为字符串
            content = new String(bytes, StandardCharsets.UTF_8);
            cityInfos = JSON.parseArray(content, CityInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityInfos;
    }

    public String getCityByIp(String ip) {

        if (StringUtils.isEmpty(ip)) {
            // 获取当前电脑的 IP 地址
            ip = HttpUtil.createGet("http://checkip.amazonaws.com/").execute().body();
        }

        // 使用 IP 地址定位服务获取当前位置
        String apiUrl = "http://ip-api.com/json/" + ip + "?lang=zh-CN";

        String body = HttpUtil.createGet(apiUrl).execute().body();
        System.out.println(body);


        JSONObject jsonObject = JSON.parseObject(body, JSONObject.class);
        return jsonObject.getString("city");//JSON
    }
    public String getCityNameByIp(String ip) {
        if (StringUtils.isEmpty(ip)) {
            // 获取当前电脑的 IP 地址
            ip = HttpUtil.createGet("http://checkip.amazonaws.com/").execute().body();
        }

        // 使用 IP 地址定位服务获取当前位置
        String apiUrl = "http://ip-api.com/json/" + ip + "?lang=zh-CN";

        String body = HttpUtil.createGet(apiUrl).execute().body();

        JSONObject jsonObject = JSON.parseObject(body, JSONObject.class);
        return jsonObject.getString("city");//直接返回城市名称
    }

}
