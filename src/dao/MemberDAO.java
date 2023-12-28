package dao;

import java.nio.file.Path;
import java.util.*;

import controller.MallController;
import dto.Board;
import dto.Member;
import util.Util;

public class MemberDAO {
	private MemberDAO() {}
	static private MemberDAO Instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return Instance;
	}
	
	private List<Member> members = new ArrayList<Member>();

	public Object getMemberById(String id) {
		//멤버중에서 id가 중복인지 확인.
		for(Member m : members) {
			if(m.getId().equals(id)) {
				return m;
			}
		}
		return null;
	}

	public boolean insertMember(String id, String pw, String name) {
		Member temp = new Member(Member.getNum(),id,pw,name);
		// 회원가입
		Member.setNum(Member.getNum()+1);
		return members.add(temp);
	}

	public Object isValidMember(String id, String pw) {
		// 멤버중에서 id와 pw가 일치하는지 확인.
		for(Member m : members) {
			if(m.getId().equals(id) && m.getPw().equals(pw)) {
				return m;
			}
		}
		return null;
	}

    public void addMember(Member temp) {
		//데이터에서 멤버 추가
		members.add(temp);
		Member.setNum(temp.getMemberNum()+1);
    }

	public List<Member> getMembers() {
		return members;
	}

	public void updateMember() {
		// 기존 비밀번호를 입력받고, 새로운 비밀번호를 입력받아서 변경
		String curPw = Util.getValue("현재 비밀번호 ");
		String newPw = Util.getValue("새로운 비밀번호 ");
		if(members.stream().filter(member -> member.getPw().equals(curPw)).count() == 0) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		members.stream().filter(member -> member.getPw().equals(curPw)).forEach(member -> {
			member.setPw(newPw);
			System.out.println("비밀번호가 변경되었습니다.");
		});
	}

    public void printMember(String id) {
		// 자신의 정보 출력
		// 회원번호 아이디 비밀번호 이름
		members.stream().filter(member -> member.getId().equals(id)).forEach(member -> {	
			System.out.printf("%-5s %7s%7s\t%7s%n", "회원번호","아이디","비밀번호","이름");
			System.out.printf("%-10d %7s%7s\t%7s%n", member.getMemberNum() , member.getId() , member.getPw() , member.getMemberName());
		});
		
    }

	public boolean deleteMember() {
		if(MallController.getInstance().getLoginId().equals("admin")) {
			System.out.println("관리자 삭제메뉴");
			//삭제할 회원아이디 입력 후 삭제. 관리자는 삭제불가능
			String id = Util.getValue("삭제할 회원 아이디 ");
			if(id.equals("admin")) {
				System.out.println("관리자는 삭제할 수 없습니다.");
				return false;
			}
			members.removeIf(member -> member.getId().equals(id));
			CartDAO.getInstance().deleteCart(id);

			return false;
		}else{
		//비밀번호를 확인 후 회원삭제
		String pw = Util.getValue("비밀번호 ");
		if(members.stream().filter(member -> member.getPw().equals(pw)).count() == 0) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return false;
		}
		members.removeIf(member -> member.getPw().equals(pw));
		// 카트에서도 삭제
		CartDAO.getInstance().deleteCart();
		System.out.println("회원탈퇴가 완료되었습니다.");
		return true;
		}

		
	}

	public void printAllMember() {
		// 모든 멤버 출력
		System.out.printf("%s %s %7s\t%7s%n", "회원번호","아이디","비밀번호","이름");
		members.stream().forEach(member -> {
			System.out.printf("%-7d\t%s%7s\t%7s%n", member.getMemberNum() , member.getId() , member.getPw() , member.getMemberName());
		});
	}



}
