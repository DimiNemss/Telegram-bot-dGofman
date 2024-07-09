package ru.dmitriy.tgBot.DataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.dmitriy.tgBot.DataBase.entity.Client;
import ru.dmitriy.tgBot.DataBase.entity.Product;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")
public interface ClientRepository extends JpaRepository<Client, Long>
{


}
