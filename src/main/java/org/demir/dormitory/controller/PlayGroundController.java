package org.demir.dormitory.controller;

import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.PlayGroundRequest;
import org.demir.dormitory.dto.request.PlayGroundUpdateRequest;
import org.demir.dormitory.dto.response.PlayGroundResponse;
import org.demir.dormitory.service.PlayGroundService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/playground")
public class PlayGroundController {

    private final PlayGroundService playGroundService;

    public PlayGroundController(PlayGroundService playGroundService) {
        this.playGroundService = playGroundService;
    }

    @GetMapping("/onePlayGround")
    public ApiResponse<PlayGroundResponse> getPlayGroundById(@RequestParam Long id) {
        PlayGroundResponse playGroundResponse=playGroundService.getOnePlayGroundById(id);
        return new ApiResponse<>("PlayGround retrieved successfully.", playGroundResponse, HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<PlayGroundResponse> savePlayGround(@RequestBody PlayGroundRequest request) {
        return new ApiResponse<>("PlayGround saved successfully.", playGroundService.savePlayGround(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<PlayGroundResponse>> getAllPlayGrounds() {
        return new ApiResponse<>("PlayGrounds retrieved successfully.", playGroundService.getAllPlayGround(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ApiResponse<PlayGroundResponse> updatePlayGround(@RequestBody PlayGroundUpdateRequest request) {
        return new ApiResponse<>("PlayGround updated successfully.", playGroundService.updatePlayGround(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deletePlayGround(@RequestParam Long id) {
        playGroundService.deletePlayGround(id);
        return new ApiResponse<>("PlayGround deleted successfully.", null, HttpStatus.OK);
    }
}
