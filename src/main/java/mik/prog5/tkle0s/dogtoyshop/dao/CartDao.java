package mik.prog5.tkle0s.dogtoyshop.dao;


import mik.prog5.tkle0s.dogtoyshop.entity.Cart;

public interface CartDao extends CrudDao<Cart, Long>{
    Integer getSum();

    boolean deleteAll();
}
