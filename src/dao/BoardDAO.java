package dao;

import java.util.*;

import dto.Board;

public class BoardDAO {
    //싱글톤
    private BoardDAO() {
    }
    private static BoardDAO instance = new BoardDAO();
    public static BoardDAO getInstance() {
        return instance;
    }
    //싱글톤 끝

    private List<Board> boards = new ArrayList<>();

    public void addBoard(Board board) {
        boards.add(board);
    }

    public List<Board> getBoards() {
        return boards;
    }

    

    
}
