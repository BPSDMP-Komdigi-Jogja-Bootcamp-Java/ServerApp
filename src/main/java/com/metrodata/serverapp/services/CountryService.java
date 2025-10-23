package com.metrodata.serverapp.services;

import com.metrodata.serverapp.dto.request.CountryRequest;
import com.metrodata.serverapp.dto.response.CountryResponse;
import com.metrodata.serverapp.models.Country;
import com.metrodata.serverapp.models.Region;
import com.metrodata.serverapp.repositories.CountryRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CountryService {

  private CountryRepository countryRepository;
  private RegionService regionService;

  public List<Country> getAll() {
    return countryRepository.findAll();
  }

  public Country getById(Integer id) {
    return countryRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Country not found!!!"
        )
      );
  }

  // custome response getByID
  public CountryResponse getByIdCustomeResponse(Integer id) {
    Country country = countryRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found")
      );

    CountryResponse countryResponse = new CountryResponse();
    countryResponse.setCountryID(country.getId());
    countryResponse.setCountryCode(country.getCode());
    countryResponse.setCountryName(country.getName());
    countryResponse.setRegionID(country.getRegion().getId());
    countryResponse.setRegionName(country.getRegion().getNama());

    return countryResponse;
  }

  // custome by map
  public Map<String, Object> getByIdCustomeMap(Integer id) {
    Country country = countryRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found")
      );

    Map<String, Object> results = new HashMap<>();
    results.put("cId", country.getId());
    results.put("cCode", country.getCode());
    results.put("cName", country.getName());
    results.put("rId", country.getRegion().getId());
    results.put("rName", country.getRegion().getNama());

    return results;
  }

  // tanpa dto
  public Country create(Country country) {
    return countryRepository.save(country);
  }

  // menggunakan dto -> secara manual
  public Country createDTOManual(CountryRequest countryRequest) {
    // mapping : object country -> country request
    Country country = new Country();
    country.setCode(countryRequest.getCode());
    country.setName(countryRequest.getName());

    Region region = regionService.getById(countryRequest.getRegionId());
    country.setRegion(region);

    return countryRepository.save(country);
  }

  // menggunakan dto -> secara otomatis (bean utils)
  public Country createDTOOtomatis(CountryRequest countryRequest) {
    // mapping : object country -> country request
    Country country = new Country();
    BeanUtils.copyProperties(countryRequest, country);

    Region region = regionService.getById(countryRequest.getRegionId());
    country.setRegion(region);

    return countryRepository.save(country);
  }

  public Country update(Integer id, Country country) {
    getById(id); // stop -> tidak ada
    country.setId(id);
    return countryRepository.save(country);
  }

  public Country delete(Integer id) {
    Country country = getById(id);
    countryRepository.delete(country);
    return country;
  }
}
