package com.example.spannercatservice;

import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import lombok.experimental.Accessors;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@With
@Table(name = Cat.TABLE_NAME)
public class Cat extends Animal{
  public static final String TABLE_NAME = "CATS";
  private String name;
  private String lastName;
  private int age;
  private String somethingNew;
}
