package com.metrodata.serverapp.controllers;

import com.metrodata.serverapp.dto.request.CountryRequest;
import com.metrodata.serverapp.dto.response.CountryResponse;
import com.metrodata.serverapp.models.Country;
import com.metrodata.serverapp.services.CountryService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/country")
public class CountryController {

  private CountryService countryService;

  @GetMapping
  public List<Country> getAll() {
    return countryService.getAll();
  }

  @GetMapping("/{id}")
  public Country getById(@PathVariable Integer id) {
    return countryService.getById(id);
  }

  // custome response getByID
  @GetMapping("/custom-response/{id}")
  public CountryResponse getByIdCustomeResponse(@PathVariable Integer id) {
    return countryService.getByIdCustomeResponse(id);
  }

  // custome response getByID
  @GetMapping("/custom-map/{id}")
  public Map<String, Object> getByIdCustomeMap(@PathVariable Integer id) {
    return countryService.getByIdCustomeMap(id);
  }

  // tanpa dto
  @PostMapping
  public Country create(@RequestBody Country country) {
    return countryService.create(country);
  }

  // menggunakan dto -> secara manual
  @PostMapping("/dto-manual")
  public Country craeteDTOManual(@RequestBody CountryRequest countryRequest) {
    return countryService.createDTOManual(countryRequest);
  }

  // menggunakan dto -> secara otomatis by bean utils
  @PostMapping("/dto-otomatis")
  public Country createDTOOtomatis(@RequestBody CountryRequest countryRequest) {
    return countryService.createDTOOtomatis(countryRequest);
  }

  @PutMapping("/{id}")
  public Country update(
    @PathVariable Integer id,
    @RequestBody Country country
  ) {
    return countryService.update(id, country);
  }

  @DeleteMapping("/{id}")
  public Country delete(@PathVariable Integer id) {
    return countryService.delete(id);
  }
}
