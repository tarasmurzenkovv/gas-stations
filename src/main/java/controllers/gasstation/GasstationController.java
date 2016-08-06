package controllers.gasstation;

import model.Customer;
import model.GasStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import repository.CustomerRepository;
import service.GasStationService;
import service.Revenue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class GasstationController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private GasStationService gasStationService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public List<GasStation> viewGasStations() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.getByLogin(login);
        return customer.getGasstations();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addGasStation(@Valid @RequestBody GasStationDto gasStationDto) {
        gasStationService.addGasStation(gasStationDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/update", params = {"id"}, method = RequestMethod.POST)
    public GasStation loadUpdateInfo(@Valid @RequestParam("id") Integer gasStationId) {
        return gasStationService.loadInfo(gasStationId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void performUpdateInfo(@Valid @RequestBody GasStationDto gasStationDto) {
        gasStationService.performUpdate(gasStationDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/search", params = {"name"}, method = RequestMethod.GET)
    public Map<String, GasStation> findGasStations(@Valid @RequestParam(value = "name") String fuelTypeName) {
        return gasStationService.findRelevantGasStations(fuelTypeName);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/revenue", params = {"from", "to", "name"}, method = RequestMethod.GET)
    public List<Revenue> calculateRevenue(@NotNull @RequestParam(value = "from") Date fromDate,
                                          @NotNull @RequestParam(value = "to") Date toDate,
                                          @NotNull @RequestParam(value = "name") String gasStationName) {
        return gasStationService.calculateRevenueForDateInterval(gasStationName, fromDate, toDate);
    }

}
