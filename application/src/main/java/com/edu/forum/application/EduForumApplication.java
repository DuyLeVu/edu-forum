package com.edu.forum.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.edu.forum.application", "com.edu.forum.common"})
@EnableJpaRepositories(value = {"com.edu.forum.application", "com.edu.forum.common"})
@EntityScan({"com.edu.forum.application", "com.edu.forum.common"})
public class EduForumApplication {
    public static void main(String[] args) {
      try {
        SpringApplication.run(EduForumApplication.class, args);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
