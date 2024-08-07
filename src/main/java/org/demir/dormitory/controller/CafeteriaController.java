package org.demir.dormitory.controller;

import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.dto.request.CafeteriaRequest;
import org.demir.dormitory.dto.request.CafeteriaUpdateRequest;
import org.demir.dormitory.dto.response.CafeteriaResponse;
import org.demir.dormitory.service.CafeteriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/cafeteria")
public class CafeteriaController {

    private final CafeteriaService cafeteriaService;

    public CafeteriaController(CafeteriaService cafeteriaService) {
        this.cafeteriaService = cafeteriaService;
    }

    @GetMapping("/oneCafeteria")
    public ApiResponse<CafeteriaResponse> getCafeteriaById(@RequestParam Long id) {
        return new ApiResponse<>("Cafeteria retrieved successfully.", cafeteriaService.getOneCafeteriaById(id), HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ApiResponse<CafeteriaResponse> saveCafeteria(@RequestBody CafeteriaRequest request) {
        return new ApiResponse<>("Cafeteria saved successfully.", cafeteriaService.saveCafeteria(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ApiResponse<List<CafeteriaResponse>> getAllCafeterias() {
        return new ApiResponse<>("Cafeterias retrieved successfully.", cafeteriaService.getAllCafeteria(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ApiResponse<CafeteriaResponse> updateCafeteria(@RequestBody CafeteriaUpdateRequest request) {
        return new ApiResponse<>("Cafeteria updated successfully.", cafeteriaService.updateCafeteria(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteCafeteria(@RequestParam Long id) {
        cafeteriaService.deleteCafeteria(id);
        return new ApiResponse<>("Cafeteria deleted successfully.", null, HttpStatus.OK);
    }
}
