package com.engineerpro.exampleupload.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.engineerpro.exampleupload.model.FileMetaData;
import com.engineerpro.exampleupload.service.FileStorageService;

@RestController
@RequestMapping("/api/v1/files")
public class FileUploadController {
  private final FileStorageService fileStorageService;

  public FileUploadController(FileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
    this.fileStorageService.init();
  }

  @PostMapping("/upload")
  public ResponseEntity<FileMetaData> uploadFile(@RequestParam("file") MultipartFile file) {
    FileMetaData fileMetaData = fileStorageService.store(file);
    return new ResponseEntity<>(fileMetaData, HttpStatus.OK);
  }
}
