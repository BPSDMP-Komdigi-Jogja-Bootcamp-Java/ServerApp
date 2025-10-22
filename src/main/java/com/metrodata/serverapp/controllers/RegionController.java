package com.metrodata.serverapp.controllers;

import com.metrodata.serverapp.models.Region;
import com.metrodata.serverapp.services.RegionService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller // output => view -> html (fe)
@RestController // output => endpoint/API -> json (be)
@AllArgsConstructor
@RequestMapping("/api/region") // routing
public class RegionController {

  private RegionService regionService;

  @GetMapping
  public List<Region> getAll() {
    return regionService.getAll();
  }

  /**
   * Get By Id:
   *  1. path variable => api/region/id
   *  2. path param    => api/search?region=id
   */
  @GetMapping("/optional/{id}")
  public Optional<Region> getByIdOtional(@PathVariable Integer id) {
    return regionService.getByIdOtional(id);
  }

  @GetMapping("/{id}")
  public Region getById(@PathVariable Integer id) {
    return regionService.getById(id);
  }

  @PostMapping
  public Region create(@RequestBody Region region) {
    return regionService.create(region);
  }

  @PutMapping("/{id}")
  public Region update(@PathVariable Integer id, @RequestBody Region region) {
    return regionService.update(id, region);
  }

  @DeleteMapping("/{id}")
  public Region delete(@PathVariable Integer id) {
    return regionService.delete(id);
  }
}
