package com.fast.cloud.react.controller;

import com.fast.cloud.react.domain.EsMember;
import com.fast.cloud.react.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-05-06 17:10
 */
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/member/list",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<EsMember> list() {
        Flux<EsMember> all = memberService.findAll().delayElements(Duration.ofSeconds(2));
        return all;
    }
}
