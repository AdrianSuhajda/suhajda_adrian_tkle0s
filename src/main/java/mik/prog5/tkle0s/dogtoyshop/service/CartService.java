package mik.prog5.tkle0s.dogtoyshop.service;

import lombok.RequiredArgsConstructor;
import mik.prog5.tkle0s.dogtoyshop.dao.CartDao;
import mik.prog5.tkle0s.dogtoyshop.entity.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDao cartDao;

    public Cart create(Cart cart) {
        return this.cartDao.create(cart);
    }

    public List<Cart> findAll() {
        return this.cartDao.findAll();
    }

    public Cart findById(Long id) {
        return this.cartDao.findById(id);
    }

    public Cart update(Cart cart) {
        return this.cartDao.update(cart);
    }

    public boolean delete(Long id){
        return this.cartDao.delete(id);
    }

    public boolean deleteAll() {
        return this.cartDao.deleteAll();
    }
    public int getSum() {
        return this.cartDao.getSum();
    }
}
