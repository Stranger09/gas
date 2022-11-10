package com.stranger.gas.controller;

import com.stranger.gas.dto.UserRequestDto;
import com.stranger.gas.security.AuthenticationService;
import com.stranger.gas.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserService userService;
    private AuthenticationService authenticationService;

    public RegistrationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @ModelAttribute("user")
    public UserRequestDto getDto() {
        return new UserRequestDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") UserRequestDto user, BindingResult bindingResult, ModelMap modelMap, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("successMessage", "Please correct errors in the form!");
            modelMap.addAttribute("bindingResult", bindingResult);
            return "registration";
        } else if(!userService.isUniqueUsername(user.getEmail())){
            model.addAttribute("successMessage", "Your email has to be unique! User with this email already exists!");
            return "registration";
        } else {
            authenticationService.register(user.getFirstName(),
                    user.getLastName(), user.getEmail(),
                    user.getPassword());
            return "redirect:/registration?success";
        }
    }
}

