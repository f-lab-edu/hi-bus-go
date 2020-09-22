package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Charge;
import com.younghun.hibusgo.domain.RouteGrade;
import com.younghun.hibusgo.domain.SeatGrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChargeMapper {

  boolean existsById(long id);

  Charge findByRouteIdAndGrades(long routeId, RouteGrade routeGrade, SeatGrade seatGrade);

  void addCharge(Charge charge);

  void updateCharge(Charge charge);

  void deleteCharge(long id);
}
