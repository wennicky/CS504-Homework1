package demo.service;

import demo.domain.RunningInformation;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface RunningInformationService {

    public List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformationList);

    public RunningInformation findByRunningId(String runningId);

    //public Page<RunningInformation> findByHeartRate(int heartRate, Pageable pageable);

    //public Page<RunningInformation> findByHeartRateGreaterThan(int heartRate, Pageable pageable);

    public Page<RunningInformation> findAllByOrderByHeartRateDesc(Pageable pageable);

    public void deleteByRunningId(String runningId);

    public void deleteAll();
}
