package org.demir.dormitory.controller;

import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.GardenRequest;
import org.demir.dormitory.dto.request.GardenUpdateRequest;
import org.demir.dormitory.dto.response.GardenResponse;
import org.demir.dormitory.service.GardenService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/garden")
public class GardenController {

    private final GardenService gardenService;

    public GardenController(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @GetMapping("/oneGarden")
    public ApiResponse<GardenResponse> getGardenById(@RequestParam Long id) {
        return new ApiResponse<>("Garden retrieved successfully.", gardenService.getOneGardenById(id), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<GardenResponse> saveGarden(@RequestBody GardenRequest request) {
        return new ApiResponse<>("Garden saved successfully.", gardenService.saveGarden(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<GardenResponse>> getAllGardens() {
        return new ApiResponse<>("Gardens retrieved successfully.", gardenService.getAllGarden(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ApiResponse<GardenResponse> updateGarden(@RequestBody GardenUpdateRequest request) {
        return new ApiResponse<>("Garden updated successfully.", gardenService.updateGarden(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteGarden(@RequestParam Long id) {
        gardenService.deleteGarden(id);
        return new ApiResponse<>("Garden deleted successfully.", null, HttpStatus.OK);
    }
}
