package com.example.spannercatservice;

import com.google.cloud.spring.data.spanner.core.admin.SpannerDatabaseAdminTemplate;
import com.google.cloud.spring.data.spanner.core.admin.SpannerSchemaUtils;
import com.google.cloud.spring.data.spanner.repository.config.EnableSpannerRepositories;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
@Slf4j
@EnableSpannerRepositories
public class SpannerCatServiceApplication {

  private final SpannerSchemaUtils spannerSchemaUtils;
  private final SpannerDatabaseAdminTemplate spannerDatabaseAdminTemplate;

  private final CatRepository catRepository;

  @GetMapping("/api/v1/test")
  public Iterable<Cat> testFetching(){
    String newIdentifier = UUID.randomUUID().toString();
    Cat cat = new Cat();
    cat.setId(newIdentifier);
    cat.setName("bob");
    cat.setLastName("ross");
    cat.setAge(2);
    cat.setSomethingNew("somethign");
    cat.setMetadata(new CatMetadata().setSomeMetadata("Some interesting metadata 2"));
    Flea f1 = new Flea().setCatId(cat.getId()).setName("flea1");
    Flea f2 = new Flea().setCatId(cat.getId()).setName("flea2");
    Flea f3 = new Flea().setCatId(cat.getId()).setName("flea3");
    cat.setFleas(List.of(f1, f2, f3));
    catRepository.save(cat);
    Iterable<Cat> foundCats = catRepository.findAll();
    return foundCats;
  }

  @GetMapping("/api/v1/testcats")
  public List<CatProjection> getCatProjections(){
    return catRepository.findDistinctCatsByNameAndLastName("bob");
  }

  @EventListener(ApplicationReadyEvent.class)
  @Profile("!test")
  public void setUpDatabase() {
    createTablesIfNotExists();
  }

  /**
   * Creates the tables in our database if they don't already exist.
   */
  private void createTablesIfNotExists() {
    if (!this.spannerDatabaseAdminTemplate.tableExists(Cat.TABLE_NAME)) {
      log.info("{} table did not exist. Will create it...", Cat.TABLE_NAME);
      this.spannerDatabaseAdminTemplate.executeDdlStrings(
          List.of(this.spannerSchemaUtils.getCreateTableDdlString(Cat.class)), true);
    }
    if (!this.spannerDatabaseAdminTemplate.tableExists(Flea.TABLE_NAME)) {
      log.info("{} table did not exist. Will create it...", Flea.TABLE_NAME);
      this.spannerDatabaseAdminTemplate.executeDdlStrings(
          List.of(this.spannerSchemaUtils.getCreateTableDdlString(Flea.class)), true);
    }
    log.info("Done.");
  }

  public static void main(String[] args) {
    SpringApplication.run(SpannerCatServiceApplication.class, args);
  }

}
