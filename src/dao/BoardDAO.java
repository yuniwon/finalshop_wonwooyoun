package dao;

import java.util.*;

import controller.MallController;
import dto.Board;
import util.Util;

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
    private int page = 0;
    private int totalPage = 0;
    public void addBoard(Board board) {
        boards.add(board);
        Board.setNum(Board.getNum()+1);
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void printBoard() {
        // 게시판 출력
        // 한 페이지 당 5개씩 출력
        // 총 게시글 수 00 개
        // 현재 페이지 [ 0 / 0 ]
        // (글번호) [ 제목 : 제목 ] [ 작성자 : 작성자 ] [ 작성일 : 작성일 ] [조회수 : 조회수 ]
        // [1] 이전페이지 [2] 다음페이지 [3] 게시글보기 [4] 뒤로가기
        while(true){

        System.out.println("총 게시글 : " + boards.size() + "개");
        totalPage = boards.size()/5;
        if(boards.size()%5 != 0){
            totalPage++;
        }
        System.out.println("현재 페이지 [ " + (page+1) + " / " + totalPage + " ]");
        System.out.println("==========================================");   
        System.out.println("글번호\t제목\t작성자\t작성일\t조회수");
        System.out.println("------------------------------------------");
        for(int i=page*5; i<page*5+5; i++){
            if(i >= boards.size()){
                break;
            }
            Board board = boards.get(i);
            System.out.println( i+1 + "\t" + board.getTitle() + "\t" + board.getId() + "\t" + board.getDate() + "\t" + board.getHits());
        }
        System.out.println("==========================================");
        System.out.println("[1] 이전페이지 [2] 다음페이지 [3] 게시글보기 [4] 뒤로가기");
        int sel = Util.getValue("메뉴 입력", 1, 4);
        if(sel == 1){
            if(page > 0){
                page--;
                continue;
            }
            System.out.println("[ 첫 페이지 입니다 ]");
        }else if(sel == 2){
            if(page < totalPage-1){
                page++;
                continue;
            }
            System.out.println("[ 마지막 페이지 입니다 ]");
        }else if(sel == 3){
            int num = Util.getValue("글번호 입력", 1, boards.size());
            Board board = boards.get(num-1);
            System.out.println( (num+1) + "\t" + board.getTitle() + "\t" + board.getId() + "\t" + board.getDate() + "\t" + board.getHits());
            System.out.println("==========================================");
            System.out.println("내용 : " + board.getContents());
            board.setHits(board.getHits()+1);
            System.out.println("==========================================");
        }else if(sel == 4){
            break;
        }
        
        }
    }

    public void insertBoard() {
        // 게시글 추가
        // 제목, 내용 입력 받고
        // 글번호는 자동으로
        // 작성자는 로그인한 아이디로
        // 작성일은 현재 날짜로
        // 조회수는 0으로
        String title = Util.getValue("제목 입력");
        String contents = Util.getValue("내용 입력");
        Board board = new Board(Board.getNum(), title, MallController.getInstance().getLoginId(), Util.getDate(), contents, 0);
        addBoard(board);
        System.out.println("[ 게시글이 등록되었습니다 ]");
    }

    public void myBoard() {
        // 내 개시글 보기
        // 내가 쓴 게시글만 출력
        // (글번호) [ 제목 : 제목 ] [ 작성자 : 작성자 ] [ 작성일 : 작성일 ] [조회수 : 조회수 ]
        // [1] 게시글 삭제 [2] 뒤로가기
        System.out.println("=====[ 내 게시글 ]=====");
        System.out.println("글번호\t제목\t작성자\t작성일\t조회수");
        System.out.println("------------------------------------------");
        for(Board board : boards){
            if(board.getId().equals(MallController.getInstance().getLoginId())){
                System.out.println( board.getBoradNum() + "\t" + board.getTitle() + "\t" + board.getId() + "\t" + board.getDate() + "\t" + board.getHits());
                System.out.println("내용 : " + board.getContents());
            }
        }
        System.out.println("==========================================");
        System.out.println("[1] 게시글 삭제 [2] 뒤로가기");
        int sel = Util.getValue("메뉴 입력", 1, 2);
        if(sel == 1){
            int num = Util.getValue("글번호 입력", 1, Board.getNum());
            if(boards.get(num-1).getId().equals(MallController.getInstance().getLoginId())){
            boards.remove(num-1);
            System.out.println("[ 게시글이 삭제되었습니다 ]");
            }else{
            System.out.println("[ 본인이 작성한 게시글만 삭제할 수 있습니다 ]");
            }
            
        }else if(sel == 2){
            return;
        }

    }



    
}
