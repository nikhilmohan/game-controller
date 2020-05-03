package com.learnenglish.gamecontroller.bootstrap;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class GameMap {
    private Map<Integer, GameDetails> gameDetailsMap = new HashMap<Integer, GameDetails>();
}
