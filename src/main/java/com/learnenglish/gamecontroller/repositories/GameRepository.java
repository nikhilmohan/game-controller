package com.learnenglish.gamecontroller.repositories;

import com.learnenglish.gamecontroller.domain.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameRepository extends ReactiveMongoRepository<Game, String> {
}
