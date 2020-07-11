package com.younghun.hibusgo.controller;

import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.AccountDto;
import com.younghun.hibusgo.service.AccountService;
import com.younghun.hibusgo.service.LoginService;
import com.younghun.hibusgo.validator.AccountDtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class AccountController {

    private final AccountService accountService;
    private final LoginService loginService;
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
     * 회원 가입 메서드.
     * 객체 validation 후 error가 있으면 400(Bad Request) code return
     * insert 성공시 성공시 201(Created) code return
     *
     * @param accountDto 저장할 회원의 정보
     */
    @PostMapping("/signUp")
    public ResponseEntity addAccount(@RequestBody @Valid AccountDto accountDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        Account account = accountDto.toEntity();

        accountService.addAccount(account);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 회원 탈퇴 메서드
     * @return 실제 회원 데이터는 삭제 하지않고 회원 상태를 DELETE로 변경시
     * 서버가 요청을 성공적으로 처리했지만 컨텐츠를 리턴하지 않음을 의미하는 204 code return
     *
     * 탈퇴사 로그인한 사용자가 인증이 실패한다면 인증된 상태가 않음을 의미하는  401 code return
     */
    @DeleteMapping("/myInfo")
    public ResponseEntity deleteAccount() {
        String sessionId = loginService.getLoginAccountId();

        if (sessionId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        accountService.deleteAccount(sessionId);

        loginService.accountLogout();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
