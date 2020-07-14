package com.younghun.hibusgo.controller;

import static com.younghun.hibusgo.utils.LoginUtil.ACCOUNT_MEMBER_ID;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.LoginDto;
import com.younghun.hibusgo.service.AccountService;
import com.younghun.hibusgo.service.LoginService;
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
 * Controller(presntation layer) : 비지니스 로직 흐름 제어 및 데이터 변환 및 연산
 * Exception, erorr 처리를 한다. 클라이언트에서 전달 받은 요청을 처리 하도록 Business Layer에 요청을 전달
 * Business Layer에서 전달 받은 응답을 뷰 객체와 연결하거나 응답 타입에 맞게 변환하여 전달하는 계층
 *
 * Service(Business Layer): controller를 통해 전달 받은 클라이언트의 요청을 처리하여 비지니스 로직 수행
 * 다른 계층과 통신하기 위해 인터페이스 제공, transaction 처리 역할을 하는 계층
 *
 * DAO(Data Access Layer) : Business Layer에 통 전달 받은 데이터를 저장, 수정 ,삭제 하는 계층
 * db에서 조회한 데이터를 Business Layer에 응답 하기 위해 객체화
 * db에 관련된 작업을 수행하기 위해 필요한 쿼리문을 관리
 * database와 접속을 관리하며, 개방 폐쇄 원칙을 적용하여 데이터베이스의 종류가 바뀌어도
 * jdbc 인터페이스를 통해 접근하기때문에 connection 정보를 수정하는 것외에는 변경하지 않도록 한다.
 *
 * 3 계층 아키텍쳐 장점
 *  - 각 계층에 맞는 기능에 집중한 설계 및 개발
 *  - 각 계층별로 역할을 분담하여 동시 다발적인 개발
 *  - 각 계층별로 구분 역할이 구분 되어 흐름 및 로직 파악에 좋다.
*
 * - session 처리
 * servlet container가 httpsession을 이용해 session울 생성하고 관리
 * presentation laeyr에는 servlet container와 controller가 포함 되어 있다
 * 계층적 관점에서 세션 처리를 presentaion layer중 하나인 controller에서 처리 해야 한다.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final AccountService accountService;
    private final LoginService loginService;

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
     * @return 로그인 성공시 200 code return
     * 로그인 실패시 정상이지만 데이터가 없음을 의미하는 204 code teturn
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @NotNull LoginDto loginDto) {
        String accountId = loginDto.getId();
        String password = loginDto.getPassword();

        Account account = accountService.findByIdAndPassword(accountId, password);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        loginService.accountLogin(account.getId());

        return ResponseEntity.ok().build();
    }

    /**
     * 회원 로그아웃 메서드.
     * @return 로그인 하지 않았을 시 사용자의 권한이 없음을 의미하는 401 code return
     * 로그아웃 성공시 200 code 반환
     */
    @LoginCheck
    @PostMapping("/logout")
    public ResponseEntity logout() {
        loginService.accountLogout();

        return ResponseEntity.ok().build();
    }
}
