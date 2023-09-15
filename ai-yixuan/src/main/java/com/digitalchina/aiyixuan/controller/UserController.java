package com.digitalchina.aiyixuan.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.digitalchina.aiyixuan.model.Result;
import com.digitalchina.aiyixuan.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.digitalchina.aiyixuan.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("save")
    public ResponseEntity<Result<String>> save(@RequestBody User user) {
        userService.save(user);
        String userId = user.getId();
        Result<String> result = new Result<>(200, "新增用户成功", "id", userId);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("update")
    public ResponseEntity<Result<String>>  update(@RequestBody User user) {
        userService.updateById(user);
        String userId = user.getId();
        Result<String> result = new Result<>(200, "更新用户成功",  "id", userId);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("saveOrUpdate")
    public ResponseEntity<Result<String>>  saveOrUpdate(User user){
        userService.saveOrUpdate(user);
        String userId = user.getId();
        Result<String> result = new Result<>(200, "用户信息操作成功", "id", userId);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("delete")
    public ResponseEntity<Result<String>> delete(@RequestBody String id){
        userService.removeById(id);
        Result<String> result = new Result<>(200, "用户删除成功","id",id);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("list")
    public List<User> list(String id){
        return userService.list();
    }

    @RequestMapping("listPage")
    public Page<User> listPage(Integer pageNo,Integer pageSize,User user) {
        Page<User> page = new Page<>(pageNo, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(user.getUserName())) {
            queryWrapper.like("name", user.getUserName());
        }
        return userService.page(page,queryWrapper);
    }}