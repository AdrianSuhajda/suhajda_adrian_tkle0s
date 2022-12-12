package mik.prog5.tkle0s.dogtoyshop.entity;

import lombok.Data;

@Data
public class Payment {
    private Long id;
    private String name;
    private Integer cardNumber;
    private Integer expireDate;
    private Integer cvc;
}
