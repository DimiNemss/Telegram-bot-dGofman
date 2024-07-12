package ru.dmitriy.tgBot.DataBase.rest;

import jakarta.persistence.PostUpdate;
import org.springframework.web.bind.annotation.*;
import ru.dmitriy.tgBot.DataBase.entity.*;
import ru.dmitriy.tgBot.DataBase.service.EntitiesServiceImpl;

import java.util.List;

@RestController
public class AppRestController {

    private  final EntitiesServiceImpl entityService;

    public AppRestController(EntitiesServiceImpl entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/rest/products/search")
    List<Product> getProductsByCategoryId(@RequestParam Long categoryId) {
        return entityService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/rest/clients/{id}/orders")
    List<ClientOrder> getClientOrders(@PathVariable Long id) {

        return entityService.getClientOrders(id);
    }

    @GetMapping("/rest/clients/{id}/products")
    public List<Product> getClientProducts(@PathVariable Long id) {

        return entityService.getClientProducts(id);
    }

    @GetMapping("/rest/products/popular")
    public  List<Product> getTopPopularProducts(@RequestParam Integer limit) {
        return entityService.getTopPopularProducts(limit);
    }

    @GetMapping("/rest/category/{id}/categories")
    public List<Category> getCategoriesByParentId(@PathVariable Long id) {
        return  entityService.getCategoriesByParentId(id);
    }

    @GetMapping("/rest/products/{id}")
    public Product getProductById(Long id) {
        return entityService.getProductById(id);
    }

    @PostMapping("/rest/clients")
    public Client postClient(Long id, String fullName, String phoneNumber, String address) {
        return  entityService.postClient(id, fullName, phoneNumber, address);
    }

    @PostMapping("/rest/clients/{id}/order")
    public ClientOrder postClientOrder(Client client, Integer status, Double total) {
        return entityService.postOrder(client, status, total);
    }

    @PostMapping("/rest/order/{id}")
    public OrderProduct postProduct(ClientOrder clientOrder, Product product, Integer count) {
        return entityService.postProduct(clientOrder, product, count);
    }

    @PostUpdate
    public ClientOrder updateClientOrder(ClientOrder clientOrder) {
        return entityService.updateClientOrder(clientOrder);
    }
    public Client findByByExternalId(Long id) {
        return entityService.findByExternalId(id);
    }

    public boolean existsByProduct(Product product) {
        return entityService.existsByProduct(product);
    }

    public List<Product> getClientProduct(Long clientOrderId) {
        return  entityService.getClientProduct(clientOrderId);
    }

}
