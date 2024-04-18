package com.example.lldpracticals.anup.java.tictactoe.Strategies.WinningStrategies;

import com.example.lldpracticals.anup.java.tictactoe.Model.Board;
import com.example.lldpracticals.anup.java.tictactoe.Model.Move;
import com.example.lldpracticals.anup.java.tictactoe.Model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWiseWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol,Integer>> rowCounts=new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        if (rowCounts.containsKey(row)) {
            Map<Symbol, Integer> symbolCount = rowCounts.get(row);
            if (symbolCount.containsKey(symbol)) {
                int count = symbolCount.get(symbol);
                count++;
                if (count == board.getSize()) {
                    return true;
                }
                symbolCount.put(symbol, count);
            } else {
                symbolCount.put(symbol, 1);
            }
        }
        else {
            Map<Symbol, Integer> symbolCount = new HashMap<>();
            symbolCount.put(symbol, 1);
            rowCounts.put(row, symbolCount);
        }
        return false;
    }

    @Override
    public void handleUndo(Board board,Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        if (!rowCounts.containsKey(row)) {
            return;
        }
        Map<Symbol, Integer> symbolCount = rowCounts.get(row);
        if (!symbolCount.containsKey(symbol)) {
            return;
        }
        int count = symbolCount.get(symbol);
        if (count - 1 == 0) {
            symbolCount.remove(symbol);
        } else {
            count--;
            symbolCount.put(symbol, count);
        }
    }
}
