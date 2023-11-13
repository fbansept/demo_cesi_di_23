package edu.cesi.demo.controller;

import edu.cesi.demo.dao.ProductDao;
import edu.cesi.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id){
        return productDao.findById(id).orElse(null);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        List<Product> listProduct = productDao.findAll();
        return listProduct;
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id){
        productDao.deleteById(id);
    }

    @PostMapping("/product")
    public void editProduct(@RequestBody Product product) {
        productDao.save(product);
    }

    @PostMapping("/change-stock/{idProduct}/{quantity}")
    public void changeStock(@PathVariable int idProduct, @PathVariable int quantity) {
        Optional<Product> productOptional = productDao.findById(idProduct);

        if(productOptional.isPresent()) {
            Product product = productOptional.get();

            //si la quantité à modifier est positive
            // ou si la quantité à supprimer est inférieur au stock
            //(cad qu'il y est assez de stock à supprimer)
            if(quantity > 0 || product.getQuantity() > -quantity) {

                product.setQuantity(product.getQuantity() + quantity);
                productDao.save(product);
            }
        }
    }
}
