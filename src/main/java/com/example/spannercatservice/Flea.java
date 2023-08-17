package com.example.spannercatservice;

import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = Flea.TABLE_NAME)
public class Flea {
  public static final String TABLE_NAME = "FLEAS";

  @PrimaryKey(keyOrder = 2) private String name;

  @PrimaryKey(keyOrder = 1)
  @Column(name = "id")
  private String catId;
}
