package com.digitalchina.aiyixuan.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.digitalchina.aiyixuan.model.Answer;

public interface AnswerService extends IService<Answer> {
    default Answer selectRandomAnswerOption() {
        QueryWrapper<Answer> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("answer_option").orderByAsc("rand()").last("LIMIT 1");
        return getOne(queryWrapper, false);
    }
}
