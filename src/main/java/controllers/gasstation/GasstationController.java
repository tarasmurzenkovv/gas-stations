package controllers.gasstation;

import model.Customer;
import model.GasStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import repository.CustomerRepository;
import service.GasStationService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GasstationController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private GasStationService gasStationService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/view_gasstations", method = RequestMethod.GET)
    public List<GasStation> viewGasStations() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.getByLogin(login);
        return customer.getGasstations();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/add_gasstation", method = RequestMethod.POST)
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

}
