package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.HallRequest;
import org.demir.dormitory.dto.request.HallUpdateRequest;
import org.demir.dormitory.dto.response.HallResponse;
import org.demir.dormitory.entity.Hall;

import java.util.List;

public interface HallService {

    HallResponse saveHall(HallRequest saveHallRequest);

    void deleteHall(Long hallId);

    HallResponse getHallByName(String hallName);

    List<HallResponse> getAllHalls();

    HallResponse updateHall(HallUpdateRequest hallUpdateRequest);

    Hall getHallById(Long hallId);
    HallResponse getOneHallById(Long hallId);
}
