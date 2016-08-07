package controllers.customer;

import model.Customer;
import model.type.CustomerType;
import model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.CustomerService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    // store cookie for a one day
    private final static int COOKIE_MAX_AGE = 24 * 60 * 60;
    @Autowired
    private CustomerService customerService;

    private void saveCookie(String cookieName, String customerLogin, String customerType, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, customerLogin);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        cookie.setValue(customerType);
        response.addCookie(cookie);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ajax_login", method = RequestMethod.POST)
    public void processUserLogin(HttpServletResponse response) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.processCustomerLogin(response, login);
        saveCookie("GasStationCookie", customer.getLogin(), customer.getCustomerType().getTypeName(), response);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/customer_types", method = RequestMethod.GET)
    public List<CustomerType> customerTypes() {
        return customerService.getAllCustomerTypes();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/gender_groups", method = RequestMethod.GET)
    public List<Gender> genderGroups() {
        List<Gender> genders = new ArrayList<>();
        genders.add(Gender.MALE);
        genders.add(Gender.FEMALE);
        return genders;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void processUserRegistration(@Valid @RequestBody NewCustomerInformationDto newCustomerInformationDto) {
        customerService.registerANewCustomer(newCustomerInformationDto);
    }
}
