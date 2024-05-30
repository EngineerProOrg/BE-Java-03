package com.engineerpro.exampleupload.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
  @Id
  public String id;
  public String firstName;
  public String lastName;
  public String profileImgUrl;
}
