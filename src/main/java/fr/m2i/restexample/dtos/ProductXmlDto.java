package fr.m2i.restexample.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "product")
public class ProductXmlDto implements Serializable {
    @JacksonXmlProperty(isAttribute = true)
    public int quantity;
    @JacksonXmlProperty
    public String title;
}
