package org.demir.dormitory.service.impl;

import org.demir.dormitory.entity.Image;
import org.demir.dormitory.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageServiceImplTest {

    private ImageRepository imageRepository;
    private ImageServiceImpl imageService;

    @BeforeEach
    void setUp() {
        imageRepository= mock(ImageRepository.class);
        imageService = new ImageServiceImpl(imageRepository);
    }

    @Test
    void saveImage() {

        String base64="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAUDBAQEAwUEBAQFBQUGBwwIBwcHBw8LCwkMEQ8SEhEPERETFhwXExQaFRERGCEYGh0dHx8fExciJCIeJBweHx7ExHcdy3uJfAhCEJUGZ7p//Z";

        Image image = new Image();
        image.setBase64Data(base64);

        Image saved=new Image();
        saved.setId(1L);
        saved.setBase64Data(base64);

        when(imageRepository.save(image)).thenReturn(saved);

        Image result=imageService.saveImage(base64);

        assertEquals(saved.getId(),result.getId());
        assertEquals(saved.getBase64Data(),result.getBase64Data());

        verify(imageRepository).save(image);

    }
}