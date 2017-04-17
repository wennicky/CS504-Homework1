package demo.service.impl;


import demo.domain.RunningInformation;
import demo.domain.RunningInformationRepository;
import demo.service.RunningInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunningInformationServiceImpl implements RunningInformationService{

    private RunningInformationRepository runningInformationRepository;

    @Autowired
    public RunningInformationServiceImpl(RunningInformationRepository runningInformationRepository) {
        this.runningInformationRepository = runningInformationRepository;
    }

    @Override
    public List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformationList) {
        return runningInformationRepository.save(runningInformationList);
    }

    @Override
    public RunningInformation findByRunningId(String runningId) {
        return runningInformationRepository.findByRunningId(runningId);
    }

    @Override
    public Page<RunningInformation> findAllByOrderByHeartRateDesc(Pageable pageable) {
        return runningInformationRepository.findAllByOrderByHeartRateDesc(pageable);
    }

    @Override
    public void deleteByRunningId(String runningId) {
        runningInformationRepository.deleteByRunningId(runningId);
    }

    @Override
    public void deleteAll() {
        runningInformationRepository.deleteAll();
    }

}
