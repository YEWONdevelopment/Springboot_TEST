package com.prac.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)    //스프링부트 테스트와 JUnit 사이 연결자 역할
@WebMvcTest     //String 테스트 어노테이션 중, Web에 집중하라 수 있음, 컨트롤러만 사용 가능한 어노테이션
public class HelloControllerTest {

    @Autowired  //스프링이 관리하는 빈을 주입
    private MockMvc mvc;    //웹 API를 테스트할 때 사용, MVC테스트의 시작점

    @Test
    public void hello_return() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto_return() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name",name)         //API테스트할 때 사용될 요청 파라미터 설정
                        .param("amount",String.valueOf(amount)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name",is(name)))     //jsonpath는 JSON응답값을 필드별로 검증할 수 있는 메소드
                        .andExpect(jsonPath("$.amount",is(amount)));
    }
}
