package com.learnenglish.gamecontroller.bootstrap;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameDetails {
    private String name;
    private String url;
    private double passScore;
}
