package mik.prog5.tkle0s.dogtoyshop.controller;

import lombok.RequiredArgsConstructor;
import mik.prog5.tkle0s.dogtoyshop.entity.Payment;
import mik.prog5.tkle0s.dogtoyshop.service.CartService;
import mik.prog5.tkle0s.dogtoyshop.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final CartService cartService;

    @GetMapping("/pay")
    public String pay(Model model) {
        model.addAttribute("payment", new Payment());
        return "paymentForm";
    }
    @PostMapping("/pay")
    public String pay(final Payment payment) {
        this.paymentService.create(payment);
        this.cartService.deleteAll();
        return "payment";
    }
}
