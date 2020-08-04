package com.younghun.hibusgo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.AccountDto;
import com.younghun.hibusgo.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
 * @SpringBootTest - 통합테스트 환경을 제공하는 spring-boot-test 어노테이션. 실제 구동되는 어플리케이션과 동일한 어플리케이션 컨텍스트를 제공함.
 * 대신 어플리케이션의 설정을 모두 로드하기 때문에 성능, 속도면에서 느리다. 제공되는 의존성 : JUnit, Spring Test, Spring Boot Test, AssertJ,
 *
 * @AutoConfigureMockMvc - Mock 테스트 시 필요한 의존성을 제공. Service에서 호출하는 Bean을 주입해준다. 간단히 컨트롤러 클래스만 테스트 하고
 * 싶다면 Mockup Test를 사용할 수 있는데 service 객체에 @MockBean 어노테이션을 적용 하는 것으로 이 어노테이션을 대체할 수 있다.
 *
 *  단위 테스트 : 개발 단계에서 각 모듈(기능)이나 어플케이션에 있는 개별적인 코드 단위가 예상대로 작동하는지 수행하는 테스트
 *  각각 함수를 한 가지의 일만 수행 하도록 작성 해야 한다.
 *
 *  단위 테스트 작성 방법
 *  1. 버그를 찾는다.
 *  2. 버그를 고쳤을 때 통과 할 만한 테스트 작성
 *  3. 테스트를 통과할 때 까지 코드를 수정
 *
 *  현재 변화하는 코드에 대해 테스트 만 작성, 가능한 모든 시나리오의 테스트를 작성하지 않는다.
 *
 *  통합 테스트 : 단위 테스트가 끝난 후 각 모듈(기능)을 통합 하는 과정에서 모듈간 호환성 문제가 없는지 수행하는 테스트
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
//        account.setStatus(Account.Status.DEFAULT);

        String json = objectMapper.writeValueAsString(account);

        mockMvc.perform(post("/accounts/signUp")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("회원 삭제 처리 - 입력값 정상")
    @Test
    void deleteAccount_with_correct_input() throws Exception {
        String id = "abcd1234";

        mockMvc.perform(delete("/accounts/" + id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isNoContent());
    }

}