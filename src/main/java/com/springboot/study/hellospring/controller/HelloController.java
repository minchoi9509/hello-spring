package com.springboot.study.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // HTTP url의 Get 방식에 매칭
    // 정척 컨텐츠 방식
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("name", "value");

        // return을 hello로 지정해주면 - resources 폴더 하위에 있는 hello 파일을 찾아서 렌더링 함
        return "hello";
    }

    // MVC, tempalte 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = true) String name, Model model)
    {
        // hello-mvc?name=${name} 으로 param을 강제함
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API
    @GetMapping("hello-api-simple")
    @ResponseBody
    public String helloApiSimple(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api-real")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        // 객체 Return 시 Json 데이터로 반환
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
