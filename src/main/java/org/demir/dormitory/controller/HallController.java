package org.demir.dormitory.controller;

import jakarta.validation.Valid;
import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.HallRequest;
import org.demir.dormitory.dto.request.HallUpdateRequest;
import org.demir.dormitory.dto.response.HallResponse;
import org.demir.dormitory.service.HallService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/hall")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("/oneHall")
    public ApiResponse<HallResponse> getHallById(@RequestParam Long id) {
        return new ApiResponse<>("Hall retrieved successfully.", hallService.getOneHallById(id), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<HallResponse> saveHall(@Valid @RequestBody HallRequest request) {
        return new ApiResponse<>("Hall saved successfully.", hallService.saveHall(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<HallResponse>> getAllHalls() {
        return new ApiResponse<>("Halls retrieved successfully.", hallService.getAllHalls(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ApiResponse<HallResponse> updateHall(@RequestBody @Valid HallUpdateRequest request) {
        return new ApiResponse<>("Hall updated successfully.", hallService.updateHall(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteHall(@RequestParam Long id) {
        hallService.deleteHall(id);
        return new ApiResponse<>("Hall deleted successfully.", null, HttpStatus.OK);
    }
}
