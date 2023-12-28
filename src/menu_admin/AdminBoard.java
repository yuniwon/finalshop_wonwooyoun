package menu_admin;

import _mall.MenuCommand;
import controller.MallController;

public class AdminBoard implements MenuCommand{
	private MallController cont;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		cont = MallController.getInstance();
		System.out.println("=====[ 관리자 게시판 ]=====");
		System.out.println("[1] 게시글 목록\n[2] 게시글 삭제\n[3] 뒤로가기\n[0] 종료");
		System.out.println("=====================");

	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		int sel = util.Util.getValue("메뉴 입력", 0, 3);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}else if(sel == 1){
			// 게시글 목록
			dao.BoardDAO.getInstance().printBoard();
		}else if(sel == 2){
			// 게시글 삭제
			dao.BoardDAO.getInstance().deleteBoard();
		}else if(sel == 3){
			cont.setNext("AdminMain");
		}

		return false;
	}

}
