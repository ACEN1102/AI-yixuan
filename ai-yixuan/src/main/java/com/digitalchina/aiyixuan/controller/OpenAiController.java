package com.digitalchina.aiyixuan.controller;


import com.digitalchina.aiyixuan.model.Answer;
import com.digitalchina.aiyixuan.model.ApiResponse;
import com.digitalchina.aiyixuan.model.DTO.UserDTO;
import com.digitalchina.aiyixuan.model.DTO.WeatherDTO;
import com.digitalchina.aiyixuan.model.User;
import com.digitalchina.aiyixuan.service.AnswerService;
import com.digitalchina.aiyixuan.service.Impl.MovieService;
import com.digitalchina.aiyixuan.service.Impl.OpenAiService;
import com.digitalchina.aiyixuan.service.Impl.WeatherService;
import com.digitalchina.aiyixuan.service.UserService;
import com.unfbx.chatgpt.entity.chat.ChatChoice;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/chatGpt")
public class OpenAiController {
    @Resource
    private OpenAiService openAiService;
    @Resource
    private WeatherService weatherService;

    @Resource
    private UserService userService;

    @Resource
    private AnswerService answerService;

    @Resource
    private MovieService movieService;

    @PostMapping("/chat")
    public List<ChatChoice> chatCompletion(@RequestBody String content) {
        Message message = Message.builder().role(Message.Role.USER).content(content).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(List.of(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiService.performChatCompletion(chatCompletion);
        return chatCompletionResponse.getChoices();

        }
    @PostMapping("/lunch")
    public ApiResponse chatLunch(@RequestBody UserDTO userDTO) {
        User user = userService.getById(userDTO.getId());
        String mergedContent = user.toString() + "分析以上信息,以荤素搭配，两菜一汤，饮品解腻，组成一串流畅的话来向我推荐一日三餐的不同内容";
        Message message = Message.builder().role(Message.Role.USER).content(mergedContent).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(List.of(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiService.performChatCompletion(chatCompletion);
        String content = chatCompletionResponse.getChoices()
                .stream()
                .findFirst()
                .map(choice -> choice.getMessage().getContent())
                .orElse(null);
        return new ApiResponse(content);
    }
    @PostMapping("/horoscope")
    public ApiResponse chatHoroscope(@RequestBody UserDTO userDTO){
        Date birthday=userService.getById(userDTO.getId()).getBirthday();
        String mergedContent =  "这是我的出生日期：" +birthday+"结合今天的日期，请根据星座预测我今日运势";
        Message message = Message.builder().role(Message.Role.USER).content(mergedContent).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(List.of(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiService.performChatCompletion(chatCompletion);
        String content = chatCompletionResponse.getChoices()
                .stream()
                .findFirst()
                .map(choice -> choice.getMessage().getContent())
                .orElse(null);
        return new ApiResponse(content);
    }

    @PostMapping("/clothes")
    public ApiResponse clothes(@RequestParam(required = false) String ip, @RequestBody UserDTO userDTO) {
        String city1 = weatherService.getCityNameByIp(ip);
        String user = userService.getById(userDTO.getId()).toString();
        WeatherDTO weather = weatherService.getWeather(weatherService.getCityByIp(ip));
        String mergedContent = "我是一位选择困难症患者(自我调侃)，这是我的个人信息：" + user + "，我目前居住在（" + city1 + "），" + "现在的天气是：" + weather.toString() + "请给出我今天出行的穿衣建议：";
        Message message = Message.builder().role(Message.Role.USER).content(mergedContent).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(List.of(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiService.performChatCompletion(chatCompletion);
        String content = chatCompletionResponse.getChoices()
                .stream()
                .findFirst()
                .map(choice -> choice.getMessage().getContent())
                .orElse(null);
        return new ApiResponse(content);
    }

    @PostMapping("/movie")
    public ApiResponse movie(@RequestBody UserDTO userDTO) {
        String user = userService.getById(userDTO.getId()).toString();
        String mergedContent = "我是一位选择困难症患者(自我调侃)，请根据我的个人信息：" + user + "，从以下所有的电影中随机(random)推荐一部我可能喜欢的电影给我：" + movieService.getMovie();
        System.out.println(movieService.getMovie());
        Message message = Message.builder().role(Message.Role.USER).content(mergedContent).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(List.of(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiService.performChatCompletion(chatCompletion);
        String content = chatCompletionResponse.getChoices()
                .stream()
                .findFirst()
                .map(choice -> choice.getMessage().getContent())
                .orElse(null);
        return new ApiResponse(content);
    }


    @PostMapping("/answerBook")
    public ApiResponse answerBook(@RequestBody String content) {
        Answer answer= answerService.selectRandomAnswerOption();
        String mergedContent ="我有一个问题:" +content+ "，这是答案之书的回答:" +answer+ "，请结合上述问题和答案，解释一下这个答案对于我是什么意思?" ;
        Message message = Message.builder().role(Message.Role.USER).content(mergedContent).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(List.of(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiService.performChatCompletion(chatCompletion);
        String content1 = chatCompletionResponse.getChoices()
                .stream()
                .findFirst()
                .map(choice -> choice.getMessage().getContent())
                .orElse(null);
        return new ApiResponse("答案之书说："+answer.getAnswerOption()+"\n"+content1);
    }
}