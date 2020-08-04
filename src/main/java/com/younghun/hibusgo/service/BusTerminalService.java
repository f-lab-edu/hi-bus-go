package com.younghun.hibusgo.service;


import com.younghun.hibusgo.domain.BusTerminal;
import com.younghun.hibusgo.domain.BusTerminal.Status;
import com.younghun.hibusgo.mapper.BusTerminalMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class BusTerminalService {

    private final BusTerminalMapper terminalMapper;

    public BusTerminal findById(int id) {
        return terminalMapper.findById(id);
    }

    public Optional<BusTerminal> findByNameAndRegion(String name, String region) {
        return Optional.ofNullable(terminalMapper.findByNameAndRegion(name, region))
            .filter(o -> o.getStatus() == Status.DEFAULT);
    }

    public void addBusTerminal(BusTerminal busTerminal) {
        terminalMapper.addBusTerminal(busTerminal);
    }

    public void deleteBusTerminal(int id) {
        terminalMapper.deleteBusTerminal(id);
    }

    public void updateBusTerminal(BusTerminal busTerminal) {
        terminalMapper.updateBusTerminal(busTerminal);
    }
}
