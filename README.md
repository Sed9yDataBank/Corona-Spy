# Corona-Spy
> Corona Spy is for everyone, it gives you real time, automated, up to date every data about numbers of corona virus statistics.

Corona Spy is designed to reach highest scores on user experience, coming with it's light theme, eye relaxing colors, easy to read data table and high resolution images, there is no much click handlers, information you need is out there waiting for you...Also corona spy is fully responsive.
Already satisfied ? Here is more...

Corona Spy gets you integrated as an acting individual during this global crises, with our app you can send messages (sms) to any phone number in your country using our programmable sms form.
If you notice any infected suspect just type in the coordinations, state, country, specific adress, if you like and say anything about the infected suspect, when you saw him, what he was wearing with whom he was.
These informative short messages you can send to any phone number in your country range, hospitals, ministry of health, local crises combat organization, observatory of new and emerging diseases. You can even send it to one of your friends, family that live far from you to alert them about the infection status in your area.

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General info
During the Covid-19 pandemic a lot of people these days are intereseted in following the latest news of this global virus to mesaure their risks and create their own thoughts and expectations about the future of this infection.

Poeple tend to run off complicated apps with confusing colors, images, click handlers that leads to a poblem with performance and loading.
Studies rates that users only stay for five seconds after click on a link and during these quick seconds they decide to stay or leave.

Another motivation is that poeple get scared of trusting the wrong informations, some app provide unaccurate numbers about the corona virus and others may not cover all countries in the world.

The data that corona Spy uses is the data repository for the 2019 Novel Coronavirus Visual Dashboard Operated By The Johns Hopkins University Center For Systems Science and Engineering (JHU CSSE). Also, Supported By ESRI Living Atlas Team And the Johns Hopkins University Applied Physics Lab (JHU APL).

With corona Spy send messages (sms) to any phone number in your country using our programmable sms form integrated from Twilio API packages.

## Screenshots

![Home Page Data Table Screenshot](https://github.com/Sed9yDataBank/Corona-Spy/blob/master/src/main/resources/static/images/HomePage.gif)

![Report Page Screenshot](https://github.com/Sed9yDataBank/Corona-Spy/blob/master/src/main/resources/static/images/ReportPage.gif)

![SMS Page Screenshot](https://github.com/Sed9yDataBank/Corona-Spy/blob/master/src/main/resources/static/images/SMS.gif)

## Technologies
* SpringBoot 
* Twilio APIs
* Thymeleaf 
* Postman

## Setup
You can clone this repository and setup the frontend, server.

Why no live demo ? deploying this project is going to cost money for a good hosting service.

## Code Examples
```java
    //Creating The Data Table Service
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

                newStats.add(coronaStatus);
            }
            this.allStats = newStats;

        }finally {
            connection.disconnect();
        }
    }
}
```
## Features

* Corona virus statistics trusted from world wide universities and organizations.
* Send phone SMS to report about infected suspects around you in real time.

To-Do List:
* Develop automated every day phone notifications About pandemic updates.
* Add an interactive map that gives data visualization about infection levels.

## Status
Project is: _in progress_

## Inspiration
Project Inspired By All The Poeple That Have Been Waiting For An App Like This During Quarantaine 2020

## Contact
Created by [@Sed9yDataBank](https://github.com/Sed9yDataBank/) - Feel Free To Contact Me ! [benzidsedki@gmail.com]
