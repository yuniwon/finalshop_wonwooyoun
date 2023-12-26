package dao;

import java.nio.file.Path;
import java.util.*;

import dto.Cart;

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
    }
    
	public List<Cart> getCarts() {
		return carts;
	}
    
    
}
