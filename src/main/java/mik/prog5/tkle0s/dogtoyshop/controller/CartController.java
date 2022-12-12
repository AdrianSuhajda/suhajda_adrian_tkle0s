package mik.prog5.tkle0s.dogtoyshop.controller;

import lombok.RequiredArgsConstructor;
import mik.prog5.tkle0s.dogtoyshop.entity.Cart;
import mik.prog5.tkle0s.dogtoyshop.entity.Toy;
import mik.prog5.tkle0s.dogtoyshop.service.CartService;
import mik.prog5.tkle0s.dogtoyshop.service.ToyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ToyService toyService;

    @GetMapping("/cart")
    public String listCart(Model model){
        List<Cart> cartList = this.cartService.findAll();
        model.addAttribute("cartList", cartList);

        int total = this.cartService.getSum();
        model.addAttribute("total", total);

        return "cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam int quantity) {
        Toy toy = this.toyService.findById(id);
        Cart cart = new Cart();
        cart.setName(toy.getName());
        cart.setImage(toy.getImage());
        cart.setDescription(toy.getDescription());
        cart.setCost(toy.getCost());

        cart.setQuantity(quantity);

        this.cartService.create(cart);

        return "redirect:/toys";
    }

    @GetMapping("/cart/{id}")
    public String delete(@PathVariable Long id){
        this.cartService.delete(id);
        return "redirect:/cart";
    }

}
