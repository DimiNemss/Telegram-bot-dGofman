package ru.dmitriy.tgBot.DataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.dmitriy.tgBot.DataBase.entity.ClientOrder;
import ru.dmitriy.tgBot.DataBase.entity.OrderProduct;
import ru.dmitriy.tgBot.DataBase.entity.Product;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orderProducts", path = "orderProducts")
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>
{
    @Query("SELECT DISTINCT op.product FROM OrderProduct op " +
            "JOIN op.clientOrder co " +
            "WHERE co.client.id = :clientId")
    List<Product> getClientProducts(@Param("clientId") Long clientId);

    List<OrderProduct> findByClientOrderIn(List<ClientOrder> clientOrders);
}
