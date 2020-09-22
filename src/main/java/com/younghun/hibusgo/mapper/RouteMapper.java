package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Route;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RouteMapper {

  boolean existsById(long id);

  List<Route> searchRoute(Route route);

  void addRoute(Route route);

  void updateRoute(Route route);

  void deleteRoute(long id);
}
