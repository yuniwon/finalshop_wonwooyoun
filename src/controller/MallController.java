package controller;

import java.util.*;

import _mall.MenuCommand;
import dao.FileDAO;
import menu_mall.*;
import menu_admin.*;
import menu_member.*;

public class MallController {
	private MallController() {}

	static private MallController instance = new MallController();

	static public MallController getInstance() {
		return instance;
	}
	

	private String loginId;
	private String next;
	private MenuCommand menuCom;
	public Map<String, MenuCommand> mapCont;

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void init() {
		FileDAO.getInstance().loadAllFiles();
		mapCont = new HashMap<>();
		mapCont.put("MallMain", new _MallMain());
		mapCont.put("MallJoin", new MallJoin());
		mapCont.put("MallLogin", new MallLogin());
		mapCont.put("AdminBoard", new AdminBoard());
		mapCont.put("AdminItem", new AdminItem());
		mapCont.put("AdminMain", new _AdminMain());
		mapCont.put("AdminMember", new AdminMember());
		mapCont.put("MemberBoard", new MemberBoard());
		mapCont.put("MemberCart", new MemberCart());
		mapCont.put("MemberInfo", new MemberInfo());
		mapCont.put("MemberMain", new _MemberMain());
		mapCont.put("MemberShopping", new MemberShopping());
		mapCont.put("MemberQuit", new MemberQuit());

		menuCom = mapCont.get("MallMain");
		menuCom.init();
		update();
		
	}

	public void update() {
		while (true) {
			if (!menuCom.update()) {
				if (next != null) {
					menuCom = mapCont.get(next);
					menuCom.init();
				} else {
					System.out.println("여기는?");
					return;
				}
			}
		}
	}

}