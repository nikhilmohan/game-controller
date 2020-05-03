package com.learnenglish.gamecontroller.handlers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class GameControllerException extends RuntimeException {
    private String message;
    private int status;
    public GameControllerException(int status, String message) {
        log.error("Error " + message);
        this.message = message;
        this.status = status;

    }
}
