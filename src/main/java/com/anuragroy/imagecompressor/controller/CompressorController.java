package com.anuragroy.imagecompressor.controller;

import com.anuragroy.imagecompressor.dto.CountDto;
import com.tinify.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.Exception;

@RestController
@RequestMapping("/api")
@Log4j2
public class CompressorController {

    @PostMapping("/uploadImage")
    public ResponseEntity<ByteArrayResource> uploadFile(
            @RequestParam("file") MultipartFile multipartFile)
            throws IOException {
        try {
            String imageName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            log.info("Compressing: " + imageName);

            byte[] sourceData = multipartFile.getBytes();
            byte[] resultData = Tinify.fromBuffer(sourceData).toBuffer();
            final ByteArrayResource compressedImage = new ByteArrayResource(resultData);

            return ResponseEntity.ok()
                    .contentLength(compressedImage.contentLength())
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                    .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", imageName))
                    .body(compressedImage);
        } catch (final IOException e) {
            log.error("error occurred while reading file: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (AccountException | ClientException | ServerException | ConnectionException e) {
            log.error("error occurred during compression: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/compression-count")
    public ResponseEntity<?> getCompressionCount() {
        try {
            final int compressionsThisMonth = Tinify.compressionCount();
            log.info("Total Compressions: " +  compressionsThisMonth);
            return new ResponseEntity<>(new CountDto(compressionsThisMonth), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error occurred while retrieving total compressions: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
