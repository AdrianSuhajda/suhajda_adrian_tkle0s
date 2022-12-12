package mik.prog5.tkle0s.dogtoyshop.entity;

import lombok.Data;

@Data
public class Toy {
    private Long id;
    private String name;
    private String image;
    private String description;
    private Integer cost;
}
