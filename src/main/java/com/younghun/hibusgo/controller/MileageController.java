package com.younghun.hibusgo.controller;


import static com.younghun.hibusgo.utils.ResponseConstants.ACCOUNT_BAD_REQUEST;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_NOT_FOUND;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Mileage;

import com.younghun.hibusgo.dto.MileageDto;
import com.younghun.hibusgo.service.AccountService;

import com.younghun.hibusgo.service.MileageService;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mileages")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class MileageController {

  private final MileageService mileageService;
  private final AccountService accountService;

  /**
   * 마일리지 조회 메소드
   * 특정 사용자의 id로 마일리지를 조회한다.
   * @return ResponseEntity
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @GetMapping("/{id}")
  public ResponseEntity<?> getMyMileage(@PathVariable long id) {

    boolean existAccount = accountService.existsById(id);

    if (!existAccount) {
      return ACCOUNT_BAD_REQUEST;
    }

    Optional<Mileage> mileage = mileageService.findByAccountId(id);

    if (!mileage.isPresent()) {
      return RESPONSE_NOT_FOUND;
    }

    return ResponseEntity.ok().body(mileage.get());
  }

  /**
   * 마일리지 수정 메소드
   * 관리자가 특정 사용자의 마일리지를 수정한다.
   * 사용자의 마일리지가 존재하지 않을 경우 추가, 존재할 경우 추가한다.
   * @param mileageDto
   * @return ResponseEntity
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @PatchMapping("/account")
  public ResponseEntity<?> updateMileage(@RequestBody @Valid MileageDto mileageDto) {
    long accountId = mileageDto.getAccountId();

    boolean existAccount = accountService.existsById(accountId);

    if (!existAccount) {
      return ACCOUNT_BAD_REQUEST;
    }

    Mileage mileage = mileageDto.toEntity();

    mileageService.updateMileage(mileage);

    return RESPONSE_ENTITY_NO_CONTENT;
  }


}
