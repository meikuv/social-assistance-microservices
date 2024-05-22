package meikuv.xyz.authservice.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

public interface IS3Service {
    void uploadFile(String keyName, MultipartFile file);

    String getFile(String keyName);

    boolean deleteFile(String keyName);
}
