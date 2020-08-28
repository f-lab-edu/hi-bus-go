package com.younghun.hibusgo.controller;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_CREATED;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_NOT_FOUND;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Mileage;
import com.younghun.hibusgo.dto.MileageDto;
import com.younghun.hibusgo.service.AccountService;
import com.younghun.hibusgo.service.LoginService;
import com.younghun.hibusgo.service.MileageService;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mileages")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class mileageController {

  private final MileageService mileageService;
  private final LoginService loginService;
  private final AccountService accountService;

  private static final ResponseEntity<String> ACCOUNT_BADE_REQUEST = ResponseEntity.badRequest().body("이미 삭제된 회원이거나, 잘못된 회원입니다.");

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

  /**
   * 마일리지 추가 메소드
   * 관리자가 특정 사용자의 id로 마일리지를 추가한다.
   * @param mileageDto
   * @return ResponseEntity
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @PostMapping("/account")
  public ResponseEntity<?> addMileage(@RequestBody @Valid MileageDto mileageDto) {
    long accountId = mileageDto.getAccountId();

    boolean existAccount = accountService.existsById(accountId);

    if (!existAccount) {
      return ACCOUNT_BADE_REQUEST;
    }

    Mileage mileage = mileageDto.toEntity();

    mileageService.addMileage(mileage);

    return RESPONSE_ENTITY_CREATED;
  }


}
