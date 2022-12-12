package mik.prog5.tkle0s.dogtoyshop.controller;

import lombok.RequiredArgsConstructor;
import mik.prog5.tkle0s.dogtoyshop.entity.Toy;
import mik.prog5.tkle0s.dogtoyshop.service.ToyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ToyController {
    private final ToyService toyService;

    @GetMapping("/toys")
    public String listToys(Model model){
        List<Toy> toyList = this.toyService.findAll();
        model.addAttribute("toyList", toyList);
        return "toys";
    }
}
