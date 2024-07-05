package ru.dmitriy.tgBot.DataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dmitriy.tgBot.DataBase.entity.ClientOrder;

@RepositoryRestResource(collectionResourceRel =
"client_orders", path = "client_orders")

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long>
{

    
}
