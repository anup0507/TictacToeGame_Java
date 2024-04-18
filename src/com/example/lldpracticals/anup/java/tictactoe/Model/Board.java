package com.example.lldpracticals.anup.java.tictactoe.Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int size;
    private List<List<Cell>> board;
    public Board(int size) {
        this.size = size;
        board= new ArrayList<>();
        for(int i=0;i<size;i++){
            List<Cell> row = new ArrayList<>();
            for(int j=0;j<size;j++){
                row.add(new Cell(i,j));
            }
            board.add(row);
        }
    }

    public int getSize() {
        return size;
    }

    public void printBoard()
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                Cell cell= board.get(i).get(j);
                cell.display();
            }
            System.out.println();
        }
    }
    public void setCellinsideBoard(Cell cell)
    {
        board.get(cell.getRow()).set(cell.getCol(),cell);
    }
    public Cell getCell(int row,int col)
    {
        return board.get(row).get(col);
    }

}
