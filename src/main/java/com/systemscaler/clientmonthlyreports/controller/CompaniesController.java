package com.systemscaler.clientmonthlyreports.controller;

import com.systemscaler.clientmonthlyreports.utils.ConstantVariables;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CompaniesController {

    @RequestMapping("/companies")
    public ResponseEntity<String> getCompanies(){
        ResponseEntity<String> companies = null;

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", ConstantVariables.AUTHORIZATION);
            headers.add("Content-Type", ConstantVariables.CONTENT_TYPE);
            HttpEntity<String> request = new HttpEntity<String>(headers);
            companies = restTemplate.exchange("https://api-aus.myconnectwise.net/v2018_6/apis/3.0/company/companies/?pageSize=1000&page=1&page=2&page=3", HttpMethod.GET, request, String.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return companies;
    }
}
