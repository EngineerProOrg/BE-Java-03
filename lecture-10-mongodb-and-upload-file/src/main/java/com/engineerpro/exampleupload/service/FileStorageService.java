package com.engineerpro.exampleupload.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.engineerpro.exampleupload.model.FileMetaData;
import com.engineerpro.exampleupload.repository.FileMetaDataRepository;

@Service
public class FileStorageService {
  @Value("${file.upload-dir}")
  private String uploadDir;

  private final FileMetaDataRepository fileMetaDataRepository;

  public FileStorageService(FileMetaDataRepository fileMetaDataRepository) {
    this.fileMetaDataRepository = fileMetaDataRepository;
  }

  public void init() {
    try {
      Files.createDirectories(Paths.get(uploadDir));
    } catch (IOException e) {
      throw new RuntimeException("Could not create upload folder!", e);
    }
  }

  public FileMetaData store(MultipartFile file) {
    try {
      String fileName = file.getOriginalFilename();
      Path filePath = Paths.get(uploadDir).resolve(fileName);
      Files.copy(file.getInputStream(), filePath);

      FileMetaData fileMetaData = new FileMetaData();
      fileMetaData.setFileName(fileName);
      fileMetaData.setFileType(file.getContentType());
      fileMetaData.setSize(file.getSize());

      return fileMetaDataRepository.save(fileMetaData);
    } catch (IOException e) {
      throw new RuntimeException("Could not store file. Error: " + e.getMessage(), e);
    }
  }
}
