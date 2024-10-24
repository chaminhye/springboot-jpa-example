package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItmes(Long id, String name, int price, int stockQuantity) {
        // 사실 setter를 사용하는 방법도 옳치는 않음
        Item item = itemRepository.findById(id);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);

        // 이렇게 change 메서드를 만들어서 변경을 추적하게 만드는 코드가 best
//        item.change(name, price, stockQuantity);
    }

    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
