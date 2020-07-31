package com.younghun.hibusgo.controller;

import com.younghun.hibusgo.aop.LoginCheck;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_CREATED;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;


import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.AccountDto;
import com.younghun.hibusgo.dto.PasswordDto;
import com.younghun.hibusgo.dto.ProfileDto;
import com.younghun.hibusgo.service.AccountService;
import com.younghun.hibusgo.service.LoginService;
import com.younghun.hibusgo.utils.SessionId;
import com.younghun.hibusgo.validator.AccountDtoValidator;
import com.younghun.hibusgo.validator.PasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final PasswordValidator passwordValidator;

    /**
     * - InitBinder는 특정 컨트롤러에서 바인딩 또는 검증 설정 변경에 사용
     *  accountDto로 객체에만 바인딩 또는 Validator 설정 적
     * @param webDataBinder requestBody에 있는 값을 특정 객체로 바인딩
     */
    @InitBinder("accountDto")
    public void accountInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountDtoValidator);
    }

    @InitBinder("passwordDto")
    public void passwordInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(passwordValidator);
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

        return RESPONSE_ENTITY_CREATED;
    }

    /**
     * 회원 탈퇴 메서드
     * @return 실제 회원 데이터는 삭제 하지않고 회원 상태를 DELETE로 변경시
     * 서버가 요청을 성공적으로 처리했지만 컨텐츠를 리턴하지 않음을 의미하는 204 code return
     *
     * 탈퇴시 로그인 사용자가 인증이 실패한다면 인증된 상태가 않음을 의미하는  401 code return
     */
    @LoginCheck
    @DeleteMapping("/myInfo")
    public ResponseEntity deleteAccount(@SessionId String loginId) {
        accountService.deleteAccount(loginId);
        loginService.accountLogout();

        return RESPONSE_ENTITY_NO_CONTENT;
    }

    /**
     * 회원 비밀번호 수정 메서드
     * @return 회원 비밀번 수정 성공시
     * 서버가 요청을 성공적으로 처리했지만 컨텐츠를 리턴하지 않음을 의미하는 204 code return
     * 객체 validation 후 error가 있으면 400(Bad Request) code return
     * 수정시 로그인한 사용자가 인증이 실패한다면 인증된 상태가 않음을 의미하는  401 code return
     *
     * @param passwordDto 수정할 회원의 비밀번호
     */
    @LoginCheck
    @PatchMapping("/password/{loginId}")
    public ResponseEntity updatePassword(@PathVariable String loginId, @RequestBody @Valid PasswordDto passwordDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        accountService.updatePassword(loginId, passwordDto.getNewPassword());

        return RESPONSE_ENTITY_NO_CONTENT;
    }

    /**
     * 회원 정보(이름, 이메일, 폰번호) 수정 메소드
     *
     * @param ProfileDto
     * @param errors
     * @return
     */
    @LoginCheck
    @PatchMapping("/myInfo")
    public ResponseEntity updateAccountInfo(@RequestBody @Valid ProfileDto ProfileDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        Account accountInfo = ProfileDto.toEntity();
        accountService.updateAccountInfo(accountInfo);

        return RESPONSE_ENTITY_NO_CONTENT;
    }

}
