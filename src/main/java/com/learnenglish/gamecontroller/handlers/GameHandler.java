package com.learnenglish.gamecontroller.handlers;

import com.learnenglish.gamecontroller.bootstrap.GameMap;
import com.learnenglish.gamecontroller.domain.Game;
import com.learnenglish.gamecontroller.domain.GameControllerResponse;
import com.learnenglish.gamecontroller.domain.PlayerContext;
import com.learnenglish.gamecontroller.repositories.GameRepository;
import com.learnenglish.gamecontroller.repositories.PlayerContextRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Slf4j
public class GameHandler {

    GameRepository gameRepository;
    PlayerContextRepository playerContextRepository;
    Validator validator;
    GameMap gameMap;

    public GameHandler(GameRepository gameRepository, PlayerContextRepository playerContextRepository,
                       Validator validator, GameMap gameMap) {
        this.gameRepository = gameRepository;
        this.playerContextRepository = playerContextRepository;
        this.validator = validator;
        this.gameMap = gameMap;
    }

    public Mono<ServerResponse> getNewGame(ServerRequest serverRequest) {
        log.info("Receiving request");
        String playerId = serverRequest.pathVariable("playerId");
        GameControllerResponse gameControllerResponse = new GameControllerResponse();

        return playerContextRepository.findOneByPlayerId(playerId)
                .switchIfEmpty(Mono.just(new PlayerContext(playerId, 0)))
                .flatMap(playerContext -> {
                    if (playerContext.getGameLevel() != 0) {
                        throw new GameControllerException(409, "Game state conflict!");
                    }
                    log.info("Setting playerContext");
                    playerContext.setGameLevel(1);
                    return playerContextRepository.save(playerContext).log()
                            .flatMap(playerContext1 -> {
                                gameControllerResponse.setPlayerId(playerContext1.getPlayerId());
                                gameControllerResponse.setGameName(gameMap.getGameDetailsMap().get(1).getName());
                                log.info("Setting gameControllerResponse");
                                return Mono.just(gameControllerResponse);
                            }).flatMap(response ->
                                    ServerResponse.seeOther(URI.create(gameMap.getGameDetailsMap().get(1).getUrl()))
                                            .body(fromValue(gameControllerResponse))
                            );

                });
    }

}

