package dao;

import java.util.*;

import dto.Member;

public class MemberDAO {
	private MemberDAO() {}
	static private MemberDAO Instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return Instance;
	}
	
	private List<Member> members = new ArrayList<Member>();

	public Object getMemberById(String id) {
		//멤버중에서 id가 중복인지 확인. 스트림 쓰면 될거같은데?
		
		return null;
	}

	public boolean insertMember(String id, String pw, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object isValidMember(String id, String pw) {
		// TODO Auto-generated method stub
		return null;
	}

}
