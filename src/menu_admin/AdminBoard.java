package menu_admin;

import _mall.MenuCommand;
import controller.MallController;

public class AdminBoard implements MenuCommand{
	private MallController cont;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		cont = MallController.getInstance();
		System.out.println("=====[ 관리자 메뉴 ]=====");
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

}
