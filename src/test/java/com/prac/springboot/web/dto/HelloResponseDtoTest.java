package com.prac.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_test() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);          //assertj라는 테스트 검증 라이브러리의 검증 메소드, 검증하고 싶은 대상을 인자로 받음
        assertThat(dto.getAmount()).isEqualTo(amount);      //비교해서 같을때만 성공
    }
}
