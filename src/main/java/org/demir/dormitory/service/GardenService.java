package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.GardenRequest;
import org.demir.dormitory.dto.request.GardenUpdateRequest;
import org.demir.dormitory.dto.response.GardenResponse;
import org.demir.dormitory.entity.Garden;

import java.util.List;

public interface GardenService {

    GardenResponse saveGarden(GardenRequest saveGardenRequest);

    void deleteGarden(Long gardenId);

    GardenResponse getGardenByName(String gardenName);

    List<GardenResponse> getAllGarden();

    GardenResponse updateGarden(GardenUpdateRequest gardenUpdateRequest);

    GardenResponse getOneGardenById(Long gardenId);
    Garden getGardenById(Long gardenId);
}
