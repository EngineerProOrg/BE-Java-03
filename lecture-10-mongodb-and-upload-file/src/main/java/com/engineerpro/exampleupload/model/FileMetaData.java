package com.engineerpro.exampleupload.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "fileMetaData")
@Data
public class FileMetaData {
  @Id
  private String id;
  private String fileName;
  private String fileType;
  private long size;
}
