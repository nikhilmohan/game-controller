package com.learnenglish.gamecontroller.config;

import com.learnenglish.gamecontroller.bootstrap.DataInitializer;
import com.learnenglish.gamecontroller.bootstrap.GameMap;
import com.learnenglish.gamecontroller.handlers.GameHandler;
import com.learnenglish.gamecontroller.repositories.GameRepository;
import com.learnenglish.gamecontroller.repositories.PlayerContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.validation.Validator;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class AppConfig {
    @Bean
    public RouterFunction<ServerResponse> itemRoutes(GameHandler gameHandler) {
        return RouterFunctions.route(GET("/game-controller/api/new-game/{playerId}").and(accept(MediaType.APPLICATION_JSON))
                , gameHandler::getNewGame);
    }

    @Bean
    public GameHandler gameHandler(GameRepository gameRepository, PlayerContextRepository playerContextRepository,
                                   Validator validator, GameMap gameMap) {
        return new GameHandler(gameRepository, playerContextRepository, validator, gameMap);
    }

    @Bean
    public GameMap gameMap()    {
        return new GameMap();
    }

    @Bean
    public DataInitializer dataInitializer(GameRepository gameRepository, GameMap gameMap)  {
        return new DataInitializer(gameRepository, gameMap);
    }

}
