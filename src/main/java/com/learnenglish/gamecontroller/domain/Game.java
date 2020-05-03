package com.learnenglish.gamecontroller.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
   @Id
   private String id;
   private String name;
   private int level;
   private String url;
   private double passScore;

   public Game(String name, int level, String url, double passScore) {
      this.name = name;
      this.level = level;
      this.url = url;
      this.passScore = passScore;
   }
}
