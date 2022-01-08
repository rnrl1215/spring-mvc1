package hello.itemservice.domain.item;

// data 를 쓰면위험하다.
// 위험한걸 체험하기위해 써본다.

import lombok.Data;

@Data
public class Item {
    private Long Id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
