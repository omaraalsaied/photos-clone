package com.oz.omar.photos.clone;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadedController {

    private final PhotosService photosService;

    public DownloadedController(PhotosService photosService) {
        this.photosService = photosService;
    }


    @GetMapping(path="download/{id}")

    public ResponseEntity<byte[]> download(@PathVariable String id) {

        Photo photo = photosService.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);


        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(photo.getFileName())
                .build());

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
