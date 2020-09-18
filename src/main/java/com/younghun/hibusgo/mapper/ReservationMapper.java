package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Reservation;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

  Reservation findById(long id);

  List<Reservation> findByAccountId(long id);

  void addReservation(Reservation reservation);
}
