package mik.prog5.tkle0s.dogtoyshop.service;

import lombok.RequiredArgsConstructor;
import mik.prog5.tkle0s.dogtoyshop.dao.PaymentDao;
import mik.prog5.tkle0s.dogtoyshop.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentDao paymentDao;

    public Payment create(Payment payment) {
        return this.paymentDao.create(payment);
    }

    public List<Payment> findAll() {
        return this.paymentDao.findAll();
    }

    public Payment findById(Long id) {
        return this.paymentDao.findById(id);
    }

    public Payment update(Payment payment) {
        return this.paymentDao.update(payment);
    }

    public boolean delete(Long id){
        return this.paymentDao.delete(id);
    }

}
