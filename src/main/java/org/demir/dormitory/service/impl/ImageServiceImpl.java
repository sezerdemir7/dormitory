package org.demir.dormitory.service.impl;

import org.demir.dormitory.entity.Image;
import org.demir.dormitory.repository.ImageRepository;
import org.demir.dormitory.service.IdGeneratorService;
import org.demir.dormitory.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final IdGeneratorService idGeneratorService;
    public ImageServiceImpl(ImageRepository imageRepository, IdGeneratorService idGeneratorService) {
        this.imageRepository = imageRepository;
        this.idGeneratorService = idGeneratorService;
    }

    @Override
    public Image saveImage(String base64) {
        Long id=idGeneratorService.generateNextSequenceId("image");
        Image image = new Image();
        image.setId(id);
        image.setBase64Data(base64);
        return imageRepository.save(image);
    }
}
