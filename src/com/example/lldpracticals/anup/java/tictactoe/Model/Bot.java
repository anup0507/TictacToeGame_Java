package com.example.lldpracticals.anup.java.tictactoe.Model;

import com.example.lldpracticals.anup.java.tictactoe.Strategies.BotPlayingStrategies.BotPlayingStrategy;
import com.example.lldpracticals.anup.java.tictactoe.Strategies.BotPlayingStrategies.BotPlayingStrategyFactory;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Long id, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, PlayerType.BOT,id,symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy=new BotPlayingStrategyFactory().getBotPlayingStrategy(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    public Move makeMove(Board board){
        return this.botPlayingStrategy.makeMove(board);

    }
}
