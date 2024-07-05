package ru.dmitriy.tgBot.DataBase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dmitriy.tgBot.DataBase.entity.ClientOrder;

@RepositoryRestResource(collectionResourceRel =
"client_orders", path = "client_orders")

public interface ClientOrderRepository extends CrudRepository<ClientOrder, Long>
{

    
}
