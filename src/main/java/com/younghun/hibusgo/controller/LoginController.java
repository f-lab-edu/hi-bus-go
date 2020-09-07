package com.younghun.hibusgo.controller;



import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_OK;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.LoginDto;
import com.younghun.hibusgo.service.AccountService;
import com.younghun.hibusgo.service.LoginService;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
 * servlet container가 httpsession을 이용해 session울 생성하고 관리한다.
 * presentation laeyr에는 servlet container와 controller가 포함 되어 있다.
 * 계층적 관점에서 세션 처리를 presentaion layer중 하나인 controller에서 처리 해야 한다.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final AccountService accountService;
    private final LoginService loginService;

    /**
     * 유저 로그인 메서드
     *
     * login 요청시 id, password로 유저가 있는지 조회
     * 아이디가 있으면 해당 유저 세션 등록
     *
     * @param loginDto
     * @return 로그인 성공시 200 code return
     * 로그인 실패시 잘못된 요청을 의미하는 400 code return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        String userId = loginDto.getUserId();
        String password = loginDto.getPassword();

        Optional<Account> account = accountService.findByUserIdAndPassword(userId, password);

        if (!account.isPresent()) {
            return ResponseEntity.badRequest().body("가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
        }

        loginService.accountLogin(account.get().getId());

        return RESPONSE_ENTITY_OK;
    }

    /**
     * 회원 로그아웃 메소드
     *
     * @return ResponseEntity(성공시 200 code)
     */
    @LoginCheck(userLevel = UserLevel.ANONYMOUS)
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        loginService.accountLogout();

        return RESPONSE_ENTITY_OK;
    }
}
