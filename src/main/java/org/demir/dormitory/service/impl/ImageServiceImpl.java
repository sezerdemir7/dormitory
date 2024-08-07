package org.demir.dormitory.service.impl;

import org.demir.dormitory.entity.Image;
import org.demir.dormitory.repository.ImageRepository;
import org.demir.dormitory.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(String base64) {
        Image image = new Image();
        image.setBase64Data(base64);
        return imageRepository.save(image);
    }
}
