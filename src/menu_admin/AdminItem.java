package menu_admin;

import _mall.MenuCommand;
import controller.MallController;

public class AdminItem implements MenuCommand{
private MallController cont;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		cont = MallController.getInstance();
		System.out.println("=====[ 관리자 상품관리 ]=====");
		System.out.println("[1] 아이템추가\n[2] 아이템 삭제\n[3] 총 매출 아이템 갯수 출력(판매량 높은순으로)\n[4] 뒤로가기\n[0] 종료");
		System.out.println("=====================");

	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		int sel = util.Util.getValue("메뉴 입력", 0, 4);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}else if(sel == 1){
			// 아이템추가
			//카테고리별 아이템목록
			dao.ItemDAO.getInstance().printAllItem();
			dao.ItemDAO.getInstance().insertItem();
		}else if(sel == 2){
			// 상품삭제
			dao.ItemDAO.getInstance().printAllItem();
			dao.ItemDAO.getInstance().deleteItem();
		}else if(sel == 3){
			// 총 매출 아이템 갯수 출력(판매량 높은순으로)
			dao.CartDAO.getInstance().printAllItem();
		}else if(sel == 4){
			cont.setNext("AdminMain");
		}

		return false;
	}

}
