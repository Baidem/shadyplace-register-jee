package com.shadyplace.registerjee.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.shadyplace.registerjee.models.User;
import com.shadyplace.registerjee.services.RoleService;
import com.shadyplace.registerjee.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {

    private Validator validator;
    private UserService userService;
    private RoleService roleService;


    public void init() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        this.userService = new UserService();
        this.roleService = new RoleService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("register.jsp").forward(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        User user = new User(nom,prenom,email,password,confirmPassword);

        List<String> errorsMessage = new ArrayList<String>();

        // Vérifier que l'email n'est pas déjà présent
        if (this.userService.getUserByEmail(user.getEmail()).size() != 0){
            errorsMessage.add("Cette email est déjà utilisé");
        }

        // Valider que le champ passeword et la confirmantion sont identique
        if (!user.getPassword().equals(user.getConfirmPassword())){
            errorsMessage.add("les mots de passe ne sont pas identiques");
        }

        // Validation des erreurs Hibernate
        Set<ConstraintViolation<User>> errors = this.validator.validate(user);

        if (errors.isEmpty() && errorsMessage.isEmpty()){
            // Si ok on enregistre
            this.userService.registerUser(user);

            // On redirige vers l'appli spring (page de login)
            response.sendRedirect("http://localhost:8083/login?registersuccess");

        } else {
            request.setAttribute("errorsHibernate", errors);
            request.setAttribute("errors", errorsMessage);

            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }

    public void destroy() {
    }
}