package com.kakaopay.ecotourism.controller;

import com.kakaopay.ecotourism.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController("/api/v1/files")
public class FileController {
    private final static Logger logger = LoggerFactory.getLogger(FileController.class);
    private final FileService fileService;

    public FileController(final FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/csv")
    public ResponseEntity uploadCsv(@RequestParam("file") MultipartFile file) {
        fileService.uploadCsv(file);
        return ResponseEntity.ok().build();
    }
}
