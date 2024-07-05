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

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    
    void createCategory(Category category, String name, Category parent) {
        category.setName(name);
        category.setParent(parent);
        categoryRepository.save(category);
    }
    
    
    void createProduct(Product product, Category category, String name, String descriptions, Double price) {
        product.setCategory(category);
        product.setName(name);
        product.setDescription(descriptions);
        product.setPrice(price);
        productRepository.save(product);   
    }

    @Test
    void creations() {      //Создание категорий и товаров

        Category  pizza = new Category();
        createCategory(pizza, "Пицца", null);

        Category rolls = new Category();
        createCategory(rolls, "Роллы", null);

        Category burger = new Category();
        createCategory(burger, "Бургеры", null);

        Category drink = new Category();
        createCategory(drink, "Напитки", null);

        Category rollClassic = new Category();      
        createCategory(rollClassic, "Классические роллы", rolls);

        Category rollBaked = new Category();
        createCategory(rollBaked, "Запечённые роллы", rolls);

        Category rollSweet = new Category();
        createCategory(rollSweet, "Сладкие роллы", rolls);

        Category rollKits = new Category();
        createCategory(rollKits, "Наборы", rolls);

        Category burgerClassic = new Category();       
        createCategory(burgerClassic, "Классические бургеры", burger);

        Category burgerSpicy = new Category();
        createCategory(burgerSpicy, "Острые бургеры", burger);

        Category drinkSoda = new Category();        
        createCategory(drinkSoda, "Газированные напитки", drink);

        Category drinkEnergy = new Category();
        createCategory(drinkEnergy, "Энергтические напитки", drink);

        Category drinkJuice = new Category();
        createCategory(drinkJuice, "Соки", drink);

        Category drinkOther = new Category();
        createCategory(drinkOther, "Другие", drink);

        //Пицца
        Product pizza1 = new Product();
        createProduct(pizza1, pizza, "Пеперони", "Это пеперони", 500.50);

        Product pizza2 = new Product();
        createProduct(pizza2, pizza, "Маргарита", "Это Маргарита", 400.00);

        Product pizza3 = new Product();
        createProduct(pizza3, pizza, "Гавайская", "Это Гавайская", 450.75);

        // Классические роллы
        Product rollClassic1 = new Product();
        createProduct(rollClassic1, rollClassic, "Филадельфия", "Классический ролл с лососем и сливочным сыром", 300.00);

        Product rollClassic2 = new Product();
        createProduct(rollClassic2, rollClassic, "Калифорния", "Ролл с крабом и авокадо", 280.00);

        Product rollClassic3 = new Product();
        createProduct(rollClassic3, rollClassic, "Спайси тунец", "Ролл с острым тунцом", 320.00);

        // Запечённые роллы
        Product rollBaked1 = new Product();
        createProduct(rollBaked1, rollBaked, "Запечённая Филадельфия", "Запечённый ролл с лососем и сливочным сыром", 350.00);

        Product rollBaked2 = new Product();
        createProduct(rollBaked2, rollBaked, "Запечённый краб", "Запечённый ролл с крабовым мясом", 370.00);

        Product rollBaked3 = new Product();
        createProduct(rollBaked3, rollBaked, "Запечённый угорь", "Запечённый ролл с угрём", 390.00);

        // Сладкие роллы
        Product rollSweet1 = new Product();
        createProduct(rollSweet1, rollSweet, "Шоколадный ролл", "Сладкий ролл с шоколадом и бананом", 200.00);

        Product rollSweet2 = new Product();
        createProduct(rollSweet2, rollSweet, "Фруктовый ролл", "Ролл с манго и клубникой", 220.00);

        Product rollSweet3 = new Product();
        createProduct(rollSweet3, rollSweet, "Карамельный ролл", "Ролл с карамелью и яблоком", 210.00);

        // Наборы роллов
        Product rollKit1 = new Product();
        createProduct(rollKit1, rollKits, "Набор ассорти", "Ассорти из различных роллов", 600.00);

        Product rollKit2 = new Product();
        createProduct(rollKit2, rollKits, "Набор делюкс", "Набор премиальных роллов", 800.00);

        Product rollKit3 = new Product();
        createProduct(rollKit3, rollKits, "Набор классика", "Набор классических роллов", 700.00);

        // Классические бургеры
        Product burgerClassic1 = new Product();
        createProduct(burgerClassic1, burgerClassic, "Чизбургер", "Классический чизбургер с говядиной", 150.00);

        Product burgerClassic2 = new Product();
        createProduct(burgerClassic2, burgerClassic, "Биг Мак", "Классический бургер с двумя котлетами", 180.00);

        Product burgerClassic3 = new Product();
        createProduct(burgerClassic3, burgerClassic, "Гамбургер", "Классический гамбургер с говяжьей котлетой", 140.00);

        // Острые бургеры
        Product burgerSpicy1 = new Product();
        createProduct(burgerSpicy1, burgerSpicy, "Спайси бургер", "Острый бургер с халапеньо и соусом", 160.00);

        Product burgerSpicy2 = new Product();
        createProduct(burgerSpicy2, burgerSpicy, "Техасский бургер", "Острый бургер с барбекю соусом", 170.00);

        Product burgerSpicy3 = new Product();
        createProduct(burgerSpicy3, burgerSpicy, "Пожарный бургер", "Бургер с острым соусом и перцем чили", 180.00);

        // Газированные напитки
        Product drinkSoda1 = new Product();
        createProduct(drinkSoda1, drinkSoda, "Кока-Кола", "Классическая газировка", 50.00);

        Product drinkSoda2 = new Product();
        createProduct(drinkSoda2, drinkSoda, "Пепси", "Популярная газировка", 55.00);

        Product drinkSoda3 = new Product();
        createProduct(drinkSoda3, drinkSoda, "Фанта", "Газировка с апельсиновым вкусом", 60.00);

        // Энергетические напитки
        Product drinkEnergy1 = new Product();
        createProduct(drinkEnergy1, drinkEnergy, "Red Bull", "Энергетический напиток", 80.00);

        Product drinkEnergy2 = new Product();
        createProduct(drinkEnergy2, drinkEnergy, "Monster", "Энергетический напиток", 85.00);

        Product drinkEnergy3 = new Product();
        createProduct(drinkEnergy3, drinkEnergy, "Burn", "Энергетический напиток", 90.00);

        // Сок
        Product drinkJuice1 = new Product();
        createProduct(drinkJuice1, drinkJuice, "Апельсиновый сок", "Сок из свежих апельсинов", 40.00);

        Product drinkJuice2 = new Product();
        createProduct(drinkJuice2, drinkJuice, "Яблочный сок", "Сок из свежих яблок", 45.00);

        Product drinkJuice3 = new Product();
        createProduct(drinkJuice3, drinkJuice, "Вишнёвый сок", "Сок из свежей вишни", 50.00);

        // Другие напитки
        Product drinkOther1 = new Product();
        createProduct(drinkOther1, drinkOther, "Чай", "Чёрный чай", 30.00);

        Product drinkOther2 = new Product();
        createProduct(drinkOther2, drinkOther, "Кофе", "Свежезаваренный кофе", 35.00);

        Product drinkOther3 = new Product();
        createProduct(drinkOther3, drinkOther, "Молочный коктейль", "Коктейль с ванильным вкусом", 40.00);
    }
} 
