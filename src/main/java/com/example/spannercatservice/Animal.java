package com.example.spannercatservice;

import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.Interleaved;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.spanner.v1.TypeCode;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@With
public class Animal {
  /** Internal identifier for an artifact. */
  @PrimaryKey
  private String id;
  /** Name used to display this artifact on the UI. */
  private String displayName;
  /** When the artifact was imported. */
  @CreatedDate
  private Instant createTime;
  /** Metadata for the artifact. */
  @Column(spannerType = TypeCode.JSON)
  private CatMetadata metadata;
  /** List of labels for this artifact. */
  @Interleaved
  private List<Flea> fleas;
}
