package com.engineerpro.exampleupload.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.engineerpro.exampleupload.model.FileMetaData;

@Repository
public interface FileMetaDataRepository extends MongoRepository<FileMetaData, String> {
}
