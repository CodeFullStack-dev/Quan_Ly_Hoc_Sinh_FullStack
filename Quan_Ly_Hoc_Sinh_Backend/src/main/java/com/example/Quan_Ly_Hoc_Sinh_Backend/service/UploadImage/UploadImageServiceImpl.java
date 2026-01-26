package com.example.Quan_Ly_Hoc_Sinh_Backend.service.UploadImage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadImageServiceImpl implements UploadImageService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile multipartFilefile, String name) {
        String url ="";

        try {
            url = cloudinary.uploader()
                    .upload(multipartFilefile.getBytes(), Map.of("public_id", name))
                    .get("url")
                    .toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    @Override
    public void deleteImage(String photoUrl) {
        try {
            String publicId = getPublicIdImg(photoUrl);
            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap(("resource_type"), "image"));
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa ảnh ");
            e.printStackTrace();
        }
    }

    private String getPublicIdImg(String photoUrl) {
        String[] parts = photoUrl.split("/");
        String publicIdWithFormat = parts[parts.length - 1]; // Chỉ lấy phần cuối cùng của URL

        //Tách public_id và định dạng
        String[] publicAndFormat = publicIdWithFormat.split("\\.");
        return publicAndFormat[0]; // Lấy publicID
    }
}
