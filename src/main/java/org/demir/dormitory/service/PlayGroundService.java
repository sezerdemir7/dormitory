package org.demir.dormitory.service;

import org.demir.dormitory.dto.request.PlayGroundRequest;
import org.demir.dormitory.dto.request.PlayGroundUpdateRequest;
import org.demir.dormitory.dto.response.PlayGroundResponse;
import org.demir.dormitory.entity.PlayGround;

import java.util.List;

public interface PlayGroundService {

    PlayGroundResponse savePlayGround(PlayGroundRequest playGroundRequest);

    void deletePlayGround(Long playGroundId);

    PlayGroundResponse getPlayGroundByName(String playGroundName);
    PlayGroundResponse getPlayGroundResponseById(Long playGroundId);

    List<PlayGroundResponse> getAllPlayGround();

    PlayGroundResponse updatePlayGround(PlayGroundUpdateRequest playGroundUpdateRequest);

    PlayGround getPlayGroundById(Long playGroundId);
    PlayGroundResponse getOnePlayGroundById(Long playGroundId);

    void save(PlayGround playGround);
    PlayGroundResponse savePlayGround(PlayGround playGround);
}
