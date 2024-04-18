package com.example.lldpracticals.anup.java.tictactoe.Model;

import com.example.lldpracticals.anup.java.tictactoe.Exceptions.MoreThanTwoBotsException;
import com.example.lldpracticals.anup.java.tictactoe.Strategies.WinningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.example.lldpracticals.anup.java.tictactoe.Exceptions.DuplicateSymbolException;

public class Game {
    private Board board;
    private List<Player> players;
    private Player currentPlayer;
    private Player winner;
    private GameState gameState;
    private int nextPlayerMoveIndex;
    private int lastPlayerMoveIndex;
    private List<Move> moves;

    public void setLastPlayerMoveIndex(int lastPlayerMoveIndex) {
        this.lastPlayerMoveIndex = lastPlayerMoveIndex;
    }

    public int getLastPlayerMoveIndex() {
        return lastPlayerMoveIndex;
    }

    private List<WinningStrategy> winningStrategies;

    public Game(GameBuilder gameBuilder) {
        this.nextPlayerMoveIndex=0;
        this.board = gameBuilder.getBoard();
        this.players = gameBuilder.getPlayers();
        this.gameState = GameState.IN_PROGRESS;
        this.winningStrategies = gameBuilder.getWinningStrategies();
        this.moves=new ArrayList<>();
        this.lastPlayerMoveIndex=-1;
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void UndoMove(){
        if(moves.size()==0){
            System.out.println("No moves to undo");
            return;
        }
        Move lastmove=moves.get(moves.size()-1);
        moves.remove(lastmove);
        Cell cell= lastmove.getCell();
        cell.setState(CellState.EMPTY);
        cell.setPlayer(null);
        cell.setSymbol(null);
        for(WinningStrategy winningStrategy:winningStrategies)
        {
            winningStrategy.handleUndo(board,lastmove);
        }
        setLastPlayerMoveIndex(nextPlayerMoveIndex);
        nextPlayerMoveIndex=(nextPlayerMoveIndex-1+players.size())%players.size();

    }
    private boolean validateMove(Move move){
        Cell cell=move.getCell();
        if(cell.getRow()<0 || cell.getRow()>=board.getSize() || cell.getCol()<0 || cell.getCol()>=board.getSize())
            return false;
        if(board.getCell(cell.getRow(),cell.getCol()).getState()!=CellState.EMPTY)return false;
        return true;
    }
    private boolean checkWinningState(Move move){
        for(WinningStrategy winningStrategy:winningStrategies)
        {
            if(winningStrategy.checkWinner(board,move))
                return true;
        }
        return false;
    }
    public void makeMove(){
            setCurrentPlayer(players.get(nextPlayerMoveIndex));
            System.out.println("Its Player "+currentPlayer.getName()+"'s turn");
            Move move = currentPlayer.makeMove(board);
            if(!validateMove(move)) {
                System.out.println("Invalid Move.Please try again");
                return;
            }
            Cell celltoChange=board.getCell(move.getCell().getRow(),move.getCell().getCol());
            celltoChange.setState(CellState.FILLED);
            celltoChange.setPlayer(currentPlayer);
            celltoChange.setSymbol(currentPlayer.getSymbol());
            //board.setCellinsideBoard(celltoChange);
            move.setCell(celltoChange);
            move.setPlayer(currentPlayer);
            moves.add(move);
            if(checkWinningState(move))
            {
                gameState=GameState.WIN;
                winner=currentPlayer;
            }
            else if(moves.size()==board.getSize()*board.getSize())
            {
                gameState=GameState.DRAW;
            }
            setLastPlayerMoveIndex(nextPlayerMoveIndex);
            nextPlayerMoveIndex=(nextPlayerMoveIndex+1)%players.size();
    }

    public static GameBuilder createbuilder(){
        return new GameBuilder();
    }
    public static class GameBuilder{
        private Board board;
        private List<Player> players;

        public Board getBoard() {
            return board;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        private List<WinningStrategy> winningStrategies;

        public GameBuilder setBoard(Board board){
            this.board = board;
            return this;
        }
        public GameBuilder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }

        public GameBuilder setWinningStrategy(WinningStrategy winningStrategy){
            this.winningStrategies.add(winningStrategy);
            return this;
        }
        public GameBuilder setWinningStrategies(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return this;
        }
        private boolean ValidateDuplicateSymbols ()  {
            Map<Symbol,Integer> symbolCount=new HashMap<>();
            for(Player player:players){
                if(symbolCount.containsKey(player.getSymbol())){
                    return false;
                }else{
                    symbolCount.put(player.getSymbol(),1);
                }
            }
            return true;
        }
        private boolean ValidateNumberOfBotsCounts()
        {
            int botCount=0;
            for(Player player:players){
                if(player.getPlayerType()==PlayerType.BOT){
                    botCount++;
                }
            }
            return botCount>1?false:true;
        }
        public boolean validate() throws DuplicateSymbolException,MoreThanTwoBotsException {
            if(board.getSize()==0){
                throw new IllegalArgumentException("Board size cannot be 0");
            }
            if(players.size()<2){
                throw new IllegalArgumentException("Minimum 2 players required");
            }
            if(!ValidateDuplicateSymbols()){
                throw new DuplicateSymbolException();
            }
            if(!ValidateNumberOfBotsCounts())
            {
                throw  new MoreThanTwoBotsException();
            }

            return true;
        }
        public Game build() throws DuplicateSymbolException, MoreThanTwoBotsException {
            if(!validate())return null;
            return new Game(this);
        }
    }
}
