package controllers.cartype;

import model.type.CarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import repository.CarTypeRepository;

import java.util.List;

@RestController
public class CarTypeController {
    @Autowired
    private CarTypeRepository carTypeRepository;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/vehicle_types", method = RequestMethod.GET)
    public List<CarType> carTypes() {
        return carTypeRepository.findAll();
    }

}
