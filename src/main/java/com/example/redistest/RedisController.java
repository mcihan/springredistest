package com.example.redistest;

import com.example.redistest.dto.RequestDto;
import com.example.redistest.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class RedisController {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    boolean initial = true;

    private final RedisService redisService;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @ResponseBody
    public String startTransaction(@RequestBody RequestDto dto) {

        if(initial){
            System.out.println("Redis host/port ----> " + host  + ":" + port + "\n\n");
            initial = false;
        }
        String value = dto.getValue();
        String hashCode = value.hashCode() + "";
        System.out.println("\nJedis test --> " + value + "  hashcode: " + hashCode + " --");

        redisService.save(value);
        System.out.println("saved: " + redisService.findById(value));

        redisService.delete(value);
        System.out.print("deleted\n\n");


        return "Redis test :" + value;
    }
}
