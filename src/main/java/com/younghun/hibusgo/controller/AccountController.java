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
     * accountDto객체에 바인딩된 값을 검증
     * @author 조영훈
     * InitBinder는 특정 컨트롤러에서 바인딩 또는 검증 설정 변경에 사용한다.
     * @param webDataBinder requestBody에 있는 값을 특정 객체로 바인딩
     */
    @InitBinder("accountDto")
    public void accountInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountDtoValidator);
    }

    /**
     * passwordDto객체에 바인딩 된 값을 검증
     * @author 조영훈
     * @param webDataBinder requestBody에 있는 값을 특정 객체로 바인딩
     */
    @InitBinder("passwordDto")
    public void passwordInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(passwordValidator);
    }

    /**
     * 회원 가입 메소드
     * @author 조영훈
     * 회원 가입 성공시 201(Created) code return.
     * 객체 valitaion 실패시 에러 정보와 400(Bad Request) code return.
     *
     * @param accountDto 저장할 회원의 정보
     * @param errors 객체 validation 에러 정보
     * @return ResponseEntity(성공시 201 code, 실패시 400 code)
     */
    @PostMapping("/signUp")
    public ResponseEntity<?> addAccount(@RequestBody @Valid AccountDto accountDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        Account account = accountDto.toEntity();
        accountService.addAccount(account);

        return RESPONSE_ENTITY_CREATED;
    }

    /**
     * 회원 탈퇴 메소드
     * @author 조영훈
     * @param loginId 로그인된 사용자 아이디
     * @return ResponseEntity(성공시 204 code)
     */
    @LoginCheck
    @DeleteMapping("/myInfo")
    public ResponseEntity<?> deleteAccount(@SessionId String loginId) {
        accountService.deleteAccount(loginId);
        loginService.accountLogout();

        return RESPONSE_ENTITY_NO_CONTENT;
    }

    /**
     * 회원 비밀번호 수정 메소드
     * @author 조영훈
     * 서버가 요청을 성공적으로 처리했지만 컨텐츠를 리턴하지 않음을 의미하는 204 code return.
     * 객체 validation 실패시 에러정보와 400(Bad Request) code return.
     *
     * @param loginId 로그인한 사용자 아이디
     * @param passwordDto 이전 패스워드, 새로운 패스워드
     * @param errors 패스워드 validation 에러 정보
     * @return ResponseEntity(성공시 201 code, 실패시 2004 code)
     */
    @LoginCheck
    @PatchMapping("/password/{loginId}")
    public ResponseEntity<?> updatePassword(@PathVariable String loginId, @RequestBody @Valid PasswordDto passwordDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        accountService.updatePassword(loginId, passwordDto.getNewPassword());

        return RESPONSE_ENTITY_NO_CONTENT;
    }

    /**
     * 회원 정보(이름, 이메일, 폰번호) 수정 메소드
     * @author 조영훈
     * 서버가 요청을 성공적으로 처리했지만 컨텐츠를 리턴하지 않음을 의미하는 204 code return.
     * 객체 validation 실패시 에러 정보와 400(Bad Request) code return.
     *
     * @param ProfileDto 프로필 정보
     * @param errors 에러 정보
     * @return ResponseEntity(성공시 204 code, 실패시 400 code)
     * */
    @LoginCheck
    @PatchMapping("/myInfo")
    public ResponseEntity<?> updateAccountInfo(@RequestBody @Valid ProfileDto ProfileDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        Account accountInfo = ProfileDto.toEntity();
        accountService.updateAccountInfo(accountInfo);

        return RESPONSE_ENTITY_NO_CONTENT;
    }

}
