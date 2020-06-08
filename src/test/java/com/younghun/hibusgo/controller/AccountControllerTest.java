package com.younghun.hibusgo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.AccountDto;
import com.younghun.hibusgo.dto.LoginDto;
import com.younghun.hibusgo.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
 * @SpringBootTest - 통합테스트 환경을 제공하는 spring-boot-test 어노테이션. 실제 구동되는 어플리케이션과 동일한 어플리케이션 컨텍스트를 제공함.
 * 대신 어플리케이션의 설정을 모두 로드하기 때문에 성능, 속도면에서 느리다. 제공되는 의존성 : JUnit, Spring Test, Spring Boot Test, AssertJ,
 *
 * @AutoConfigureMockMvc - Mock 테스트시 필요한 의존성을 제공. Service에서 호출하는 Bean을 주입해준다. 간단히 컨트롤러 클래스만 테스트 하고
 * 싶다면 Mockup Test를 사용할 수 있는데 service 객체에 @MockBean 어노테이션을 적용하는 것으로 이 어노테이션을 대체할 수 있다.
 *
 */

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("회원 가입 처리 - 입력값 오류")
    @Test
    void addAccount_with_wrong_input() throws Exception {
        AccountDto accountDto = AccountDto.builder()
                .id("abcd1234")
                .password("1")
                .name("younghun")
                .email("whdudgns2654@naver.com")
                .phoneNumber("010-1234-5678")
                .build();

        Account account = accountDto.toEntity();

        String json = objectMapper.writeValueAsString(account);

        mockMvc.perform(post("/accounts/signUp")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원 가입 처리 - 입력값 정상")
    @Test
    void addAccount_with_correct_input() throws Exception {
        AccountDto accountDto = AccountDto.builder()
                .id("abcd123455")
                .password("dudgns1234!")
                .name("younghun")
                .email("whdudgns2654@naver.com")
                .phoneNumber("010-1234-5678")
                .build();

        Account account = accountDto.toEntity();
        account.setStatus(Account.Status.DEFAULT);

        String json = objectMapper.writeValueAsString(account);

        mockMvc.perform(post("/accounts/signUp")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}