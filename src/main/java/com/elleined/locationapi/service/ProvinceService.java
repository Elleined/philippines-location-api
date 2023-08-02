package com.elleined.locationapi.service;

import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    Province save(String name, int regionId) {
        Province province = Province.provinceBuilder()
                .locationName(name)
                .regionId(regionId)
                .cities(new HashSet<>())
                .build();

        provinceRepository.save(province);
        log.debug("Province with id of {} and with name of {} saved successfully", province.getId(), name);
        return province;
    }

    Province getById(int id) throws ResourceNotFoundException {
        return provinceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Province with id of " + id + " does not exists"));
    }

    List<Province> getAllByRegionId(int regionId) {
        return provinceRepository.findAll().stream()
                .filter(province -> province.getRegionId() == regionId)
                .toList();
    }

    List<Province> getAll() {
        return provinceRepository.findAll();
    }

    void delete(Province province) {
        int id = province.getId();
        provinceRepository.delete(province);
        log.debug("Province with id of {} deleted successfully!", id);
    }

    void delete(int id) {
        provinceRepository.deleteById(id);
        log.debug("Province with id of {} deleted successfully!", id);
    }

    void update(Province province, String name, int regionId) {
        province.setLocationName(name);
        province.setRegionId(regionId);

        provinceRepository.save(province);
        log.debug("Province with id of {} updated with new name of {} and new region id of {}", province.getId(), name, regionId);
    }

    public int getCityCount(Province province) {
        return province.getCities().size();
    }

    public int getBaranggayCount(Province province) {
        return (int) province.getCities().stream()
                .map(City::getBaranggays)
                .count();

    }
}