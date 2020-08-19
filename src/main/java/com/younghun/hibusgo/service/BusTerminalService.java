package com.younghun.hibusgo.service;


import com.younghun.hibusgo.domain.BusTerminal;
import com.younghun.hibusgo.domain.BusTerminal.Status;
import com.younghun.hibusgo.mapper.BusTerminalMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class BusTerminalService {

    private final BusTerminalMapper terminalMapper;

    public BusTerminal findById(int id) {
        return terminalMapper.findById(id);
    }

    @Scheduled(fixedDelay = 300000) //5분마다 캐시에 저장
    @Cacheable(value = "terminals.name", key = "#name", cacheManager = "redisCacheManager")
    public Optional<BusTerminal> findByNameAndRegion(String name, String region) {
        return Optional.ofNullable(terminalMapper.findByNameAndRegion(name, region))
            .filter(o -> o.getStatus() == Status.DEFAULT);
    }

    @Scheduled(fixedDelay = 300000) //5분마다 캐시에 저장
    @Cacheable(value = "terminals.region", key = "#region", cacheManager = "redisCacheManager")
    public Optional<List<BusTerminal>> searchByRegion(String region) {
        return Optional.ofNullable(terminalMapper.searchByRegion(region));
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
