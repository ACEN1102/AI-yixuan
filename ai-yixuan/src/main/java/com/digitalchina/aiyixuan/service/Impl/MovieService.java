package com.digitalchina.aiyixuan.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.Random;


@Service
public class MovieService {
    public String getMovie(){
        StringBuilder movies = new StringBuilder();
        Random random = new Random();
        int randomNumber = random.nextInt(5);

        String url;
        if (randomNumber == 0) {
            url = "https://api.wmdb.tv/api/v1/top?type=Douban&skip=0&limit=50&lang=Cn";
        } else if (randomNumber == 1) {
            url = "https://api.wmdb.tv/api/v1/top?type=Douban&skip=50&limit=50&lang=Cn";
        } else if (randomNumber == 2) {
            url = "https://api.wmdb.tv/api/v1/top?type=Douban&skip=100&limit=50&lang=Cn";
        } else if (randomNumber == 3) {
            url = "https://api.wmdb.tv/api/v1/top?type=Douban&skip=150&limit=50&lang=Cn";
        }else {
            url = "https://api.wmdb.tv/api/v1/top?type=Douban&skip=200&limit=50&lang=Cn";
        }

        try (HttpResponse response = HttpUtil.createGet(url).execute()) {
            if (response.isOk()) {
                JSONArray jsonArray = JSON.parseArray(response.body());
                jsonArray.stream()
                        .map(obj -> ((JSONObject) obj).getJSONArray("data"))
                        .filter(data -> !data.isEmpty())
                        .map(data -> data.getJSONObject(0).getString("name"))
                        .forEach(name -> movies.append("'").append(name).append("'"));
            }
        }

        return movies.toString();
    }
}