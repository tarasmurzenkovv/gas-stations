package controllers.fueling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.FuelingService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class FuelingController {
    @Autowired
    private FuelingService fuelingService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/fetch_data", method = RequestMethod.GET)
    public Map<String, Object> getData() {
        return fuelingService.getData();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addFueling(@Valid @RequestBody FuelingDto fuelingDto) {
       fuelingService.addANewFueling(fuelingDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/delete", params = {"id"}, method = RequestMethod.DELETE)
    public void deleteFueling(@Valid @RequestParam("id") int fuelingId) {
        fuelingService.markFuelingEntryAsDeleted(fuelingId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public Integer getNumberOfPages() {
       return fuelingService.getNumberOfPages();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/view", params = {"page"}, method = RequestMethod.GET)
    public List<FuelingDto> displayNextFuelings(@Valid @RequestParam("page") Integer page) {
        return fuelingService.getFuelingsPerPages(page);
    }
}
