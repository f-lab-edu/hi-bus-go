package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Route;
import com.younghun.hibusgo.mapper.RouteMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RouteService {

  private final RouteMapper routeMapper;

  public List<Route> searchByName(String name) {
    return routeMapper.searchByName(name);
  }

  public List<Route> searchByTerminal(Route route) {
    return routeMapper.searchByTerminal(route);
  }

  public boolean existsByName(String name) {
    return routeMapper.existsByName(name);
  }

  public void addRoute(Route route) {
    routeMapper.addRoute(route);
  }

}
