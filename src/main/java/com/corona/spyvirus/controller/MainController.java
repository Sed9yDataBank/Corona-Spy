package com.corona.spyvirus.controller;

import com.corona.spyvirus.model.CoronaStatus;
import com.corona.spyvirus.service.CoronaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CoronaData coronaData;

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<CoronaStatus> allStats = coronaData.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "index";
    }
}

