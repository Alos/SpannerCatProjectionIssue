package com.example.spannercatservice;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CatMetadata {
  private String someMetadata;
}
