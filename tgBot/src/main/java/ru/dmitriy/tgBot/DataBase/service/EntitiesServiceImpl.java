package ru.dmitriy.tgBot.DataBase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.dmitriy.tgBot.DataBase.entity.*;
import ru.dmitriy.tgBot.DataBase.repository.ClientOrderRepository;
import ru.dmitriy.tgBot.DataBase.repository.OrderProductRepository;
import ru.dmitriy.tgBot.DataBase.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class EntitiesServiceImpl implements EntitiesService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ClientOrderRepository clientOrderRepository;
    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        Product exampleProduct = new Product();
        Category exampleCategory = new Category();
        exampleCategory.setId(id);
        exampleProduct.setCategory(exampleCategory);
        return productRepository.findAll(Example.of(exampleProduct));
    }
    @Override
     public List<ClientOrder> getClientOrders(Long id) {
        Client exampleClient = new Client();
        ClientOrder exampleClientOrder = new ClientOrder();
        exampleClient.setId(id);
        exampleClientOrder.setClient(exampleClient);
        return clientOrderRepository.findAll(Example.of(exampleClientOrder));
    }
    @Override
    public List<Product> getClientProducts(Long id) {
        return orderProductRepository.getClientProducts(id);
    }

    @Override
    public List<Product> getTopPopularProducts(Integer limit) {
        return productRepository.getTopPopularProducts(PageRequest.of(0, limit));
    }
}
