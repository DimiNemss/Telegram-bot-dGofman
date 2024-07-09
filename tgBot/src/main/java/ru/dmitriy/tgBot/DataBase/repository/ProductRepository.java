package ru.dmitriy.tgBot.DataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.dmitriy.tgBot.DataBase.entity.Product;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long>
{
    @Query("SELECT p FROM Product p " +
            "JOIN OrderProduct op ON p.id = op.product.id " +
            "GROUP BY p.id " +
            "ORDER BY SUM(op.countProduct) DESC")
    List<Product> getTopPopularProducts(Pageable pageable);
}
