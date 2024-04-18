package com.example.lldpracticals.anup.java.tictactoe.Strategies.BotPlayingStrategies;

import com.example.lldpracticals.anup.java.tictactoe.Model.Board;
import com.example.lldpracticals.anup.java.tictactoe.Model.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
