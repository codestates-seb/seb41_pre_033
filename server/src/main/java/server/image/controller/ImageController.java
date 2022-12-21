package server.image.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.image.dto.ImageDto;
import server.image.mapper.ImageMapper;
import server.image.service.ImageService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/images")
public class ImageController {
    private ImageService imageService;
    private ImageMapper imageMapper;

    public ImageController(ImageService imageService, ImageMapper imageMapper) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    @PostMapping
    public ResponseEntity postImage(@Valid @RequestBody ImageDto.Post requestBody){
        return null;
    }

    @PatchMapping("/{image-id}")
    public ResponseEntity patchImage(@PathVariable("image-id") @Positive long imageId,
                                      @Valid @RequestBody ImageDto.Patch requestBody){
        return null;
    }

    @GetMapping("/{image-id}")
    public ResponseEntity getImage(@PathVariable("image-id") @Positive long imageId){
        return null;
    }

    @GetMapping
    public ResponseEntity getImages(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size){
        return null;
    }

    @DeleteMapping("/{image-id}")
    public ResponseEntity deleteImage(@PathVariable("image-id") @Positive long imageId){
        return null;
    }
}
