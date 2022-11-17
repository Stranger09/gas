package com.stranger.gas.security;

import com.stranger.gas.model.Company;
import com.stranger.gas.model.User;
import com.stranger.gas.repository.CompanyRepository;
import com.stranger.gas.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InjectingData {
    private UserService userService;
    private AuthenticationService authenticationService;
    private CompanyRepository companyRepository;

    public InjectingData(UserService userService, AuthenticationService authenticationService, CompanyRepository companyRepository) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.companyRepository = companyRepository;
    }

    @PostConstruct
    public void init() {
        User testUser1 = new User();
        testUser1.setFirstName("Test");
        testUser1.setLastName("User");
        testUser1.setEmail("test@gmail.com");
        testUser1.setPassword("1");

        authenticationService.register(testUser1.getFirstName(), testUser1.getLastName(), testUser1.getEmail(), testUser1.getPassword());

        User testUser2 = new User();
        testUser2.setFirstName("Ann");
        testUser2.setLastName("Nna");
        testUser2.setEmail("ann@gmail.com");
        testUser2.setPassword("1");

        authenticationService.register(testUser2.getFirstName(), testUser2.getLastName(), testUser2.getEmail(), testUser2.getPassword());

        Company wogCompany = new Company();
        wogCompany.setName("WOG");
        wogCompany.setMapLink("https://wog.ua/ua/map/");
        wogCompany.setLinkName("wog.ua");
        wogCompany.setHotLine("0800300525");

        companyRepository.save(wogCompany);

        Company upgCompany = new Company();
        upgCompany.setName("UPG");
        upgCompany.setMapLink("https://upg.ua/merezha_azk/");
        upgCompany.setLinkName("upg.ua");
        upgCompany.setHotLine("0800500064");

        companyRepository.save(upgCompany);
    }
}

