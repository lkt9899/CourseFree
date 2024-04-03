package com.a603.ofcourse.global.s3;

import com.a603.ofcourse.global.s3.exception.S3ErrorCode;
import com.a603.ofcourse.global.s3.exception.S3Exception;
import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Operations;
import io.awspring.cloud.s3.S3Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class S3Util {
    private static final String BUCKET = "lkt9899";
    private final S3Operations s3Operations;

    /**
     * S3 Image uploader
     *
     * @author 이경태
     * @since 2024.04.02
     * @param multipartFile file to upload
     * @param key key using for identify this file
     * @throws S3Exception - is not image or can not access to file
     */
    public void upload(MultipartFile multipartFile, String key) {
        // check is image
        if(isNotPicture(multipartFile))
            throw new S3Exception(S3ErrorCode.IS_NOT_IMAGE);

        // upload
        try(InputStream is = multipartFile.getInputStream()) {
            s3Operations.upload(BUCKET, key, is, ObjectMetadata.builder()
                    .contentType(multipartFile.getContentType())
                    .build());
        } catch (IOException e) {
            throw new S3Exception(S3ErrorCode.CAN_NOT_ACCESS_TO_FILE);
        }
    }

    /**
     * S3 Image downloader
     *
     * @author  이경태
     * @since   2024.04.02
     * @param key           key used as file's id
     * @return image file on S3 server
     * @throws S3Exception  throw if this file is not image type
     */
    public S3Resource download(String key) {
        S3Resource s3Resource = s3Operations.download(BUCKET, key);
        String type = s3Resource.contentType();

        if(!type.equals(MediaType.IMAGE_PNG.toString()) && type.equals(MediaType.IMAGE_JPEG.toString()))
            throw new S3Exception(S3ErrorCode.IS_NOT_IMAGE);

        return s3Resource;
    }

    /**
     * file type checker
     *
     * @author  이경태
     * @since   2024.04.02
     * @param multipartFile file to upload
     * @return return boolean value is it image
     */
    private boolean isNotPicture(MultipartFile multipartFile) {
        return !MediaType.IMAGE_PNG.toString().equals(multipartFile.getContentType())
                || !MediaType.IMAGE_JPEG.toString().equals(multipartFile.getContentType());
    }
}
