package com.hilmiyafia.facegen.controller;

import com.hilmiyafia.facegen.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    private ImageService service;

    @Autowired
    public void setService(ImageService service) {
        this.service = service;
    }

    @RequestMapping("/image")
    public ResponseEntity<byte[]> image(@RequestParam float[] values) {
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/jpg"))
                .body(service.getImage(values));
    }
}
