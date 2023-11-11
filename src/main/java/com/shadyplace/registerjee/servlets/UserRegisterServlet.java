package com.shadyplace.registerjee.servlets;

import com.shadyplace.registerjee.models.FidelityRank;
import com.shadyplace.registerjee.models.User;
import com.shadyplace.registerjee.models.enums.Country;
import com.shadyplace.registerjee.models.enums.FamilyLinkLabel;
import com.shadyplace.registerjee.services.UserService;
import com.shadyplace.registerjee.services.FamilyLinkService;
import com.shadyplace.registerjee.services.FidelityRankService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@WebServlet(name = "user-register", value = "/user-register")
public class UserRegisterServlet extends HttpServlet {

    private Validator validator;
    private UserService userService;
    private FamilyLinkService familyLinkService;
    private FidelityRankService fidelityRankService;

    public void init() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        this.userService = new UserService();
        this.familyLinkService = new FamilyLinkService();
        this.fidelityRankService = new FidelityRankService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // list of countries
        List<Country> countries = Country.getAllDestinationCountries();
        request.setAttribute("countries", countries);

        request.getRequestDispatcher("user-register.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Country> countries = Country.getAllDestinationCountries();
        request.setAttribute("countries", countries);

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String countryName = request.getParameter("country-name");

        Country country = Country.getCountryByNameOrAbbreviation(countryName);

        User customer = new User(lastname,firstname,email,password,confirmPassword, country);
        customer.setFamilyLink(this.familyLinkService.getFamilyLinkByLabel(FamilyLinkLabel.NO_FAMILY));
        customer.setRegistrationDate(Calendar.getInstance());
        customer.setCurrentFidelityRank(this.fidelityRankService.findByLabel("Newcomer"));

        List<String> errorsMessage = new ArrayList<String>();

        // Check that the email is not already present
        if (this.userService.getCustomerByEmail(customer.getEmail()).size() != 0){
            errorsMessage.add("This email is already used");
        }

        // Validate that the password field and the confirmation are identical
        if (!customer.getPassword().equals(customer.getConfirmPassword())){
            errorsMessage.add("Passwords are not the same");
        }

        // Hibernate error validation
        Set<ConstraintViolation<User>> errors = this.validator.validate(customer);

        if (errors.isEmpty() && errorsMessage.isEmpty()){
            // If ok we save
            this.userService.registerCustomer(customer);

            // Redirects to spring app (login page)
            response.sendRedirect("http://localhost:8083/login?registersuccess");

        } else {
            request.setAttribute("errorsHibernate", errors);
            request.setAttribute("errors", errorsMessage);

            request.getRequestDispatcher("user-register.jsp").forward(request, response);
        }

    }

    public void destroy() {
    }
}