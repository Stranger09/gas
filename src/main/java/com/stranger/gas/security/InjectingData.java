package com.stranger.gas.security;

import javax.annotation.PostConstruct;

import com.stranger.gas.model.Company;
import com.stranger.gas.repository.CompanyRepository;
import com.stranger.gas.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class InjectingData {
    private UserService userService;
    /* private AuthenticationService authenticationService;*/
    private CompanyRepository companyRepository;

    @PostConstruct
    public void init() {
//        User testUser1 = new User();
//        testUser1.setFirstName("Test");
//        testUser1.setLastName("User");
//        testUser1.setEmail("test@gmail.com");
//        testUser1.setPassword("1");
//
//        authenticationService.register(testUser1.getFirstName(), testUser1.getLastName(), testUser1.getEmail(), testUser1.getPassword());
//
//        User testUser2 = new User();
//        testUser2.setFirstName("Ann");
//        testUser2.setLastName("Nna");
//        testUser2.setEmail("ann@gmail.com");
//        testUser2.setPassword("1");
//
//        authenticationService.register(testUser2.getFirstName(), testUser2.getLastName(), testUser2.getEmail(), testUser2.getPassword());

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

        Company brsmCompany = new Company();

        brsmCompany.setName("BRSM");
        brsmCompany.setMapLink("https://brsm-nafta.com/map");
        brsmCompany.setLinkName("brsm-nafta.com");
        brsmCompany.setHotLine("0800303404");

        companyRepository.save(brsmCompany);

        Company socarCompany = new Company();

        socarCompany.setName("SOCAR");
        socarCompany.setMapLink("https://socar.ua/map");
        socarCompany.setLinkName("socar.ua");
        socarCompany.setHotLine("0800508585");

        companyRepository.save(socarCompany);
    }
}


