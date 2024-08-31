package org.demir.dormitory.service.impl;

import org.demir.dormitory.dto.request.PlayGroundRequest;
import org.demir.dormitory.dto.request.PlayGroundUpdateRequest;
import org.demir.dormitory.dto.response.PlayGroundResponse;
import org.demir.dormitory.entity.PlayGround;
import org.demir.dormitory.exception.NotFoundException;
import org.demir.dormitory.repository.PlayGroundRepository;
import org.demir.dormitory.service.IdGeneratorService;
import org.demir.dormitory.service.PlayGroundService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayGroundServiceImpl implements PlayGroundService {

    private final PlayGroundRepository playGroundRepository;
    private final IdGeneratorService idGeneratorService;

    public PlayGroundServiceImpl(PlayGroundRepository playGroundRepository, IdGeneratorService idGeneratorService) {
        this.playGroundRepository = playGroundRepository;
        this.idGeneratorService = idGeneratorService;
    }

    @Override
    public PlayGroundResponse savePlayGround(PlayGroundRequest request) {
        PlayGround toSave = mapToPlayGround(request);
        PlayGround playGround = playGroundRepository.save(toSave);
        return mapToResponse(playGround);
    }

    @Override
    public void deletePlayGround(Long playGroundId) {
        PlayGround playGround = findPlayGroundById(playGroundId);
        playGround.setDeleted(true);
        playGroundRepository.save(playGround);
    }

    @Override
    public PlayGroundResponse getPlayGroundByName(String playGroundName) {
        PlayGround playGround = playGroundRepository.findByName(playGroundName)
                .orElseThrow(() -> new NotFoundException("PlayGround not found!"));
        return mapToResponse(playGround);
    }

    @Override
    public PlayGroundResponse getPlayGroundResponseById(Long playGroundId) {
        PlayGround playGround=findPlayGroundById(playGroundId);
        return mapToResponse(playGround);
    }

    @Override
    @Transactional
    public List<PlayGroundResponse> getAllPlayGround() {
        List<PlayGround> playGroundList = playGroundRepository.findAllByIsDeletedFalse();
        return mapToResponseList(playGroundList);
    }

    @Override
    public PlayGroundResponse updatePlayGround(PlayGroundUpdateRequest request) {
        PlayGround toUpdate = findPlayGroundById(request.id());
        toUpdate.setName(request.name());
        toUpdate.setType(request.type());
        PlayGround playGround = playGroundRepository.save(toUpdate);
        return mapToResponse(playGround);
    }

    @Override
    public PlayGround getPlayGroundById(Long playGroundId) {
        return findPlayGroundById(playGroundId);
    }

    @Override
    public PlayGroundResponse getOnePlayGroundById(Long playGroundId) {
        PlayGround  playGround=findPlayGroundById(playGroundId);
        return mapToResponse(playGround);
    }

    @Override
    public void save(PlayGround playGround) {
        playGroundRepository.save(playGround);
    }

    @Override
    public PlayGroundResponse savePlayGround(PlayGround playGround) {
        return mapToResponse(playGroundRepository.save(playGround));
    }

    private PlayGround findPlayGroundById(Long playGroundId) {
        return playGroundRepository.findById(playGroundId)
                .orElseThrow(() -> new NotFoundException("PlayGround not found!"));
    }

    private PlayGround mapToPlayGround(PlayGroundRequest request) {
        Long id=idGeneratorService.generateNextSequenceId("playground");
        PlayGround playGround = new PlayGround();
        playGround.setId(id);
        playGround.setName(request.name());
        playGround.setType(request.type());
        return playGround;
    }

    private PlayGroundResponse mapToResponse(PlayGround playGround) {
        return new PlayGroundResponse(
                playGround.getId(),
                playGround.getName(),
                playGround.getType(),
                playGround.isAvailable()
        );
    }

    private List<PlayGroundResponse> mapToResponseList(List<PlayGround> playGroundList) {
        return playGroundList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
