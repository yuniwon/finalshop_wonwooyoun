package dao;

import java.nio.file.Path;
import java.util.*;

import controller.MallController;
import dto.Cart;
import dto.Item;

public class CartDAO {

    //싱글톤
    private CartDAO() {
    }
    private static CartDAO instance = new CartDAO();
    public static CartDAO getInstance() {
        return instance;
    }
    //싱글톤 끝

    private List<Cart> carts = new ArrayList<>();
    
    public void addCart(Cart temp4) {
        carts.add(temp4);
        Cart.setNum(Cart.getNum()+1);

    }
    public void addCart(String id, int itemNum, int itemCnt) {
        carts.add(new Cart(Cart.getNum(),id,itemNum,itemCnt));
        Cart.setNum(Cart.getNum()+1);

    }
    
	public List<Cart> getCarts() {
		return carts;
	}
    public void printCartById() {
        String id = MallController.getInstance().getLoginId();

        // 장바구니에 담긴 아이템 출력 ( 번호 , 아이템명, 가격, 수량, 총가격) 
        ItemDAO.getInstance().getItems().stream().forEach(item ->{
            int cnt = 0;
            for(Cart cart : carts){
                if(cart.getId().equals(id) && cart.getItemNum() == item.getItemNum()){
                    cnt += cart.getItemCnt();
                }
            }
            if(cnt != 0){
                System.out.println(item.getItemNum() + " \t" + item.getItemName() + "(" + item.getPrice() + "원)\t " + cnt + "개 총 " + item.getPrice()*cnt + "원");
            }
        });
        int total = 0;
        int totalcnt = 0;
        System.out.println(" ===================");
        // 물건의 총 갯수와 가격 출력
        for(Cart cart : carts){
            if(cart.getId().equals(id)){
                Item item = ItemDAO.getInstance().getItems().get(cart.getItemNum()-1);
                total += item.getPrice()*cart.getItemCnt();
                totalcnt += cart.getItemCnt();
            }
        }
        System.out.println("총 "+ totalcnt+"개 (" + total + "원)");
    }
    public void deleteCart() {
            String id = MallController.getInstance().getLoginId();
        // 장바구니에 담긴 아이템 중 id와 일치하는 아이템 삭제
        carts.removeIf(cart -> cart.getId().equals(id));
    }
    public void deleteCart(String id) {
     carts.removeIf(cart -> cart.getId().equals(id));
    }
    public void printAllItem() {
        // 장바구니에 담긴 아이템 출력 ( 번호 , 카테고리,  아이템명, 가격, 판매수량)
        // 아이템별로 합쳐서 배열 저장

            List<Cart> temp = new ArrayList<>();
            ItemDAO.getInstance().getItems().stream().forEach(item ->{
            int cnt = 0;
            for(Cart cart : carts){
                if(cart.getItemNum() == item.getItemNum()){
                    cnt += cart.getItemCnt();
                }
            }
            if(cnt != 0){
            temp.add(new Cart(item.getItemNum(),item.getItemName(),item.getItemNum(),cnt));
                // System.out.println(item.getItemNum() + " \t" + item.getCategoryName()+ "\t" + item.getItemName() + "(" + item.getPrice() + "원)\t " + cnt + "개 총 " + item.getPrice()*cnt + "원");
            }
        });
        
            temp.stream().sorted(Comparator.comparing(Cart::getItemCnt).reversed()).forEach(cart -> {
                for(Item item : ItemDAO.getInstance().getItems()){
                    if(cart.getItemNum() == item.getItemNum()){
                        System.out.println(cart.getItemNum() + " \t" + item.getCategoryName()+ "\t" + cart.getId() + "(" + item.getPrice() + "원)\t " + cart.getItemCnt() + "개 총 ");
                    }
                }
                // System.out.println(cart.getItemNum() + cart.getId() +" \t" + cart.getItemCnt() + "개 총 " + cart.getItemCnt()*ItemDAO.getInstance().getItems().get(cart.getItemNum()-1).getPrice() + "원");
            });

    }
   
    
}
