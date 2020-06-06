package com.younghun.hibusgo.controller;

import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.AccountDto;
import com.younghun.hibusgo.dto.LoginDto;
import com.younghun.hibusgo.service.AccountService;
import com.younghun.hibusgo.validator.AccountDtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
            return ResponseEntity.badRequest().build();
        }

        Account account = accountDto.toEntity();

        accountService.addAccount(account);

        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }

    /**
     *  - 유저 로그인 메서드
     *  login 요청시 id, password로 유저가 있는지 조회
     *  아이디가 있으면 해당 유저 세션 등
     *
     * @param loginDto
     * @param httpSession
     * @return ResponseEntity
     */
    @GetMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody @NotNull LoginDto loginDto, HttpSession httpSession) {
        ResponseEntity responseEntity = null;

        String accountId = loginDto.getId();
        String password = loginDto.getPassword();

        Account account = accountService.login(accountId, password);

        if (account == null) {
            responseEntity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        else if (Account.Status.DEFAULT.equals(account.getStatus())) {
            httpSession.setAttribute("account", account.getId());
            responseEntity = new ResponseEntity(account.getId(), HttpStatus.OK);
        }

        return responseEntity;
    }

    /**
     * 회원 로그아웃 메서드.
     *
     * @author jun
     * @param session 현제 접속한 세션
     * @return 로그인 하지 않았을 시 401코드를 반환하고 result:NO_LOGIN 반환 로그아웃 성공시 200 코드를 반환
     */
    @GetMapping("logout")
    public void logout(HttpSession session) {
        accountService.logout(session);
    }

}
