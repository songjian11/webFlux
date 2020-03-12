package com.songj.demo.controller;

import com.songj.demo.model.User;
import com.songj.demo.model.UserValidator;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/demo1")
public class Demo01Controller {
    @GetMapping("/test01")
    public Mono<Object> test01(){
        return Mono.create(monoSink -> {
            System.out.println("创建 Mono");
            monoSink.success("hello webflux");
        }).doOnSubscribe(subscription -> { //当订阅者去订阅发布者的时候，该方法会调用
            System.out.println("===========" + subscription);
         }).doOnNext(o -> { //当订阅者收到数据时，改方法会调用
            System.out.println("-----------" + o);
         });
    }

    @GetMapping("flux")
    public Flux<String> flux() {
        return Flux.just("hello","webflux","spring","boot");
    }

    @GetMapping("getUser/{user}")
    public Mono<String> getUser(@PathVariable("user") User use){
        return Mono.just("is ok");
    }

    /**
     * 全局校验
     * @param user
     * @return
     */
    @PostMapping("/userValidator1")
    public Mono<String> userValidator1(@Validated @RequestBody User user){
        System.out.println(user.toString());
        return Mono.just(user.toString());
    }

    /**
     * 局部校验，只对当前类有效
     * @param binder
     */
    @InitBinder
    public void initBinder(DataBinder binder){
        binder.setValidator(new UserValidator());
    }
}
