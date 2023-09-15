package com.digitalchina.aiyixuan.service.Impl;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import com.unfbx.chatgpt.interceptor.OpenAiResponseInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Service
public class OpenAiService {
    public ChatCompletionResponse performChatCompletion(ChatCompletion chatCompletion) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        // 设置日志级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new OpenAiResponseInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        // 构建客户端
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey(List.of(""))//在这里添加你的chatGptKEY
                .keyStrategy(new KeyRandomStrategy())
                .okHttpClient(okHttpClient)
                .build();

        return openAiClient.chatCompletion(chatCompletion);
    }
}