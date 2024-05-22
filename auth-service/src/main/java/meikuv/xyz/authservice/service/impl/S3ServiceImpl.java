package meikuv.xyz.authservice.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import meikuv.xyz.authservice.service.IS3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements IS3Service {
    private static final Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);

    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Override
    public void uploadFile(String keyName, MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3Client.putObject(new PutObjectRequest(bucketName, keyName, file.getInputStream(), metadata));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public String getFile(String keyName) {
        URL url = s3Client.getUrl(bucketName, keyName);
        return url.toExternalForm();
    }

    @Override
    public boolean deleteFile(String keyName) {
        try {
            s3Client.deleteObject(bucketName, keyName);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
