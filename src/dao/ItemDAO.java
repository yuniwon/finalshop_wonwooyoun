package dao;

import java.nio.file.Path;
import java.util.*;

import dto.Item;

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
    public void addItem(Item temp3) {
        items.add(temp3);
    }
	public List<Item> getItems() {
		return items;
	}


    
}
