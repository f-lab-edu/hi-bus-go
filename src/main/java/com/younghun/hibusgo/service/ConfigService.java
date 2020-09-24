package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Config;
import com.younghun.hibusgo.mapper.ConfigMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {

  private final ConfigMapper configMapper;

  public Optional<Config> findConfig() {
    return Optional.ofNullable(configMapper.findConfig());
  }

  public void updateConfig(Config config) {
    configMapper.updateConfig(config);
  }
}
