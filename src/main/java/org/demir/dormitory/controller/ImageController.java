package org.demir.dormitory.controller;

import org.demir.dormitory.common.ApiResponse;
import org.demir.dormitory.entity.Image;
import org.demir.dormitory.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/image")
public class ImageController {


    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ApiResponse<Image> uploadImage(@RequestBody String base64Image) {
        Image image = imageService.saveImage(base64Image);
        return new ApiResponse("İmage upload successfully.",image, HttpStatus.CREATED);
    }
}
