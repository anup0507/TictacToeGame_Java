package com.example.lldpracticals.anup.java.tictactoe.Strategies.WinningStrategies;

import com.example.lldpracticals.anup.java.tictactoe.Model.Board;
import com.example.lldpracticals.anup.java.tictactoe.Model.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
    void handleUndo(Board board, Move move);
}
