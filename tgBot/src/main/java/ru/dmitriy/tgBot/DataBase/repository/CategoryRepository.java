package ru.dmitriy.tgBot.DataBase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.dmitriy.tgBot.DataBase.entity.Category;

@RepositoryRestResource(collectionResourceRel =
"categories", path = "categories")

public interface CategoryRepository extends CrudRepository<Category, Long>
{

    
}
