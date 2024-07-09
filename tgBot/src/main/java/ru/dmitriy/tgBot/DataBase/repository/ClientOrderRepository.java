package ru.dmitriy.tgBot.DataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dmitriy.tgBot.DataBase.entity.ClientOrder;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "clientOrders", path = "clientOrders")
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long>
{
    List<ClientOrder> findByClientId(Long clientId);
}
