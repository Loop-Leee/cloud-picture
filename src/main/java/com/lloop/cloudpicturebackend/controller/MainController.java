package com.lloop.cloudpicturebackend.controller;

import com.lloop.cloudpicturebackend.common.BaseResponse;
import com.lloop.cloudpicturebackend.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lloop
 * @Create 2025/3/15 15:45
 */
@RestController
@RequestMapping("/")
public class MainController {

    /**
     * 健康检查
     */
    @GetMapping("/health")
    public BaseResponse<String> health() {
        return ResultUtils.success("ok");
    }

}