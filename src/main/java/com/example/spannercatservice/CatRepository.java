package com.example.spannercatservice;

import com.google.cloud.spanner.Key;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import com.google.cloud.spring.data.spanner.repository.query.Query;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends SpannerRepository<Cat, Key> {
  @Query("SELECT DISTINCT name, lastName FROM CATS WHERE name = @name")
  List<CatProjection> findDistinctCatsByNameAndLastName(String name);
}
