package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(BookForm bookForm) {

        Book book = new Book();
        book.setName(bookForm.getName());
        book.setAuthor(bookForm.getAuthor());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setPrice(bookForm.getPrice());
        book.setIsbn(bookForm.getIsbn());

        itemService.save(book);
        return "redirect:/items";
    }

    @GetMapping(value = "/items")
    public String list(Model model) {
        List<Item> all = itemService.findAll();
        model.addAttribute("items", all);
        return "items/itemList";

    }

    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Book one = (Book) itemService.findOne(itemId);

        BookForm bookForm = new BookForm();
        bookForm.setId(one.getId());
        bookForm.setName(one.getName());
        bookForm.setPrice(one.getPrice());
        bookForm.setAuthor(one.getAuthor());
        bookForm.setStockQuantity(one.getStockQuantity());
        bookForm.setIsbn(one.getIsbn());

        model.addAttribute("form", bookForm);
        return "items/updateItemForm";
    }

    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm bookForm) {

        itemService.updateItmes(itemId, bookForm.getName(), bookForm.getPrice(), bookForm.getStockQuantity());
        return "redirect:/items";

    }
}
