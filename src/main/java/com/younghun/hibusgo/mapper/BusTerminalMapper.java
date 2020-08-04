package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.BusTerminal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusTerminalMapper {

    BusTerminal findById(int id);

    BusTerminal findByNameAndRegion(String name, String region);

    void addBusTerminal(BusTerminal busTerminal);

    void deleteBusTerminal(int id);

    void updateBusTerminal(BusTerminal busTerminal);
}
