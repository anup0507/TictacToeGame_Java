package com.example.lldpracticals.anup.java.tictactoe.Strategies.WinningStrategies;

import com.example.lldpracticals.anup.java.tictactoe.Model.Board;
import com.example.lldpracticals.anup.java.tictactoe.Model.Move;
import com.example.lldpracticals.anup.java.tictactoe.Model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class CornersWinningStrategy implements  WinningStrategy{
    private Map<Integer, Map<Symbol,Integer>> Cornerscounts=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }

    @Override
    public void handleUndo(Board board,Move move) {


    }
}
