package controllers.vehicle;

import model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.VehicleService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VehiclesController {
    @Autowired
    private VehicleService vehicleService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public List<Vehicle> viewVehicles() {
        return vehicleService.getAuthenticatedCustomerVehicles();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        vehicleService.processAddingANewVehicle(vehicleDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/delete", params = {"id"}, method = RequestMethod.DELETE)
    public void deleteVehicle(@Valid @RequestParam("id") Integer vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }
}
