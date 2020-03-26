package com.corona.spyvirus.service;

import com.corona.spyvirus.model.CoronaStatus;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class VirusData {

    //Show Infection Corona Virus Data
    //Old One, A New Repository Has Been Made, Cleaner Tables, New References
    //private static String VIRUS_DATA_URL_1 = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/archived_data/archived_time_series/time_series_2019-ncov-Confirmed.csv";

    private static String VIRUS_DATA_URL_1 = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<CoronaStatus> allStats = new ArrayList<>();
    private static HttpURLConnection connection;

    public List<CoronaStatus> getAllStats() {
        return allStats;
    }

    public void setAllStats(List<CoronaStatus> allStats) {
        this.allStats = allStats;
    }

    // Make an HTTP Call To The Url
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusConfirmedData() throws IOException {

        List<CoronaStatus> newStats = new ArrayList<>();

        try {
            URL url = new URL(VIRUS_DATA_URL_1);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            //read response
            StringBuilder content;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                content = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            //System.out.println(content.toString());

            StringReader reader = new StringReader(content.toString());

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                CoronaStatus coronaStatus = new CoronaStatus();
                coronaStatus.setState(record.get("Province/State"));
                coronaStatus.setCountry(record.get("Country/Region"));

                int latestCases = Integer.parseInt(record.get(record.size() - 1));
                int prevDayCases = Integer.parseInt(record.get(record.size() - 2));

                coronaStatus.setLatestTotalCases(latestCases);
                coronaStatus.setDiffFromPrevDayCases(latestCases - prevDayCases);

                //System.out.println(locationStats);
                newStats.add(coronaStatus);
            }
            this.allStats = newStats;

        }finally {
            connection.disconnect();
        }
    }
}
