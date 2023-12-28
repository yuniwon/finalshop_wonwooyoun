package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import util.Util;

public class MemberCart implements MenuCommand{
	private MallController cont;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		cont = MallController.getInstance();
		System.out.println("=====[ 구매내역 ]=====");
		CartDAO.getInstance().printCartById();
		System.out.println("[1] 쇼핑하기\n[2] 뒤로가기\n[0] 종료");
		System.out.println("=====================");

	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		int sel = Util.getValue("메뉴 입력", 0, 2);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}else if (sel == 1) {
			cont.setNext("MemberShopping");
		} else if (sel == 2) {
			cont.setNext("MemberMain");
		}

		return false;
	}

}
