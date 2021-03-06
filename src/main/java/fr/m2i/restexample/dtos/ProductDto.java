package fr.m2i.restexample.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    public Long id;
    public int quantity;
    public String title;
}
