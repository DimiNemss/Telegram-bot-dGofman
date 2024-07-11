package ru.dmitriy.tgBot.DataBase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.dmitriy.tgBot.DataBase.entity.*;
import ru.dmitriy.tgBot.DataBase.repository.*;

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

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ClientRepository clientRepository;

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

    @Override
    public List<Category> getCategoriesByParentId(Long id) {

        return categoryRepository.findByParent_Id(id);
    }

    @Override
    public Client postClient(Long id, String fullName, String phoneNumber, String address) {
        Client client = new Client();
        client.setExternalId(id);
        client.setPhoneNumber(phoneNumber);
        client.setFullName(fullName);
        client.setAddress(address);
        return clientRepository.save(client);
    }

    @Override
    public ClientOrder postOrder(Client client, Integer status, Double total) {
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setClient(client);
        clientOrder.setStatus(status);
        clientOrder.setTotal(total);
        return clientOrderRepository.save(clientOrder);
    }

    @Override
    public Client findByExternalId(Long id) {
        return clientRepository.findByExternalId(id);
    }

    @Override
    public OrderProduct postProduct(ClientOrder clientOrder, Product product, Integer count) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setCountProduct(count);
        orderProduct.setClientOrder(clientOrder);
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public boolean existsByProduct(Product product) {
        return orderProductRepository.existsByProduct(product);
    }
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ClientOrder updateClientOrder(ClientOrder clientOrder) {
        return clientOrderRepository.save(clientOrder);
    }
    public List<Product> getClientProduct(Long clientOrderId) {
        List<OrderProduct> orderProducts = orderProductRepository.findByClientOrderId(clientOrderId);

        return orderProducts.stream()
                .map(OrderProduct::getProduct)
                .collect(Collectors.toList());
    }
}
