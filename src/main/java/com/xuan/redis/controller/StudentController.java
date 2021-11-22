package com.xuan.redis.controller;

import com.xuan.redis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private RedisTemplate redisTemplate;//redistemplate实例spring data rediis提供的，自动创建
    @PostMapping("/set")
    public void set(@RequestBody Student student){
        redisTemplate.opsForValue().set("student",student);
//        redisTemplate.opsForValue()返回一个key-value对象
//        然后set传入key和value
    }

    @GetMapping("/get/{key}")
    public Student get(@PathVariable("key") String key){
        return (Student) redisTemplate.opsForValue().get(key);
    }

    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable("key") String key){
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }
}
