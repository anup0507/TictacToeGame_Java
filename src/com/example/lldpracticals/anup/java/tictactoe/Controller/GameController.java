package com.example.lldpracticals.anup.java.tictactoe.Controller;

import com.example.lldpracticals.anup.java.tictactoe.Exceptions.DuplicateSymbolException;
import com.example.lldpracticals.anup.java.tictactoe.Exceptions.MoreThanTwoBotsException;
import com.example.lldpracticals.anup.java.tictactoe.Model.Board;
import com.example.lldpracticals.anup.java.tictactoe.Model.Game;
import com.example.lldpracticals.anup.java.tictactoe.Model.GameState;
import com.example.lldpracticals.anup.java.tictactoe.Model.Player;
import com.example.lldpracticals.anup.java.tictactoe.Strategies.WinningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game StartGame(int dimesnsionofBoard, List<Player> players, List<WinningStrategy> winningStrategies) throws DuplicateSymbolException, MoreThanTwoBotsException {
        return Game.createbuilder()
                .setBoard(new Board(dimesnsionofBoard))
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }
    public void printBoard(Game game)
    {
        game.getBoard().printBoard();
    }
    public void UndoMove(Game game)
    {
        game.UndoMove();
    }
    public void makeMove(Game game)
    {
        game.makeMove();
    }
    public GameState checkState(Game game) {
        return game.getGameState();
    }
    public Player getWinner(Game game)
    {
        return game.getWinner();
    }
}
