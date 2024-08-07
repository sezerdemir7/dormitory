package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.GardenRequest;
import org.demir.dormitory.dto.request.GardenUpdateRequest;
import org.demir.dormitory.dto.response.GardenResponse;
import org.demir.dormitory.entity.Garden;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.GardenRepository;
import org.demir.dormitory.service.GardenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GardenServiceImpl implements GardenService {
    private final GardenRepository gardenRepository;

    public GardenServiceImpl(GardenRepository gardenRepository) {
        this.gardenRepository = gardenRepository;
    }

    @Override
    public GardenResponse saveGarden(GardenRequest request) {
        Garden toSave=mapToGarden(request);
        Garden garden=gardenRepository.save(toSave);
        return mapToResponse(garden);
    }


    @Override
    public void deleteGarden(Long gardenId) {
        Garden garden= findGardenById(gardenId);
        garden.setDeleted(true);
        gardenRepository.save(garden);
    }



    @Override
    public GardenResponse getGardenByName(String gardenName) {
        Garden garden=gardenRepository.findByName(gardenName).orElseThrow(
                ()->new NotFoundException("Garden not found!."));
        return mapToResponse(garden);
    }

    @Override
    public List<GardenResponse> getAllGarden() {
        List<Garden> gardenList=gardenRepository.findAllByIsDeletedFalse();
        return mapToResponseList(gardenList);
    }

    @Override
    public GardenResponse updateGarden(GardenUpdateRequest gardenUpdateRequest) {
        return null;
    }

    @Override
    public GardenResponse getOneGardenById(Long gardenId) {
        Garden garden=findGardenById(gardenId);
        return mapToResponse(garden);
    }

    @Override
    public Garden getGardenById(Long gardenId) {
        return findGardenById(gardenId);
    }
    private Garden findGardenById(Long gardenId) {
        return gardenRepository.findById(gardenId).orElseThrow(
                () -> new NotFoundException("Garden not found!."));
    }

    private Garden mapToGarden(GardenRequest request) {
        Garden garden=new Garden();
        garden.setName(request.name());
        garden.setLocation(request.location());
        return garden;

    }
    private List<GardenResponse> mapToResponseList(List<Garden> gardenList){
        List<GardenResponse>  responseList=gardenList.stream().
                map(this::mapToResponse).collect(Collectors.toList());
        return responseList;
    }

    private GardenResponse mapToResponse(Garden garden){
        GardenResponse response=
                new GardenResponse(
                        garden.getId(),
                        garden.getName(),
                        garden.getLocation(),
                        garden.isAvailable());
        return response;
    }
}
