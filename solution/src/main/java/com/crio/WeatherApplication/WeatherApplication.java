package com.crio.WeatherApplication;

import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherApplication {

  Logger logger = LoggerFactory.getLogger(WeatherApplication.class);

  // WeatherApplication prints the city name and
  // whether rain is expected in the next few days or not.
  // Input: city prefix from command line argument
  // Output: Multiple lines of City Details <City Name, City Woid>

  // Sample run
  // ./gradlew bootrun
  // Sample output
  //  City Details - City Name :Brisbane, City WoeId :1100660
  //  City Details - City Name :Bangkok, City WoeId :1225447
  //  City Details - City Name :Bangalore, City WoeId :2295421
  //  City Details - City Name :Durban, City WoeId :1580914

  public static void main(String[] args) throws IOException {
    ForecastFetcher forecastFetcher = new ForecastFetcher();
    String cityWoeIds = forecastFetcher.fetchWoeIds("Ban");
    ArrayList<CityWoeId> cityWoeIdList = forecastFetcher.getWoeIdsFromJson(cityWoeIds);
    for(CityWoeId cityWoeId: cityWoeIdList) {
      System.out.println("City Details - " + "City Name :"  + cityWoeId.city
          + ", City WoeId :" + cityWoeId.woeId);
    }
  }
}
