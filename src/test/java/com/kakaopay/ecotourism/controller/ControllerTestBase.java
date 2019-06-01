package com.kakaopay.ecotourism.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.ecotourism.service.FileService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Disabled
public class ControllerTestBase {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private FileService fileService;

    protected void initData() throws IOException {
        String fileDir = "data.sql";
        String fileName = "2017_national park_ ecotourism_information_UTF8.csv";
        String fileFullPath = String.format("%s/%s", fileDir, fileName);
        File file = new File(fileFullPath);

        MultipartFile multipartFile = new MockMultipartFile(fileFullPath, fileName, null, new FileInputStream(file));

        fileService.uploadCsv(multipartFile);
    }
}
