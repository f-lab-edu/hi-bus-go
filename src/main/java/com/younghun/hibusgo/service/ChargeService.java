package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Charge;
import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.RouteGrade;
import com.younghun.hibusgo.domain.SeatGrade;
import com.younghun.hibusgo.mapper.ChargeMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargeService {

  private final ChargeMapper chargeMapper;

  public boolean existsById(long id) {
    return chargeMapper.existsById(id);
  }

  public Optional<Charge> findByRouteIdAndGrades(long routeId, RouteGrade routeGrade, SeatGrade seatGrade) {
    return Optional.ofNullable(chargeMapper.findByRouteIdAndGrades(routeId, routeGrade, seatGrade))
        .filter(o -> o.getStatus() == DataStatus.DEFAULT);
  }

  public void addCharge(Charge charge) {
    chargeMapper.addCharge(charge);
  }

  public void updateCharge(Charge charge) {
    chargeMapper.updateCharge(charge);
  }

  public void deleteCharge(long id) {
    chargeMapper.deleteCharge(id);
  }
}
