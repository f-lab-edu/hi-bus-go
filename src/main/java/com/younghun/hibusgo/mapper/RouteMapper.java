package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Route;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RouteMapper {

  List<Route> searchByName(String name);

  List<Route> searchByTerminal(Route route);

  boolean existsByName(String name);

  void addRoute(Route route);

}
