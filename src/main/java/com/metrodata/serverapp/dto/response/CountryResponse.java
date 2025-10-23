package com.metrodata.serverapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {

  private Integer countryID;
  private String countryCode;
  private String countryName;
  private Integer regionID;
  private String regionName;
}
