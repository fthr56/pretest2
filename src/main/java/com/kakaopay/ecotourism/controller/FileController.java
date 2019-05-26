package com.kakaopay.ecotourism.controller;

import com.kakaopay.ecotourism.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/api/v1/files")
@AllArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/csv")
    public ResponseEntity uploadCsv(@RequestParam("files") MultipartFile multipartFile){
        fileService.uploadCsv(multipartFile);

        return ResponseEntity.ok().build();
    }
}
