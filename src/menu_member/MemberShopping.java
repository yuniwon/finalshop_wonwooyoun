package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.ItemDAO;
import util.Util;

public class MemberShopping implements MenuCommand{
	private MallController cont;
	private int size;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		cont = MallController.getInstance();
		System.out.println("=====[ 쇼핑몰에 오신것을 환영합니다 ]=====");
		// 아이템 DAO에서 카테고리를 가져와서 중복없이 출력
		size = ItemDAO.getInstance().printCategory();
		
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		int sel = Util.getValue("카테고리 ", 0, size);
		if( sel == 0){
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}else if( sel <= size){
			ItemDAO.getInstance().printItemByCategory(sel);
			cont.setNext("MemberMain");
		}
		return false;
	}

}
