package controllers.fueltype;

import model.type.FuelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import repository.FuelTypeRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/fuel")
public class FuelTypeController {
    @Autowired
    private FuelTypeRepository fuelTypeRepository;


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public List<FuelType> getAllFuelTypes() {
        return fuelTypeRepository.findAll();
    }

}
