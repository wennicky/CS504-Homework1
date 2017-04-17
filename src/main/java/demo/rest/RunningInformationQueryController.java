package demo.rest;


import com.fasterxml.jackson.databind.util.JSONPObject;
import demo.domain.RunningInformation;
import demo.service.RunningInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RunningInformationQueryController {

    private final String DefaultPage = "0";
    private final String DefaultItemPerPage = "2";

    @Autowired
    private RunningInformationService runningInformationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInformation> runningInformationList) {
        runningInformationService.saveRunningInformation(runningInformationList);
    }

    @Transactional
    @RequestMapping(value = "/delete/{runningId}", method = RequestMethod.DELETE)
    public void deleteByRunningId(@PathVariable String runningId) {
        runningInformationService.deleteByRunningId(runningId);
    }

    @RequestMapping(value = "/runningId/{runningId}", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> findByRunningId(@PathVariable String runningId) {

        RunningInformation content = runningInformationService.findByRunningId(runningId);
        JSONObject result = new JSONObject();
        result.put("runningId", content.getRunningId());
        result.put("totalRunningTime", content.getTotalRunningTime());
        result.put("heartRate", content.getHeartRate());
        result.put("userId", content.getId());
        result.put("userName", content.getUserInfo().getUsername());
        result.put("userAddress", content.getUserInfo().getAddress());
        result.put("healthWarningLevel", content.getHealthWarningLevel());
        return new ResponseEntity<JSONObject>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/allRunningInformation", method = RequestMethod.GET)
    public ResponseEntity<List<JSONObject>> findAllByOrderByHealthWarningLevelAsc(
            @RequestParam(name = "page", required = false, defaultValue = DefaultPage)  Integer page,
            @RequestParam(name = "size", required = false, defaultValue = DefaultItemPerPage)   Integer size
            ) {

        Page<RunningInformation> rawResults = runningInformationService.findAllByOrderByHeartRateDesc(new PageRequest(page, size));
        List<RunningInformation> content = rawResults.getContent();

        List<JSONObject> results = new ArrayList<JSONObject>();
        for (RunningInformation item : content) {
            JSONObject info = new JSONObject();
            info.put("runningId", item.getRunningId());
            info.put("totalRunningTime", item.getTotalRunningTime());
            info.put("heartRate", item.getHeartRate());
            info.put("userId", item.getId());
            info.put("userName", item.getUserInfo().getUsername());
            info.put("userAddress", item.getUserInfo().getAddress());
            info.put("healthWarningLevel", item.getHealthWarningLevel());
            results.add(info);
        }
        return new ResponseEntity<List<JSONObject>>(results, HttpStatus.OK);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        runningInformationService.deleteAll();
    }

}
