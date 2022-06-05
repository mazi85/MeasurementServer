package pl.mazi85.measurementserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.mazi85.measurementserver.service.meassource.MeasSourceService;
import pl.mazi85.measurementserver.service.sample.SampleService;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulingReadDataService {

    private final PlcReadDataService plcReadDataService;
    private final MeasSourceService measSourceService;
    private final SampleService sampleService;

    @Async
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void readPlcData() {
        List<Long> scheduleEnabledMeasSourceIds = measSourceService.getScheduleEnableMeasSources();

        for (Long measSourceId : scheduleEnabledMeasSourceIds) {
        String connectionString = measSourceService.getMeasSourceConnectionString(measSourceId);
        Map<Long, Integer> registers = measSourceService.getMeasSourceRegisters(measSourceId);
            PlcReadResponse plcReadResponse = plcReadDataService.readPlcData(registers, connectionString);
            Map<String, Integer> sampleDefIdValuesMap = plcReadDataService.getResponseValues(plcReadResponse);
            sampleService.saveData(sampleDefIdValuesMap, measSourceId);
        }

    }

}






