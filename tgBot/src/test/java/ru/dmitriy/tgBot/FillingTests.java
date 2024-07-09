package ru.dmitriy.tgBot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.dmitriy.tgBot.DataBase.entity.*;
import ru.dmitriy.tgBot.DataBase.repository.*;

@SpringBootTest
class FillingTests {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    private Category createCategory(String name, Category parent) {
        Category category = new Category();
        category.setName(name);
        category.setParent(parent);
        categoryRepository.save(category);
        return category;
    }

    private Product createProduct(Category category, String name, String description, Double price) {
        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        productRepository.save(product);
        return product;
    }

    private Client createClient(Long externalId, String fullName, String phoneNumber, String address) {
        Client client = new Client();
        client.setExternalId(externalId);
        client.setFullName(fullName);
        client.setPhoneNumber(phoneNumber);
        client.setAddress(address);
        clientRepository.save(client);
        return client;
    }

    private ClientOrder createOrder(Client client, Integer status, Double total) {
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setClient(client);
        clientOrder.setStatus(status);
        clientOrder.setTotal(total);
        clientOrderRepository.save(clientOrder);
        return clientOrder;
    }

    private void createOrderProduct(ClientOrder clientOrder, Product product, Integer countProduct) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setClientOrder(clientOrder);
        orderProduct.setProduct(product);
        orderProduct.setCountProduct(countProduct);
        orderProductRepository.save(orderProduct);
    }

    @Test
    void creations() {      //Создание категорий и товаров

        Category pizza = createCategory("Пицца", null);
        Category rolls = createCategory("Роллы", null);
        Category burger = createCategory("Бургеры", null);
        Category drink = createCategory("Напитки", null);

        Category rollClassic = createCategory("Классические роллы", rolls);
        Category rollBaked = createCategory("Запечённые роллы", rolls);
        Category rollSweet = createCategory("Сладкие роллы", rolls);
        Category rollKits = createCategory("Наборы", rolls);

        Category burgerClassic = createCategory("Классические бургеры", burger);
        Category burgerSpicy = createCategory("Острые бургеры", burger);

        Category drinkSoda = createCategory("Газированные напитки", drink);
        Category drinkEnergy = createCategory("Энергетические напитки", drink);
        Category drinkJuice = createCategory("Соки", drink);
        Category drinkOther = createCategory("Другие", drink);

        //Пицца
        Product product1 = createProduct(pizza, "Пеперони", "Это пеперони", 500.50);
        createProduct(pizza, "Маргарита", "Это Маргарита", 400.00);
        createProduct(pizza, "Гавайская", "Это Гавайская", 450.75);

        // Классические роллы
        Product product2 = createProduct(rollClassic, "Филадельфия", "Классический ролл с лососем и сливочным сыром", 300.00);
        createProduct(rollClassic, "Калифорния", "Ролл с крабом и авокадо", 280.00);
        createProduct(rollClassic, "Спайси тунец", "Ролл с острым тунцом", 320.00);

        // Запечённые роллы
        Product product3 = createProduct(rollBaked, "Запечённая Филадельфия", "Запечённый ролл с лососем и сливочным сыром", 350.00);
        createProduct(rollBaked, "Запечённый краб", "Запечённый ролл с крабовым мясом", 370.00);
        createProduct(rollBaked, "Запечённый угорь", "Запечённый ролл с угрём", 390.00);

        // Сладкие роллы
        createProduct(rollSweet, "Шоколадный ролл", "Сладкий ролл с шоколадом и бананом", 200.00);
        createProduct(rollSweet, "Фруктовый ролл", "Ролл с манго и клубникой", 220.00);
        createProduct(rollSweet, "Карамельный ролл", "Ролл с карамелью и яблоком", 210.00);

        // Наборы роллов
        createProduct(rollKits, "Набор ассорти", "Ассорти из различных роллов", 600.00);
        createProduct(rollKits, "Набор делюкс", "Набор премиальных роллов", 800.00);
        createProduct(rollKits, "Набор классика", "Набор классических роллов", 700.00);

        // Классические бургеры
        createProduct(burgerClassic, "Чизбургер", "Классический чизбургер с говядиной", 150.00);
        createProduct(burgerClassic, "Биг Мак", "Классический бургер с двумя котлетами", 180.00);
        createProduct(burgerClassic, "Гамбургер", "Классический гамбургер с говяжьей котлетой", 140.00);

        // Острые бургеры
        createProduct(burgerSpicy, "Спайси бургер", "Острый бургер с халапеньо и соусом", 160.00);
        createProduct(burgerSpicy, "Техасский бургер", "Острый бургер с барбекю соусом", 170.00);
        createProduct(burgerSpicy, "Пожарный бургер", "Бургер с острым соусом и перцем чили", 180.00);

        // Газированные напитки
        createProduct(drinkSoda, "Кока-Кола", "Классическая газировка", 50.00);
        createProduct(drinkSoda, "Пепси", "Популярная газировка", 55.00);
        createProduct(drinkSoda, "Фанта", "Газировка с апельсиновым вкусом", 60.00);

        // Энергетические напитки
        createProduct(drinkEnergy, "Red Bull", "Энергетический напиток", 80.00);
        createProduct(drinkEnergy, "Monster", "Энергетический напиток", 85.00);
        createProduct(drinkEnergy, "Burn", "Энергетический напиток", 90.00);

        // Сок
        createProduct(drinkJuice, "Апельсиновый сок", "Сок из свежих апельсинов", 40.00);
        createProduct(drinkJuice, "Яблочный сок", "Сок из свежих яблок", 45.00);
        createProduct(drinkJuice, "Вишнёвый сок", "Сок из свежей вишни", 50.00);

        // Другие напитки
        createProduct(drinkOther, "Чай", "Чёрный чай", 30.00);
        createProduct(drinkOther, "Кофе", "Свежезаваренный кофе", 35.00);
        createProduct(drinkOther, "Молочный коктейль", "Коктейль с ванильным вкусом", 40.00);

        //Создание клиентов
        Client client1 = createClient(111L, "Adam", "111", "a");
        Client client2 = createClient(222L, "Bob", "222", "b");
        Client client3 = createClient(333L, "Cris", "222", "c");

        //Создание заказов
        ClientOrder order1 = createOrder(client1, 1, 100.0);
        ClientOrder order2 = createOrder(client1, 1, 500.0);
        ClientOrder order3 = createOrder(client1, 1, 600.0);

        ClientOrder order4 = createOrder(client2, 1, 1000.0);
        ClientOrder order5 = createOrder(client2, 1, 1200.0);

        ClientOrder order6 = createOrder(client3, 1, 10000.0);

        //Создание продуктов в заказах
        createOrderProduct(order1, product1, 2);
        createOrderProduct(order1, product2, 1);
        createOrderProduct(order1, product3, 5);
        createOrderProduct(order2, product1, 1);
        createOrderProduct(order2, product2, 2);
        createOrderProduct(order3, product3, 2);

        createOrderProduct(order4, product1, 2);
        createOrderProduct(order4, product2, 1);
        createOrderProduct(order4, product3, 5);
        createOrderProduct(order5, product1, 1);
        createOrderProduct(order5, product2, 2);

        createOrderProduct(order6, product1, 1);
        createOrderProduct(order6, product2, 2);
    }
}
