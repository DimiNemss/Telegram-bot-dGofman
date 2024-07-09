package ru.dmitriy.tgBot.DataBase.rest;

import org.springframework.web.bind.annotation.*;
import ru.dmitriy.tgBot.DataBase.entity.ClientOrder;
import ru.dmitriy.tgBot.DataBase.entity.Product;
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
}
