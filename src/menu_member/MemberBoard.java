package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class MemberBoard implements MenuCommand{
	private MallController cont;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		cont = MallController.getInstance();
		System.out.println("=====[ 게시판 ]=====");
		System.out.println("[1] 게시물보기\n[2] 게시글추가\n[3] 내 개시글(삭제)\n[4] 뒤로가기\n[0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		int sel = Util.getValue("메뉴 입력", 0, 4);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}else if(sel == 1){
			// 게시물 보기
			BoardDAO.getInstance().printBoard();
		}else if(sel == 2){
			BoardDAO.getInstance().insertBoard();
		}else if(sel == 3){
			BoardDAO.getInstance().myBoard();
		}else if(sel == 4){
			cont.setNext("MemberMain");
		}

		return false;
	}

}
