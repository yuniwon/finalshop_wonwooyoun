package dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dto.*;

public class FileDAO {


	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");
		private String name;
		FileName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
	}

	private FileDAO() {}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}
	
	private void createFile(FileName name) {
		Path path = Paths.get("src/files/" + name.getName());
		try {
			Files.createFile(path);
		} catch (IOException e) {
			//System.out.println("파일이 이미 있음");
		}
	}

	private void init() {

		createFile(FileName.BOARD);
		createFile(FileName.MEMBER);
		createFile(FileName.ITEM);
		createFile(FileName.CART);

	}

	public void loadAllFiles() {
		// try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(Path.of("src/files/board.txt").toFile()))){

		// 	Charset charset = StandardCharsets.UTF_8;
		// 	// 버퍼 인풋 스트림에서  byte[1024] 씩 가져오는것 
		// 	byte[] buffer = new byte[1024];
		// 	int readByte =0;
		// 	int count =0;
		// 	while((readByte = bis.read(buffer)) != -1) {
		// 		String data = new String(buffer, 0, readByte,charset);
		// 		System.out.println(data);
		// 	}
		// }
		// catch(IOException e) {
		// 	e.printStackTrace();
		// }
		try {
			Files.list(Paths.get("src/files")).forEach(path -> { //files에 있는 파일들을 읽어서 각각의 DAO에 저장
				try {
					Files.lines(path).forEach(line -> { 
						String[] arr = line.split("/");
						switch (path.getFileName().toString()) {
						case "board.txt":
						Board temp = new Board(Integer.parseInt(arr[0]), arr[1], arr[3], arr[4], arr[2], Integer.parseInt(arr[5]));
							BoardDAO.getInstance().addBoard(temp);
							break;
							case "member.txt":
							Member temp2 = new Member(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3]);
							MemberDAO.getInstance().addMember(temp2);
							break;
							case "item.txt":
							Item temp3 = new Item(Integer.parseInt(arr[0]), arr[1], arr[2], Integer.parseInt(arr[3]));
							ItemDAO.getInstance().addItem(temp3);
							break;
							case "cart.txt":
							Cart temp4 = new Cart(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
							CartDAO.getInstance().addCart(temp4);
							break;
						}
					});
				} catch (IOException e) {
					System.out.println("파일 읽기 실패");
					e.printStackTrace();
				}
			});
		} catch (IOException e1) {
			System.out.println("파일 목록 읽기 실패");
			e1.printStackTrace();
		}
		
		// BoardDAO.getInstance().getBoards().forEach(System.out::println);
		// MemberDAO.getInstance().getMembers().forEach(System.out::println);
		// ItemDAO.getInstance().getItems().forEach(System.out::println);
		// CartDAO.getInstance().getCarts().forEach(System.out::println);
	


	
	
	}
    public void saveFile() {
		// 각 파일에 저장
		// 파일이 없으면 생성
		// files 폴더에 저장
		// 파일에 저장할때는 / 구분자로 저장
		// Board : 번호 / 제목 / 내용 / 작성자 / 작성일 / 조회수
		// Member : 번호 / 아이디 / 비밀번호 / 이름
		// Item : 번호 / 카테고리 / 아이템명 / 가격
		// Cart : 번호 / 아이디 / 아이템번호 / 수량
		try {
			Files.write(Paths.get("src/files/board.txt"), BoardDAO.getInstance().getBoards().stream().map(board -> board.toString()).toList(), StandardCharsets.UTF_8);
			Files.write(Paths.get("src/files/member.txt"), MemberDAO.getInstance().getMembers().stream().map(member -> member.toString()).toList(), StandardCharsets.UTF_8);
			Files.write(Paths.get("src/files/item.txt"), ItemDAO.getInstance().getItems().stream().map(item -> item.toString()).toList(), StandardCharsets.UTF_8);
			Files.write(Paths.get("src/files/cart.txt"), CartDAO.getInstance().getCarts().stream().map(cart -> cart.toString()).toList(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}