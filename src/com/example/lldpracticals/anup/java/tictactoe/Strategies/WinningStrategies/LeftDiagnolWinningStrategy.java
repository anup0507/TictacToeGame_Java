package com.example.lldpracticals.anup.java.tictactoe.Strategies.WinningStrategies;

import com.example.lldpracticals.anup.java.tictactoe.Model.Board;
import com.example.lldpracticals.anup.java.tictactoe.Model.Move;
import com.example.lldpracticals.anup.java.tictactoe.Model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class LeftDiagnolWinningStrategy implements WinningStrategy{
    private Map<Symbol,Integer> leftDiagnolCounts=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col=move.getCell().getCol();
        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getSymbol();
        if(col==row){
            if(leftDiagnolCounts.containsKey(symbol)){
                int count=leftDiagnolCounts.get(symbol);
                count++;
                if(count==board.getSize()){
                    return true;
                }
                leftDiagnolCounts.put(symbol,count);
            }else{
                leftDiagnolCounts.put(symbol,1);
            }
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {

        int col=move.getCell().getCol();
        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getSymbol();
        if(col==row){

            if(!leftDiagnolCounts.containsKey(symbol)){
                return;
            }
            int count=leftDiagnolCounts.get(symbol);


            if(count-1==0) {
                leftDiagnolCounts.remove(symbol);
            }
            else{
                count--;
                leftDiagnolCounts.put(symbol,count);
            }
        }

    }
}
