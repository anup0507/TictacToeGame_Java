package com.example.lldpracticals.anup.java.tictactoe.Strategies.WinningStrategies;

import com.example.lldpracticals.anup.java.tictactoe.Model.Board;
import com.example.lldpracticals.anup.java.tictactoe.Model.Move;
import com.example.lldpracticals.anup.java.tictactoe.Model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWiseWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol,Integer>> counts=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col=move.getCell().getCol();
        Symbol symbol=move.getPlayer().getSymbol();
        if(counts.containsKey(col)){
            Map<Symbol,Integer> symbolCount=counts.get(col);
            if(symbolCount.containsKey(symbol)){
                int count=symbolCount.get(symbol);
                count++;
                if(count==board.getSize()){
                    return true;
                }
                symbolCount.put(symbol,count);
            }else{
                symbolCount.put(symbol,1);
            }
        }else{
            Map<Symbol,Integer> symbolCount=new HashMap<>();
            symbolCount.put(symbol,1);
            counts.put(col,symbolCount);
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col=move.getCell().getCol();
        Symbol symbol=move.getPlayer().getSymbol();
        if(!counts.containsKey(col)){
            return;
        }
        Map<Symbol,Integer> symbolCount=counts.get(col);
        if(!symbolCount.containsKey(symbol)){
            return;
        }
        int count=symbolCount.get(symbol);
        if(count-1==0) {
            symbolCount.remove(symbol);
        }
        else{
                count--;
                symbolCount.put(symbol,count);
        }
    }
}
