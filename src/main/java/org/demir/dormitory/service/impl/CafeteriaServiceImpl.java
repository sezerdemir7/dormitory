package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.CafeteriaRequest;
import org.demir.dormitory.dto.request.CafeteriaUpdateRequest;
import org.demir.dormitory.dto.response.CafeteriaResponse;
import org.demir.dormitory.entity.Cafeteria;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.CafeteriaRepository;
import org.demir.dormitory.service.CafeteriaService;
import org.demir.dormitory.service.IdGeneratorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CafeteriaServiceImpl implements CafeteriaService {

    private final CafeteriaRepository cafeteriaRepository;
    private final IdGeneratorService idGeneratorService;

    public CafeteriaServiceImpl(CafeteriaRepository cafeteriaRepository, IdGeneratorService idGeneratorService) {
        this.cafeteriaRepository = cafeteriaRepository;
        this.idGeneratorService = idGeneratorService;
    }

    @Override
    public CafeteriaResponse saveCafeteria(CafeteriaRequest request) {
        Cafeteria toSave = mapToCafeteria(request);
        Cafeteria cafeteria = cafeteriaRepository.save(toSave);
        return mapToResponse(cafeteria);
    }

    @Override
    public void deleteCafeteria(Long cafeteriaId) {
        Cafeteria cafeteria = findCafeteriaById(cafeteriaId);
        cafeteria.setDeleted(true);
        cafeteriaRepository.save(cafeteria);
    }

    @Override
    public CafeteriaResponse getCafeteriaByName(String cafeteriaName) {
        Cafeteria cafeteria = cafeteriaRepository.findByName(cafeteriaName).orElseThrow(() ->
                new NotFoundException("Cafeteria not found!."));

        return mapToResponse(cafeteria);
    }

    @Override
    public List<CafeteriaResponse> getAllCafeteria() {
        List<Cafeteria> cafeteriaList = cafeteriaRepository.findAllByIsDeletedFalse();
        return mapToResponseList(cafeteriaList);
    }

    @Override
    public CafeteriaResponse updateCafeteria(CafeteriaUpdateRequest request) {
        Cafeteria toUpdate = findCafeteriaById(request.id());
        toUpdate.setName(request.name());
        toUpdate.setLocation(request.location());
        Cafeteria cafeteria = cafeteriaRepository.save(toUpdate);
        return mapToResponse(cafeteria);
    }

    @Override
    public CafeteriaResponse getOneCafeteriaById(Long cafeteriaId) {
        Cafeteria cafeteria = findCafeteriaById(cafeteriaId);
        return mapToResponse(cafeteria);
    }

    @Override
    public Cafeteria getCafeteriaById(Long cafeteriaId) {
        return findCafeteriaById(cafeteriaId);
    }

    private Cafeteria findCafeteriaById(Long cafeteriaId) {
        return cafeteriaRepository.findById(cafeteriaId).orElseThrow(() ->
                new NotFoundException("Cafeteria not found!."));
    }

    private Cafeteria mapToCafeteria(CafeteriaRequest request) {
        Long id=idGeneratorService.generateNextSequenceId("cafeteria");
        Cafeteria cafeteria = new Cafeteria();
        cafeteria.setId(id);
        cafeteria.setName(request.name());
        cafeteria.setLocation(request.location());
        return cafeteria;
    }

    private CafeteriaResponse mapToResponse(Cafeteria cafeteria) {
        CafeteriaResponse response = new CafeteriaResponse(
                cafeteria.getId(),
                cafeteria.getName(),
                cafeteria.getLocation()
        );
        return response;
    }

    private List<CafeteriaResponse> mapToResponseList(List<Cafeteria> cafeteriaList) {
        List<CafeteriaResponse> responseList = cafeteriaList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return responseList;
    }
}
