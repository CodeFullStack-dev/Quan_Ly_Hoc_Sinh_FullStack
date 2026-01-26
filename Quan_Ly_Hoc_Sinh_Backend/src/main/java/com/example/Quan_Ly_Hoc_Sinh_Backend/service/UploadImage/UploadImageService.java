package com.example.Quan_Ly_Hoc_Sinh_Backend.service.UploadImage;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageService {
    String uploadImage(MultipartFile multipartFilefile, String name);
    void deleteImage(String photoUrl);
}
