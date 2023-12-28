package dao;

import java.nio.file.Path;
import java.util.*;

import controller.MallController;
import dto.Item;
import util.Util;

public class ItemDAO {
    //싱글톤
    private ItemDAO() {
    }
    private static ItemDAO instance = new ItemDAO();
    public static ItemDAO getInstance() {
        return instance;
    }
    //싱글톤 끝
    
    private List<Item> items = new ArrayList<>();
    private List<String> category = new ArrayList<>();
    

    public void addItem(Item temp3) {
        items.add(temp3);
        Item.setNum(Item.getNum()+1);
    }
	public List<Item> getItems() {
		return items;
	}

    public int printCategory(){
        System.out.println();
        if(category.size() == 0){
            items.stream().map(Item::getCategoryName).distinct().forEach(name ->{ category.add(name); });
        }
        for(int i=0; i<category.size(); i++){
            System.out.println("["+(i+1)+"] "+category.get(i));
        }
        return category.size();
    }
    public void printItemByCategory(int sel) {
        String Cat_temp = category.get(sel-1);
        items.stream().filter(item -> item.getCategoryName().equals(Cat_temp)).forEach(item -> {
            System.out.println(item.getItemName() + " " + item.getPrice() + "원");	
        });
        String name = Util.getValue("상품명 ");
        // 아이템이 있는지 확인 후 있으면 구매수량 입력
        for(Item item : items){
            // System.out.println(item.getItemName() + " " + item.getPrice() + "원");
            if(item.getCategoryName().equals(Cat_temp) && item.getItemName().equals(name)){
                int num = Util.getValue("구매수량 ", 1, 100);
                // 장바구니에 추가
                CartDAO.getInstance().addCart(MallController.getInstance().getLoginId(), item.getItemNum(), num);
                System.out.println("[ 장바구니에 추가되었습니다 ]");
                break;
            }
        }

    }
    public void insertItem() {

        String name = Util.getValue("상품명 ");
        String category = Util.getValue("카테고리 ");
        int price = Util.getValue("가격 ", 100, 1000000);
        //아이템명 중복확인
        for(Item item : items){
            if(item.getItemName().equals(name)){
                System.out.println("[ 상품명이 중복됩니다 ]");
                return;
            }
        }
        Item.setNum(Item.getNum()+1);
        items.add(new Item(Item.getNum(),category,name,price));
        System.out.println("[ 상품이 등록되었습니다 ]");
        // 아이템 추가
    }
    public void printAllItem() {
        // 아이템 전체 출력
        // 카테고리순서대로 출력
        // (번호 ) [카테고리 ] [상품명 ] [가격 ]
        items.stream().sorted(Comparator.comparing(Item::getCategoryName)).forEach(item -> {
            System.out.println("(" + item.getItemNum() + ") [" + item.getCategoryName() + "] [" + item.getItemName() + "] [" + item.getPrice()+"원]");
        });
    }
    public void deleteItem() {
        // 아이템 삭제
        // 삭제시 해당 아이템의 구매내역도 삭제
        // 아이템 번호를 입력받아 삭제
        int num = Util.getValue("삭제할 상품번호 ", 1, items.size());
        items.remove(num-1);
        System.out.println("[ 상품이 삭제되었습니다 ]");
        // 구매내역 삭제
        CartDAO.getInstance().getCarts().removeIf(cart -> cart.getItemNum() == num);
    }
    public void printAllItemByCart() {
        // 장바구니에 담긴 아이템 출력
    }
    
}
