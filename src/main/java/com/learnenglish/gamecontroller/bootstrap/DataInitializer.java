package com.learnenglish.gamecontroller.bootstrap;

import com.learnenglish.gamecontroller.domain.Game;
import com.learnenglish.gamecontroller.repositories.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

@Slf4j
public class DataInitializer implements CommandLineRunner {

    GameRepository gameRepository;
    GameMap gameMap;

    public DataInitializer(GameRepository gameRepository, GameMap gameMap)   {
        this.gameRepository = gameRepository;
        this.gameMap = gameMap;
    }
    @Override
    public void run(String... args) throws Exception {



        gameRepository.saveAll(Arrays.asList(new Game("basic-grammar-game", 1, "/basic-grammar/api/quiz", 100.00),
                new Game("word-finder-game", 2, "/word-finder/api/quiz", 80.00),
                new Game("word-memory-game", 3, "word-memory/api/quiz", 80.00))).blockLast();

        gameRepository.findAll()
                .subscribe(game -> {
                    gameMap.getGameDetailsMap().put(game.getLevel(), new GameDetails(game.getName(),
                             game.getUrl(), game.getPassScore()));
                },System.out::println, ()->log.info("Game map " + gameMap));



    }
}
