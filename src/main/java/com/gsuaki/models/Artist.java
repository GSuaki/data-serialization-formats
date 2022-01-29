package com.gsuaki.models;

import java.util.List;

public class Artist {

  private String artistName;
  private Integer cache;
  private List<String> cities;

  public Artist() {
  }

  public Artist(String artistName, Integer cache, List<String> cities) {
    this.artistName = artistName;
    this.cache = cache;
    this.cities = cities;
  }

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public Integer getCache() {
    return cache;
  }

  public void setCache(Integer cache) {
    this.cache = cache;
  }

  public List<String> getCities() {
    return cities;
  }

  public void setCities(List<String> cities) {
    this.cities = cities;
  }
}
