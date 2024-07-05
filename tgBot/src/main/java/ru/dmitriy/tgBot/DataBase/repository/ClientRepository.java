package ru.dmitriy.tgBot.DataBase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.dmitriy.tgBot.DataBase.entity.Client;

@RepositoryRestResource(collectionResourceRel =
"clients", path = "clients")

public interface ClientRepository extends CrudRepository<Client, Long>
{

    
}
