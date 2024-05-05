package com.example.shoop.crewControllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileForm {
    private String SKU;
    private MultipartFile uploadedFile;
}
