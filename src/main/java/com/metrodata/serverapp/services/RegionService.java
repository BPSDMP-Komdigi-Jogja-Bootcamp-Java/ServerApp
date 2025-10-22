package com.metrodata.serverapp.services;

import com.metrodata.serverapp.models.Region;
import com.metrodata.serverapp.repositories.RegionRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class RegionService {

  private RegionRepository regionRepository;

  public List<Region> getAll() {
    return regionRepository.findAll();
  }

  // cara pertama
  public Optional<Region> getByIdOtional(Integer id) {
    return regionRepository.findById(id);
  }

  // cara kedua
  public Region getById(Integer id) {
    return regionRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found!!!")
      );
  }

  public Region create(Region region) {
    return regionRepository.save(region);
  }

  public Region update(Integer id, Region region) {
    getById(id); // stop -> tidak ada
    region.setId(id);
    return regionRepository.save(region);
  }

  public Region delete(Integer id) {
    Region region = getById(id);
    regionRepository.delete(region);
    return region;
  }
}
