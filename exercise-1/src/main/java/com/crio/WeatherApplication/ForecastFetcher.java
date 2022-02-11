package com.crio.WeatherApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ForecastFetcher {

  public String fetchWoeIds(String cityPrefix) throws IOException {
    // create url
    URL url = new URL("https://www.metaweather.com/api/location/search/?query=" + cityPrefix);

    // Send Get request and fetch data
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    BufferedReader br = new BufferedReader(new InputStreamReader(
        (conn.getInputStream())));

    // Read data line-by-line from buffer & print it out
    String output;
    StringBuilder jsonResponse = new StringBuilder();
    while ((output = br.readLine()) != null) {
      jsonResponse.append(output);
    }

    conn.disconnect();
    return jsonResponse.toString();
  }

  public ArrayList<CityWoeId> getWoeIdsFromJson(String jsonString) throws IOException {
    System.out.println(jsonString);
    ObjectMapper mapper = new ObjectMapper();
    CityDetail[] citiesDetail = mapper.readValue(jsonString, CityDetail[].class);
    ArrayList<CityWoeId> cityWoeIds = new ArrayList<>();
    for (CityDetail cityDetail : citiesDetail) {
      CityWoeId cityWoeId = new CityWoeId(cityDetail.title, cityDetail.woeid);
      cityWoeIds.add(cityWoeId);
    }
    return cityWoeIds;
  }
}







