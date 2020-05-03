package com.learnenglish.gamecontroller.repositories;

import com.learnenglish.gamecontroller.domain.PlayerContext;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PlayerContextRepository extends ReactiveMongoRepository<PlayerContext, String> {
    public Mono<PlayerContext> findOneByPlayerId(String playerId);
}
