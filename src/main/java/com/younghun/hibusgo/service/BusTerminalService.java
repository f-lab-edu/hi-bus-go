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

    /**
     * @Cacheable : 동일 값이 Cache에 있는 경우 Cache에서 데이터를 return합니다.
     * 만약 동일 key 값이 없을 경우 메소드를 실행하고 반환된 결과 값을 Cache에 저장합니다.
     */
    @Cacheable(value = "terminals.name", key = "#name", cacheManager = "redisCacheManager")
    public Optional<BusTerminal> findByNameAndRegion(String name, String region) {
        return Optional.ofNullable(terminalMapper.findByNameAndRegion(name, region))
            .filter(o -> o.getStatus() == Status.DEFAULT);
    }

    @Cacheable(value = "terminals.region", key = "#region", cacheManager = "redisCacheManager")
    public List<BusTerminal> searchByRegion(String region) {
        return terminalMapper.searchByRegion(region);
    }

    @Scheduled(fixedDelay = 300000L) // 5분마다 캐시 갱신
    @Cacheable(value = "terminals.total", key = "'total'")
    public List<BusTerminal> searchTotal() {
        return terminalMapper.searchTotal();
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

    public boolean existsByName(String name) {
        return terminalMapper.existsByName(name);
    }

    public boolean existsById(int id) {
        return terminalMapper.existsById(id);
    }
}
