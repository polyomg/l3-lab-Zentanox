package web.shop.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import web.shop.DB;
import web.shop.entity.Item;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private Map<Integer, Item> map = new HashMap<>();

    @Override
    public Item add(Integer id) {
        if(id == null) return null;
        Item item = map.get(id);
        if(item != null) {
            item.setQty(item.getQty() + 1);
        } else {
            web.shop.entity.Item dbItem = DB.items.get(id);
            if(dbItem == null) return null;
            Item newItem = new Item(dbItem.getId(), dbItem.getName(), dbItem.getPrice(), 1);
            map.put(id, newItem);
            item = newItem;
        }
        return item;
    }

    @Override
    public void remove(Integer id) {
        if(id == null) return;
        map.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        if(id == null) return null;
        Item item = map.get(id);
        if(item == null) return null;
        if(qty <= 0) {
            map.remove(id);
            return null;
        } else {
            item.setQty(qty);
            return item;
        }
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return map.values();
    }

    @Override
    public int getCount() {
        int c = 0;
        for(Item i : map.values()) c += i.getQty();
        return c;
    }

    @Override
    public double getAmount() {
        double s = 0;
        for(Item i : map.values()) s += i.getQty() * i.getPrice();
        return s;
    }
}
