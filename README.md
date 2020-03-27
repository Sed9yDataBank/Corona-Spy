# Corona-Spy
> Corona Spy Is For Everyone, It Gives You Real Time, Automated, Up To Date Every Data About Numbers Of Corona Virus Statistics.

Corona Spy Is Designed To Reach Highest Scores On User Experience, Coming With It's Light Theme, Eye Relaxing Colors, Easy To Read Data Table And High Resolution Images, There Is No Much Click Handlers, Information You Need Is Out There Waiting For You...Also Corona Spy Is Fully Responsive.
Already Satisfied ? Here Is More...

Corona Spy Gets You Integrated As An Acting Individual During This Global Crises, With Our App You Can Send Messages (SMS) To Any Phone Number In Your Country Using Our Programmable SMS Form.

If You Notice Any Infected Suspect Just Type In The Coordinations, State, Country, Specific Adress, If You Like And Say Anything About The Infected Suspect, When You Saw Him, What He Was Wearing With Whom He Was.
These Informative Short Messages You Can Send To Any Phone Number In Your Country Range, Hospitals, Ministry Of Health, Local Crises Combat Organization, Observatory Of New And Emerging Diseases. You Can Even Send It To One Of Your Friends, Family That Live Far From You To Alert Them About The Infection Status In Your Area.

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
During The COVID-19 Pandemic A lot Of People These Days Are Intereseted In Following The Latest News Of This Global Virus To Mesaure Their Risks And Create Their Own Thoughts And Expectations About The Future Of This Infection.

Poeple Tend To Run Off Complicated Apps With Confusing Colors, Images, Click Handlers That Leads To A Poblem With Performance And Loading.
Studies Rates That Users Only Stay For Five Seconds After Click On A Link And During These Quick Seconds They Decide To Stay Or Leave.

Another Motivation Is That Poeple Get Scared Of Trusting The Wrong Informations, Some App Provide Unaccurate Numbers About The Corona Virus And Others May Not Cover All Countries In The World.

The Data That Corona Spy Uses Is The Data Repository For The 2019 Novel Coronavirus Visual Dashboard Operated By The Johns Hopkins University Center For Systems Science and Engineering (JHU CSSE). Also, Supported By ESRI Living Atlas Team And the Johns Hopkins University Applied Physics Lab (JHU APL).

With Corona Spy Send Messages (SMS) To Any Phone Number In Your Country Using Our Programmable SMS Form Integrated From Twilio API Packages.

## Screenshots
![Example screenshot](./img/screenshot.png)

## Technologies
* SpringBoot
* Twilio APIs
* Thymeleaf ( For Non-Technical Viewers Of This Project, HTML, CSS, JavaScript )
* Postman

## Setup
Describe how to install / setup your local environement / add link to demo version.

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
List of features ready and TODOs for future development
* Statistic Number About Corona Virus Trusted From World Wide Universities And Organizations
* Send Phone SMS To Warning About Infected Suspects Around You
* App Developed To Integrate The Best User Experience

To-Do List:
* Develop Automated Every Day Phone Notifications To Let Know About Pandemic New Updates
* Add An Interactive Map With Data About The Virus

## Status
Project is: _in progress_

## Inspiration
Project Inspired By All The Poeple That Have Been Waiting For An App Like This During Quarantaine 2020

## Contact
Created by [@Sed9yDataBank](https://github.com/Sed9yDataBank/) - Feel Free To Contact Me ! [benzidsedki@gmail.com]
