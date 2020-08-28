package com.younghun.hibusgo.controller;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_NOT_FOUND;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Mileage;
import com.younghun.hibusgo.service.LoginService;
import com.younghun.hibusgo.service.MileageService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mileages")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class mileageController {

  private final MileageService mileageService;
  private final LoginService loginService;

  /**
   * 마일리지 조회 메소드
   * 로그인한 사용자의 id로 마일리지를 조회한다.
   * @return ResponseEntity
   */
  @LoginCheck(userLevel = UserLevel.USER)
  @GetMapping("/my")
  public ResponseEntity<?> getMyMileage() {

    Long accountId = loginService.getLoginAccountId().get();

    Optional<Mileage> mileage = mileageService.findById(accountId);

    if (!mileage.isPresent()) {
      return RESPONSE_NOT_FOUND;
    }

    return ResponseEntity.ok().body(mileage.get());
  }


}
