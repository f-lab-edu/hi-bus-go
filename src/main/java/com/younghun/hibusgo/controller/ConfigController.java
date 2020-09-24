package com.younghun.hibusgo.controller;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Config;
import com.younghun.hibusgo.dto.ConfigDto;
import com.younghun.hibusgo.service.ConfigService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class ConfigController {

  private final ConfigService configService;

  /**
   * 설정 정보 조회 메소드
   * @return Config
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @GetMapping()
  public ResponseEntity<Config> getConfig() {
    Optional<Config> config = configService.findConfig();

    return ResponseEntity.ok().body(config.get());
  }

  /**
   * 설정 정보 수정 메소드
   * 관리자가 설정 정보를 수정한다.
   * 설정 정보가 존재하지 않을 경우 추가, 존재할 경우 추가한다.
   * @param configDto
   * @return ResponseEntity<Void>
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @PatchMapping()
  public ResponseEntity<Void> updateMileage(@RequestBody ConfigDto configDto) {
    Config config = configDto.toEntity();
    configService.updateConfig(config);

    return RESPONSE_ENTITY_NO_CONTENT;
  }

}
