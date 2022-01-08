package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    // test 끌날때 마다 실행된다.
    @AfterEach
    void afterEach() {
        itemRepository.clear();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item saveItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }


    @Test
    void findAll() {
        // given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);
    }


    @Test
    void updateItem() {
        // given
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long id = savedItem.getId();

        // when
        Item updateItem = new Item("item2", 20000,20);
        itemRepository.update(id, updateItem);

        // then
        Item findItem = itemRepository.findById(id);
        assertThat(findItem.getItemName()).isEqualTo(findItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(findItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(findItem.getQuantity());
    }
}