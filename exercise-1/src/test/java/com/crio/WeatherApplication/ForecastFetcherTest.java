package com.crio.WeatherApplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.*;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;


class ForecastFetcherTest {

  public String generateInputJson()throws IOException {

    List<CityDetail> cityDetailList = new ArrayList();

    // Initialising an array of cityDetailList.
    cityDetailList.add(new CityDetail("Brisbane", "City", 1100661, "-27.468880,153.022827"));
    cityDetailList.add(new CityDetail("Bangkok", "City", 1225448, "13.753330,100.504822"));
    cityDetailList.add(new CityDetail("Bangalore", "City", 2295420, "12.955800,77.620979"));
    cityDetailList.add(new CityDetail("Durban", "City", 1580913, "-29.855721,31.035110"));

    // Wrapping the list in the array to simulate the input obtained from URL in Forecast Fetcher class.
    List<CityDetail> input[] = new List[]{cityDetailList};

    StringBuilder jsonResponse = new StringBuilder();
    ObjectMapper objectMapper = new ObjectMapper();

    jsonResponse.append(objectMapper.writeValueAsString(input[0]));

    return jsonResponse.toString();
  }

  public ArrayList<CityWoeId> generateOutputJson(int noOfCities) {
    ArrayList<CityWoeId> output = new ArrayList<>();

    String city[] = {"Brisbane", "Bangkok", "Bangalore", "Durban"};
    int woeId[] = {1100661, 1225448, 2295420, 1580913};

    for(int i = 0; i < noOfCities; i++) {
      CityWoeId cityWoeId = new CityWoeId();
      cityWoeId.city = city[i];
      cityWoeId.woeId = woeId[i];
      output.add(cityWoeId);
    }
    return output;
  }

  @Test
  public void getWoeIdsFromJson4CitiesTest() throws IOException{
    ForecastFetcher forecastFetcher = new ForecastFetcher();
    String inputJson = generateInputJson();
    ArrayList<CityWoeId> actualOutput = forecastFetcher.getWoeIdsFromJson(inputJson);
    ArrayList<CityWoeId> expectedOutput = generateOutputJson(4);
    assertEquals(expectedOutput, actualOutput, "The actual CityWoeId List is not "
        + "matching with the expected List");
  }

  @Test
  public void getWoeIdsFromJson2CitiesTest() throws IOException {
    ForecastFetcher forecastFetcher = new ForecastFetcher();
    String inputJson = "[{\"title\":\"Brisbane\",\"location_type\":\"City\",\"woeid\":1100661,\"lat_long\":\"-27.468880,153.022827\"},"
        + "{\"tile\":\"Bangkok\",\"location_type\":\"City\",\"woeid\":1225448,\"latt_long\":\"13.753330,100.504822\"}]\n";

    ArrayList<CityWoeId> actualOutput = forecastFetcher.getWoeIdsFromJson(inputJson);
    ArrayList<CityWoeId> expectedOutput = generateOutputJson(2);
    assertEquals(expectedOutput, actualOutput, "The actual CityWoeId List is not "
        + "matching with the expected List");
  }

  @Test
  public void incorrectJsonShouldThrowException() {
    ForecastFetcher forecastFetcher = new ForecastFetcher();
    String incorrectJson = "[{\"title\":\"Brisbane\",\"location_type\":\"City\",\"woeid\":1100661,\"latt_long\":\"-27.468880,153.022827\"},"
        + "{\"title\":\"Bangkok\",\"location_type\":\"City\",\"woeid\":1225448,\"lat_long\":\"13.753330,100.504822\"},"
        + "{\"title\":\"Bangalore\",\"location_type\":\"City\",\"woeid\":2295420,\"latt_long\":\"12.955800,77.620979\"},"
        + "{\"tile\":\"Durban\",\"location_type\":\"City\",\"woeid\":1580913,\"latt_long\":\"-29.855721,31.035110\"}]\n";

    assertThrows(IllegalArgumentException.class, () -> forecastFetcher
        .getWoeIdsFromJson(incorrectJson));
  }
}


// Template :- Sample

//// Method Under Test:
//gMapService.getPriceEstimates()
//// Test case:
//Check if getPriceEstimates() is working correctly and returns a list of PriceEstimates given a valid set of start and end coordinates of a customer.
////Dependency Layer/Function:
//externalUberServiceMock.fetchEstimates()
////Mock Setup:
//Function to mock: externalUberServiceMock.fetchEstimates()
//Input to mock: (startLatitude, startLongitude, endLatitude, endLongitude) // Provide actual values if declared in unit test.
//Pre-set output from mock: <list of price estimate objects>
//// Test Input:
//gMapService.GetPriceEstimates(startLatitude, startLongitude, endLatitude, endLongitude)
//// Expected Test Output:
// list of PriceEstimates


// 1. Method Under Test:
// forecastFetcher.getWoeIdsFromJson(inputJson)
// 2. Test case
// Checks if getWoeIdsFromJson is working correctly and returns a list of CityWoeIds given a valid inputJson of 2 cities
// Dependency/Function
// None
// Mock Setup:
// NA
// Test Input:
// Input JsonString which is serialised CityDetails
// Test Output:
// List of CityWoeIds.


// 1. Method under Test
// // forecastFetcher.getWoeIdsFromJson(inputJson)
// 2. Test case
// Checking if getWoeIdsFromJson function is throwing the right error/exception when the input json is incorrect.
// Dependency/Function:
// None
// Mock Setup
// NA
// Test Input
// Incorrect Json String
// Test Output
// Exception is thrown correctly


