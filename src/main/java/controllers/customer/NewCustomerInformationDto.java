package controllers.customer;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class NewCustomerInformationDto {

    @NotNull(message = "Enter your first name")
    String firstName;

    @NotNull(message = "Enter your last name")
    String lastName;

    @NotNull(message = "Enter your date of birth")
    @DateTimeFormat
    Date dateOfBirth;

    @NotNull(message = "Enter your login")
    String login;

    @Length(min = 5, message = "Password length must be more than 5 characters")
    @NotNull(message = "Enter your password")
    String password;

    @Email
    @NotNull(message = "Enter your email")
    String email;

    @NotNull(message = "Select your group")
    String group;

    String gender;

    public NewCustomerInformationDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = (StringUtils.isEmpty(firstName) ? null : firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = (StringUtils.isEmpty(lastName) ? null : lastName);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = (dateOfBirth == null) ? null : dateOfBirth;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = (StringUtils.isEmpty(login) ? null : login);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = (StringUtils.isEmpty(password) ? null : password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (StringUtils.isEmpty(email) ? null : email);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = StringUtils.isEmpty(group) ? null : group;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = (StringUtils.isEmpty(gender) ? null : gender);
    }
}
