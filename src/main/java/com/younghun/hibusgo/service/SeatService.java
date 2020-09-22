package com.younghun.hibusgo.service;

import com.younghun.hibusgo.mapper.SeatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class SeatService {

  private final SeatMapper seatMapper;

  public boolean existEmptySeatByRoutIdAndNumber(long routeId, long seatNumber) {
    return seatMapper.existEmptySeatByRoutIdAndNumber(routeId, seatNumber);
  }
}
