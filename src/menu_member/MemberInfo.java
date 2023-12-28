package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import dto.Member;
import util.Util;

public class MemberInfo implements MenuCommand{
	private MallController cont;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		cont = MallController.getInstance();
		System.out.println("=====[ 내 정보 ]=====");
		MemberDAO.getInstance().printMember(cont.getLoginId());
		System.out.println("[1] 비밀번호 수정\n[2] 뒤로가기\n[0] 종료");
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
			MemberDAO.getInstance().updateMember();
		} else if (sel == 2) {
			cont.setNext("MemberMain");
		}

		return false;
	}

}
