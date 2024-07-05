package ru.dmitriy.tgBot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.dmitriy.tgBot.DataBase.repository.CategoryRepository;
import ru.dmitriy.tgBot.DataBase.repository.ClientRepository;
import ru.dmitriy.tgBot.DataBase.repository.ProductRepository;
import ru.dmitriy.tgBot.DataBase.entity.Category;
import ru.dmitriy.tgBot.DataBase.entity.Client;
import ru.dmitriy.tgBot.DataBase.entity.Product;

@SpringBootTest
class FillingTests {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createTwoClients() {
        Client client1 = new Client();
        client1.setAddress("address1");
        client1.setExternalId(1L);
        client1.setFullName("fullName1");
        client1.setPhoneNumber("1111111");
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setAddress("address2");
        client2.setExternalId(2L);
        client2.setFullName("fullName2");
        client2.setPhoneNumber("2222222");
        clientRepository.save(client2);
    }

    private Category createCategory(String name, Category parent) {
        Category category = new Category();
        category.setName(name);
        category.setParent(parent);
        categoryRepository.save(category);
        return category;
    }

    private void createProduct(Category category, String name, String description, Double price) {
        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        productRepository.save(product);   
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
        createProduct(pizza, "Пеперони", "Это пеперони", 500.50);
        createProduct(pizza, "Маргарита", "Это Маргарита", 400.00);
        createProduct(pizza, "Гавайская", "Это Гавайская", 450.75);

        // Классические роллы
        createProduct(rollClassic, "Филадельфия", "Классический ролл с лососем и сливочным сыром", 300.00);
        createProduct(rollClassic, "Калифорния", "Ролл с крабом и авокадо", 280.00);
        createProduct(rollClassic, "Спайси тунец", "Ролл с острым тунцом", 320.00);

        // Запечённые роллы
        createProduct(rollBaked, "Запечённая Филадельфия", "Запечённый ролл с лососем и сливочным сыром", 350.00);
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
    }
}
