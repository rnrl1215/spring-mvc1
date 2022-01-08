package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }


    @GetMapping("/{itemId}")
    public String items(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName ,
                       @RequestParam int price,
                       @RequestParam int quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

    // @PostMapping("/add")
    // ModelAttribute 의 item 은 뭘까?
    // 아래 처럼 알아서 된다.
    // model.addAttribute("item", item);
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    // @PostMapping("/add")
    // ModelAttribute 의 item 즉 name 을 빼면?
    // 아래 처럼 알아서 된다.
    // Item -> item
    // HelloData -> helloData 가 된다.
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    // 아예 생략도 가능하다.
    // 상품을 저장하고 뷰를 보여 줬다.
    // 상품 저장을 하면 상품상세로 넘어간다.
    // 하지만 url 은 여전히 저장을 나타내기 때문에
    // 새로고침을 하면 계속 저장된다. 계속 아이템이 저장된다.
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);

        return "redirect:basic/item"+item.getId();
    }


    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        // status 가 true 일때 저장돼서 넘어 왔다는걸 판단한다.
        redirectAttributes.addAttribute("status", true);

        // redirectAttributes itemId 를 쓰면 아래 return 시 itemid 가 치환이 된다.
        // 남는 status 는 쿼리 파라미터로 넘어간다.
        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    // @ModelAttribute 를 사용하면 객체에 담아서 넘어 오기 때문에 바로 사용하면 된다.
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        // {itemId} 를 사용할수 있다
        // redirect 이용하여 다음 경로로 이동가능하다.
        // 상태 코드 302 가 된다.
        return "redirect:/basic/items/{itemId}";
    }



    /**
     *  Test 용 데이터 추가.
     *
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("ItemA",10000,10));
        itemRepository.save(new Item("ItemB",20000,20));
    }
}
