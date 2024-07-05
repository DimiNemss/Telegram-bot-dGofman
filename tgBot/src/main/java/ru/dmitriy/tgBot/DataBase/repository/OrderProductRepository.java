package ru.dmitriy.tgBot.DataBase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.dmitriy.tgBot.DataBase.entity.OrderProduct;

@RepositoryRestResource(collectionResourceRel =
"order_products", path = "order_products")

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long>
{

    
}
