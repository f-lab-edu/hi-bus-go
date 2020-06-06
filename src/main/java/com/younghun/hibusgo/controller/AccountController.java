package com.younghun.hibusgo.controller;

import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.AccountDto;
import com.younghun.hibusgo.service.AccountService;
import com.younghun.hibusgo.validator.AccountDtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class AccountController {

    private final AccountService accountService;
    private final AccountDtoValidator accountDtoValidator;

    /**
     * - InitBinder는 특정 컨트롤러에서 바인딩 또는 검증 설정 변경에 사용
     *  accountDto로 객체에만 바인딩 또는 Validator 설정 적
     * @param webDataBinder requestBody에 있는 값을 특정 객체로 바인딩
     */
    @InitBinder("accountDto")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountDtoValidator);
    }

    /**
     * - 유저 회원 가입 메서드.
     * 객체 validation 후 error가 있으면 400(Bad Request) code return
     * insert 성공시 성공시 201(Created) code return
     *
     * @param accountDto 저장할 회원의 정보
     */
    @PostMapping("/add")
    public ResponseEntity<AccountDto> addAccount(@RequestBody @Valid AccountDto accountDto, Errors errors) {
        if (errors.hasErrors()) {
            new ResponseEntity<>(accountDto, HttpStatus.BAD_REQUEST);
        }

        Account account = accountDto.toEntity();

        accountService.addAccount(account);

        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }

}
