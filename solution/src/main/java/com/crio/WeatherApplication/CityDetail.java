package com.crio.WeatherApplication;

public class CityDetail {

  public String title;
  public String location_type;
  public int woeid;
  public String latt_long;

  public CityDetail() {

  }

  public CityDetail(String title, String location_type, int woeid, String latt_long) {
    this.title = title;
    this.location_type = location_type;
    this.woeid = woeid;
    this.latt_long = latt_long;
  }

}

