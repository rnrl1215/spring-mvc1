package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ItemRepository {
    // 멀티환경에서는 일반 hashmap 이아니라
    // ConcurrentHashMap 을 써야한다.
    private static final Map<Long, Item> store = new HashMap<>();

    // atomic
    private static Long sequence =0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        // 감싸서 반환하게 되면 값을 넣어도
        // 실제 store 에는 영향이 가지 않는다.
        return new ArrayList<>(store.values());
    }

    // 원래는 updateParamDto 를 만들고 id 를제외 데이터를 넣는게 맞다.
    // 설계상 명확하게 맞다. 중복 <<<<<< 명확성
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clear() {
        store.clear();
    }

}

