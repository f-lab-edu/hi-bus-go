package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.Reservation;
import com.younghun.hibusgo.mapper.ReservationMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationMapper reservationMapper;

  public Optional<Reservation> findById(long id) {
    return Optional.ofNullable(reservationMapper.findById(id))
        .filter(o -> o.getStatus() != DataStatus.DELETED);
  }

  public List<Reservation> findByAccountId(long id) {
    return reservationMapper.findByAccountId(id);
  }


}
