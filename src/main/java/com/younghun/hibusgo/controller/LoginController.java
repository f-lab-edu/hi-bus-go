package com.younghun.hibusgo.controller;

import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.LoginDto;
import com.younghun.hibusgo.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;


/**
 * Controller : 사용자의 요청을 어떻게 처리 결정하는 역할
 *
 * Service: Controller에서 넘어온 요청을 처리하는 역할
 * service method는 한번에 하나의 비지니스로직만 수행
 *
 * DAO: Service에서 넘어온 요청 값을 기준으로 database 조회
 * 조회 결과를 service로 return
 */
@RestController
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final AccountService accountService;

    /**
     *  - 유저 로그인 메서드
     *  login 요청시 id, password로 유저가 있는지 조회
     *  아이디가 있으면 해당 유저 세션 등록
     *
     *  new ResponseEntity() 새로운 객체를 생성하여 return 하는 대신 ResponseEntity에 상수를 담아 return
     *  객체를 new로 생성을 많이 하면 다중의 사용자가 접근했을 때 새로운 객체가 자주 생성되고, GC가 자주 발생한다.
     *  GC를 수행하는 동안 많은 메모리 소비와 jvm 수행이 멈추고 요청을 처리하는 동안 대기하는 경우가 발생한다.
     *  이러한 이유로 새로운 객체롤 최대한 적게 생성하도록 지향.
     * @param loginDto
     * @param httpSession
     * @return ResponseEntity
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @NotNull LoginDto loginDto, HttpSession httpSession) {
        String accountId = loginDto.getId();
        String password = loginDto.getPassword();

        Account account = accountService.findByIdAndPassword(accountId, password);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        else if (Account.Status.DEFAULT.equals(account.getStatus())) {
            ResponseEntity.status(HttpStatus.OK);
            accountService.login(account.getId(), httpSession);
        }

        return ResponseEntity.ok().build();
    }

    /**
     * 회원 로그아웃 메서드.
     *
     * @param session 현제 접속한 세션
     * @return 로그인 하지 않았을 시 401 코드를 반환
     * 로그아웃 성공시 200 코드를 반환
     */
    @PostMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        String loginId = (String) session.getAttribute("account");
        Account loginAccount = accountService.findById(loginId);

        if (loginAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            accountService.logout(session);
        }

        return ResponseEntity.ok().build();
    }
}
