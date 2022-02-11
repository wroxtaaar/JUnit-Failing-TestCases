package com.crio.WeatherApplication;

import java.util.Objects;

public class CityWoeId {

  String city;
  Integer woeId;

  public CityWoeId() {

  }

  public CityWoeId(String city, int woeid) {
    this.city = city;
    this.woeId = woeid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CityWoeId cityWoeId = (CityWoeId) o;
    return Objects.equals(city, cityWoeId.city) && Objects.equals(woeId,
        cityWoeId.woeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, woeId);
  }

  @Override
  public String toString() {
    return "CityWoeId{" +
        "city='" + city + '\'' +
        ", woeId=" + woeId +
        '}';
  }
}