package com.younghun.hibusgo.utils;

import com.younghun.hibusgo.service.LoginService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Component;

/**
 * @EnableRedisHttpSession : Filter를 구현한 springSessionRepositoryFilter라는 이름의 스프링 빈을 만들어준다.
 * 이 필터는 HttpSession 구현체를 Spring Session 으로 교체하는 역할을 하고, 이 Spring Session은 Redis에 저장된다.
 *
 * session에 저장되는 정보를 redis에 저장한다. 클라이언트의 세션 쿠키값은 따로 설정하지 않으면 session이라는 key값으로 생성된다.
 *
 * session에 string, set, hash로 3가지 데이터 타입으로 저장
 * spring:session:sessions:expires:(session id) (string) - 세션의 만료
 * spring:session:expirations (set)키 - 만료 시간에 삭제되는 세션 정보
 * spring:session:expirations:(expire time) (hash) - 세션의 생성 시간, 마지막 세션 조회 시간, 최대 타임아웃 허용 시간과 해당 세션에 저장한 데이터를 저장
 */

@EnableRedisHttpSession
@Component
@RequiredArgsConstructor
public class LoginUtil implements LoginService {

  public static final String ACCOUNT_MEMBER_ID = "ACCOUNT_MEMBER_ID";

  public final HttpSession session;

  /**
   * 계정 로그인 메소드
   * @param accountId
   * 계정 로그인 id를 세션으로 등록
   */
  @Override
  public void accountLogin(String accountId) {
    session.setAttribute(ACCOUNT_MEMBER_ID, accountId);
  }

  /**
   * 계정 로그아웃 메소드
   * 계정 로그인 id 세션 정보 삭제
   */
  @Override
  public void accountLogout() {
    session.removeAttribute(ACCOUNT_MEMBER_ID);
  }

  /**
   * 로그인 여부 확인 메소드
   * @return boolean
   */
  @Override
  public boolean isLoginAccount() {
    String accountId = (String) session.getAttribute(ACCOUNT_MEMBER_ID);
    return accountId != null ? true : false;
  }

  /**
   * 로그인된 사용자 정보 가져오는 메소드
   * @return Account
   */
  @Override
  public String getLoginAccountId() {
    return Optional.ofNullable(session.getAttribute(ACCOUNT_MEMBER_ID))
        .map(String::valueOf)
        .orElse(null);
  }
}
