package com.kakaopay.ecotourism.service;

import com.kakaopay.ecotourism.repository.ProgramRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("File 서비스 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class FileServiceTest {
    @Autowired
    private FileService fileService;

    @Autowired
    private ProgramRepository programRepository;

    @DisplayName("csv file을 통한 데이터 저장 테스트")
    @Test
    public void uploadFile() throws IOException {
        String fileDir = "data";
        String fileName = "2017_national park_ ecotourism_information_UTF8.csv";
        String fileFullPath = String.format("%s/%s", fileDir, fileName);
        File file = new File(fileFullPath);

        MultipartFile multipartFile = new MockMultipartFile(fileFullPath, fileName, null, new FileInputStream(file));

        fileService.uploadCsv(multipartFile);

        long count = programRepository.count();
        assertThat(count).isEqualTo(110);
    }
}
