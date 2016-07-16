package controllers.dashboard;

import model.GasStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.GasStationService;
import service.Revenue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class DashboardController {
    @Autowired
    private GasStationService gasStationService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/search_gasstations", params = {"name"}, method = RequestMethod.GET)
    public Map<String, GasStation> findGasStations(@Valid @RequestParam(value = "name") String fuelTypeName) {
        return gasStationService.findRelevantGasStations(fuelTypeName);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/calculate_revenue", params = {"from", "to", "name"}, method = RequestMethod.GET)
    public List<Revenue> calculateRevenue(@NotNull @RequestParam(value = "from") Date fromDate,
                                          @NotNull @RequestParam(value = "to") Date toDate,
                                          @NotNull @RequestParam(value = "name") String gasStationName) {
        return gasStationService.calculateRevenueForDateInterval(gasStationName, fromDate, toDate);
    }
}
