package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.CityDTO;
import com.elleined.locationapi.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final LocationService locationService;

    @PostMapping
    public CityDTO save(@Valid @RequestBody CityDTO cityDTO) {
        return locationService.saveCity(cityDTO);
    }

    @PostMapping("/saveAll")
    public Set<CityDTO> saveAll(@Valid @RequestBody Set<CityDTO> cityDTOS) {
        return locationService.saveAllCities(cityDTOS);
    }

    @GetMapping("/{id}")
    public CityDTO getById(@PathVariable("id") int id) {
        return locationService.getCityById(id);
    }

    @GetMapping("/zipCode/{zipCode}")
    public CityDTO getByZipCode(@PathVariable("zipCode") int zipCode) {
        return locationService.getCityByZipCode(zipCode);
    }

    @GetMapping("/getAllByProvince/{provinceId}")
    public List<CityDTO> getAllByProvince(@PathVariable("provinceId") int provinceId) {
        return locationService.getAllByProvince(provinceId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CityDTO> delete(@PathVariable("id") int id) {
        locationService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public CityDTO update(@PathVariable("id") int id,
                          @Valid @RequestBody CityDTO cityDTO) {

        return locationService.updateCity(id, cityDTO);
    }
}
