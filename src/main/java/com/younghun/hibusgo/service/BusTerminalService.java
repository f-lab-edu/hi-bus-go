package com.younghun.hibusgo.service;


import com.younghun.hibusgo.domain.BusTerminal;
import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.mapper.BusTerminalMapper;
import com.younghun.hibusgo.utils.CacheKeys;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class BusTerminalService {

    private final BusTerminalMapper terminalMapper;

    public BusTerminal findById(long id) {
        return terminalMapper.findById(id);
    }

    /**
     * @Cacheable : 동일 값이 Cache에 있는 경우 Cache에서 데이터를 return합니다.
     * 만약 동일 key 값이 없을 경우 메소드를 실행하고 반환된 결과 값을 Cache에 저장합니다.
     * unless = "#result == null" -> 조회 결과 값이 null이 아닐 경우에만 캐싱
     */
    @Cacheable(value = CacheKeys.TERMINALS_NAME, key = "#name", unless = "#result == null", cacheManager = "redisCacheManager")
    public Optional<BusTerminal> findByNameAndRegion(String name, String region) {
        return Optional.ofNullable(terminalMapper.findByNameAndRegion(name, region))
            .filter(o -> o.getStatus() == DataStatus.DEFAULT);
    }

    @Cacheable(value = CacheKeys.TERMINALS_REGION, key = "#region", unless = "#result == null", cacheManager = "redisCacheManager")
    public List<BusTerminal> searchByRegion(String region) {
        return terminalMapper.searchByRegion(region);
    }

    @Cacheable(value = CacheKeys.TERMINALS_TOTAL, key = "'total'", unless = "#result == null", cacheManager = "redisCacheManager")
    public List<BusTerminal> searchTotal() {
        return terminalMapper.searchTotal();
    }

    public void addBusTerminal(BusTerminal busTerminal) {
        terminalMapper.addBusTerminal(busTerminal);
    }

    public void deleteBusTerminal(long id) {
        terminalMapper.deleteBusTerminal(id);
    }

    public void updateBusTerminal(BusTerminal busTerminal) {
        terminalMapper.updateBusTerminal(busTerminal);
    }

    public boolean existsByName(String name) {
        return terminalMapper.existsByName(name);
    }

    public boolean existsById(long id) {
        return terminalMapper.existsById(id);
    }
}
