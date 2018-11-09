package com.systemscaler.clientmonthlyreports.controller;

import com.systemscaler.clientmonthlyreports.utils.ConstantVariables;
import com.systemscaler.clientmonthlyreports.utils.Utilities;
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
public class ReportsController {

    private void print(String msg) {
        Utilities utilities = new Utilities();
        utilities.printMessage(msg);
    }

    @RequestMapping("/reportsByMonth")
    public ResponseEntity<String> generateReportByMonth(){
        ResponseEntity<String> reportsByMonth = null;

        try {
            RestTemplate reportsByMonthTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", ConstantVariables.AUTHORIZATION);
            httpHeaders.add("Content-type", ConstantVariables.CONTENT_TYPE);
            HttpEntity<String> requestReportsByMonth = new HttpEntity<String>(httpHeaders);
            reportsByMonth = reportsByMonthTemplate.exchange("https://api-aus.myconnectwise.net/v2018_6/apis/3.0/service/tickets/?conditions=company/name=\"The Wilderness Society\" and status/name=\">Review Required\" and mergedParentTicket/summary=null and dateEntered>[2018-10-01] and dateEntered<[2018-10-20]&pageSize=1000&page=1&page=2&page=3&orderBy=id desc", HttpMethod.GET, requestReportsByMonth, String.class);
        }catch (Exception e){
            print(e.getMessage());
        }
        return reportsByMonth;
    }
}
