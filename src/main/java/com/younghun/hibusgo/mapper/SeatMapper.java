package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Seat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeatMapper {

  Seat findById(long id);

  boolean existEmptySeatByRoutIdAndNumber(long routeId, long seatNumber);

  void updateStatus(long id, long seatNumber);
}
