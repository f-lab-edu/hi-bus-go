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
 * Controller(controller layer) : 비지니스 로직 처리 흐름 제어 및 데이터 변환 및 연산
 * Exception, erorr 처리를 한다. 클라이언트에서 전달 받은 요청을 처리 하도록 Business Layer에
 * 요청을 전달한다. Business Layer에서 전달 받은 응답을 뷰 객체와 연결하거나 응답 타입에 맞게
 * 변환하여 전달하는 계층 *
 *
 * Service(Business Layer): controller를 통해 전달 받은 클라이언트의 요청을 처리하여 비지니스 로직 수행
 * contoller layer와 persistence layer 연결, 다른 계층과 통신하기 위해 인터페이스 제공,
 * transaction 처리 역할을 하는 계층
 *
 * DAO(Persistence Layer) : Business Layer에 전달 받은 데이터를 저장, 수정 ,삭제 하는 계층
 * db에서 조회한 데이터를 Business Layer에 응답 하기 위해 객체화한다.
 * db에 관련된 작업을 수행하기 위해 필요한 쿼리문을 관리하는 계층
 */
@RestController
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final AccountService accountService;
    public static final String ACCOUNT_MEMBER_ID = "ACCOUNT_MEMBER_ID";

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
     * @return 로그인 성공시 200 code return
     * 로그인 실패시 정상이지만 데이터가 없음을 의미하는 204 code teturn
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @NotNull LoginDto loginDto, HttpSession httpSession) {
        String accountId = loginDto.getId();
        String password = loginDto.getPassword();

        Account account = accountService.findByIdAndPassword(accountId, password);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        accountService.login(account.getId(), httpSession);

        return ResponseEntity.ok().build();
    }

    /**
     * 회원 로그아웃 메서드.
     *
     * @param session 현제 접속한 세션
     * @return 로그인 하지 않았을 시 사용자의 권한이 없어 리소스를 사용할수 없음을 의미하는 403 code return
     * 로그아웃 성공시 200 code 반환
     */
    @PostMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        String loginId = (String) session.getAttribute("account");
        Account loginAccount = accountService.findById(loginId);

        if (loginAccount == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            accountService.logout(session);
        }

        return ResponseEntity.ok().build();
    }
}
