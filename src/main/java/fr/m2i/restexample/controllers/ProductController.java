package fr.m2i.restexample.controllers;

import fr.m2i.restexample.dtos.ProductDto;
import fr.m2i.restexample.dtos.ProductXmlDto;
import fr.m2i.restexample.dtos.ProductsXmlDto;
import fr.m2i.restexample.exceptions.M2I404Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    @GetMapping
    public List<ProductDto> getAll(@RequestParam(required = false) boolean isSalty) throws M2I404Exception {
        // TODO: SELECT all en base
        if (isSalty) {
                methodThatThrowsException();
        }
        return Arrays.asList(new ProductDto(112, "Chocolat"), new ProductDto(15, "Pomme"),
                new ProductDto(10, "Poire"));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto getById(@PathVariable int id) {
        // TODO : SELECT by id en base
        return new ProductDto(10, "Poire");
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        // TODO: insérer en base
        return productDto;
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable int id, @RequestBody ProductDto productDto) {
        // TODO : modifier en base
        return productDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        // TODO: suppression en base
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ProductsXmlDto getAllXml() {
        return new ProductsXmlDto(Arrays.asList(new ProductXmlDto(112, "Chocolat"), new ProductXmlDto(15, "Pomme"),
                new ProductXmlDto(10, "Poire")));
    }

    @PostMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ProductXmlDto createXml(@RequestBody ProductXmlDto product) {
        // Quel est le comportement si on envoie du JSON ?
        return product;
    }

    private void methodThatThrowsException() throws M2I404Exception {
        // TODO: code métier qui a un moment, fait planter l'API
        throw new M2I404Exception("Impossible de trouvé les produits");
    }
}
