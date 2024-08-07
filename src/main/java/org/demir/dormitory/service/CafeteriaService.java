package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.CafeteriaRequest;
import org.demir.dormitory.dto.request.CafeteriaUpdateRequest;
import org.demir.dormitory.dto.response.CafeteriaResponse;
import org.demir.dormitory.entity.Cafeteria;

import java.util.List;

public interface CafeteriaService {

    CafeteriaResponse saveCafeteria(CafeteriaRequest saveCafeteriaRequest);

    void deleteCafeteria(Long cafeteriaId);

    CafeteriaResponse getCafeteriaByName(String cafeteriaName);

    List<CafeteriaResponse> getAllCafeteria();

    CafeteriaResponse updateCafeteria(CafeteriaUpdateRequest cafeteriaUpdateRequest);

    CafeteriaResponse getOneCafeteriaById(Long cafeteriaId);

    Cafeteria getCafeteriaById(Long cafeteriaId);

}
