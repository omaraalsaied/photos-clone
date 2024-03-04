package com.oz.omar.photos.clone;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotosController {
    @GetMapping(path = "/")
    public String hello() {
        return "Hello World !";
    }
}
