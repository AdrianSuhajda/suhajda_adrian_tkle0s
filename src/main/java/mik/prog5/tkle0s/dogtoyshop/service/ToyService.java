package mik.prog5.tkle0s.dogtoyshop.service;

import lombok.RequiredArgsConstructor;
import mik.prog5.tkle0s.dogtoyshop.dao.ToyDao;
import mik.prog5.tkle0s.dogtoyshop.entity.Toy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToyService {

    private final ToyDao toyDao;

    public Toy create(Toy toy) {
        return this.toyDao.create(toy);
    }

    public List<Toy> findAll() {
        return this.toyDao.findAll();
    }

    public Toy findById(Long id) {
        return this.toyDao.findById(id);
    }

    public Toy update(Toy toy) {
        return this.toyDao.update(toy);
    }

    public boolean delete(Long id){
        return this.toyDao.delete(id);
    }
}
