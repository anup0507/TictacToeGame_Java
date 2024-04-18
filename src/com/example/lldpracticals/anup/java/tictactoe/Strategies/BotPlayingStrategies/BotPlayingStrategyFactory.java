package com.example.lldpracticals.anup.java.tictactoe.Strategies.BotPlayingStrategies;

import com.example.lldpracticals.anup.java.tictactoe.Model.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        switch (botDifficultyLevel) {
            case EASY:
                return new EasyBotPlayingStrategy();
            case MEDIUM:
                return new MediumBotPlayingStrategy();
            case HARD:
                return new HardBotPlayingStrategy();
            default:
                return new EasyBotPlayingStrategy();
        }
    }
}
