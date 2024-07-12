package ru.dmitriy.tgBot.DataBase.service;

import org.hibernate.query.Order;
import ru.dmitriy.tgBot.DataBase.entity.*;

import java.util.List;
/**
 * Сервис для работы с сущностями телеграмм-бота
 */
public interface EntitiesService
{
    /**
     * Найти товар по Id
     * @param id Id товара
     */
    Product getProductById(Long id);
    /**
     * Существует ли товар в заказе
     * @param product Требуемый товар
     */
    boolean existsByProduct(Product product);
    /**
     * Добавить товар к заказу
     * @param clientOrder КлиентЮ сделавший заказ
     * @param product
     */
    OrderProduct postProduct(ClientOrder clientOrder, Product product, Integer count);
    /**
     * Существует ли клиент с указанным externalId
     * @param id
     */
    Client findByExternalId(Long id);
    /**
     * Создать заказ
     * @param client Клиент, сделавший заказ
     * @param status Статус заказа
     * @param total Цена заказа
     */
    ClientOrder postOrder(Client client, Integer status, Double total);
    /**
     * Создать клиента
     * @param id идентификатор клиента
     * @param fullName Имя и фамилия клиента
     * @param phoneNumber Номер телефона клиента
     * @param address Адресс клиента
     */
    Client postClient(Long id, String fullName, String phoneNumber, String address);
    /**
     * Получить список категорий по ID родителя
     * @param id идентификатор родителя
     */  
    List<Category> getCategoriesByParentId(Long id);
    /**
     * Получить список товаров в категории
     * @param id идентификатор категории
     */
    List<Product> getProductsByCategoryId(Long id);
    /**
     * Получить список заказов клиента
     * @param id идентификатор клиента
     */
    List<ClientOrder> getClientOrders(Long id);
    /**
     * Получить список всех товаров во всех заказах клиента
     * @param id идентификатор клиента
     */
    List<Product> getClientProducts(Long id);
    /**
     * Получить указанное кол-во самых популярных (наибольшее
     * количество штук в заказах) товаров среди клиентов
     * @param limit максимальное кол-во товаров
     */
    List<Product> getTopPopularProducts(Integer limit);
    /**
     * Найти всех клиентов по подстроке имени
     * @param name подстрока имени клиента
     */
    default List<Client> searchClientsByName(String name) {
        throw new UnsupportedOperationException("Доп. задание");
    }
    /**
     * Найти все продукты по подстроке названия
     * @param name подстрока названия продукта
     */
    default List<Product> searchProductsByName(String name) {
        throw new UnsupportedOperationException("Доп. задание");
    }
}