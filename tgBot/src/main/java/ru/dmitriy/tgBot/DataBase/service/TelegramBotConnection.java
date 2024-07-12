package ru.dmitriy.tgBot.DataBase.service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;

import jakarta.annotation.PostConstruct;
import ru.dmitriy.tgBot.DataBase.entity.Client;
import ru.dmitriy.tgBot.DataBase.entity.ClientOrder;
import ru.dmitriy.tgBot.DataBase.entity.Product;
import ru.dmitriy.tgBot.DataBase.rest.AppRestController;

@Service
public class TelegramBotConnection
{
    private final AppRestController appRestController;
    private class TelegramUpdatesListener implements UpdatesListener {

        @Override
        public int process(List<Update> updates) {
            updates.forEach(this::processUpdate);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }
        private ClientOrder clientOrder;
        private double totalOrderAmount = 0.0;
        private void processUpdate(Update update) {

            Long id = null;
            String fullName = null;
            String phoneNumber = null;
            if (update.message() != null) {
                id = update.message().from().id();
                fullName = update.message().from().firstName() + " " + update.message().from().lastName();
                phoneNumber = "000";
                if (update.message().contact() != null) {
                    phoneNumber = update.message().contact().phoneNumber();
                }
            } else if (update.callbackQuery() != null) {
                id = update.callbackQuery().from().id();
                fullName = update.callbackQuery().from().firstName() + " " + update.callbackQuery().from().lastName();
                phoneNumber = "000";
            }

            Client client = null;
            if (id != null) {
                client = appRestController.findByByExternalId(id);
                if (client == null) {
                    client = appRestController.postClient(id, fullName, phoneNumber, "Sevastopol");
                }
            }

            if (clientOrder == null) {
                clientOrder = appRestController.postClientOrder(client, 1, 0.0);
            }

            if (update.callbackQuery() != null) {
                String productId = update.callbackQuery().data();
                System.out.println(productId);
                Product product = appRestController.getProductById(Long.parseLong(productId));
                appRestController.postProduct(clientOrder, product, 1);
                String productName = product.getName();
                bot.execute(new SendMessage(update.callbackQuery().from().id(),
                        "Добавлен товар " + productName));
                totalOrderAmount += product.getPrice();
            } else {


                List<KeyboardButton> categories = entitiesService.getCategoriesByParentId(null)
                        .stream()
                        .map(category -> new KeyboardButton(category.getName()))
                        .collect(Collectors.toList());
                ReplyKeyboardMarkup markup = new
                        ReplyKeyboardMarkup(categories.toArray(KeyboardButton[]::new));
                markup.resizeKeyboard(true);
                markup.addRow(new KeyboardButton("Оформить заказ"));
                markup.addRow(new KeyboardButton("В основное меню"));
                    if(update.message() != null) {
                        bot.execute(new SendMessage(update.message().chat().id(), "Запрос").replyMarkup(markup));
                    }
                if(update.message() != null){
                    switch (update.message().text()) {
                        case "В основное меню" -> {
                            getCategoryInfo(null, update);
                        }
                        case "Пицца" -> {
                            getProducts(1L, update);
                        }
                        case "Роллы" -> {
                            getCategoryInfo(2L, update);
                        }
                        case "Бургеры" -> {
                            getCategoryInfo(3L, update);
                        }
                        case "Напитки" -> {
                            getCategoryInfo(4L, update);
                        }
                        case "Классические роллы" -> {
                            getProducts(5L, update);
                        }
                        case "Запечённые роллы" -> {
                            getProducts(6L, update);
                        }
                        case "Сладкие роллы" -> {
                            getProducts(7L, update);
                        }
                        case "Наборы" -> {
                            getProducts(8L, update);
                        }
                        case "Классические бургеры" -> {
                            getProducts(9L, update);
                        }
                        case "Острые бургеры" -> {
                            getProducts(10L, update);
                        }
                        case "Газированные напитки" -> {
                            getProducts(11L, update);
                        }
                        case "Энергитические напитки" -> {
                            getProducts(12L, update);
                        }
                        case "Соки" -> {
                            getProducts(13L, update);
                        }
                        case "Другие" -> {
                            getProducts(14L, update);
                        }
                        case "Оформить заказ" -> {
                            totalPrice(update, totalOrderAmount, clientOrder, client);
                        }
                    }
                }
            }
        }

        private void getCategoryInfo(Long id, Update update) {
            List<KeyboardButton> categories = entitiesService.getCategoriesByParentId(id)
                    .stream()
                    .map(category -> new KeyboardButton(category.getName()))
                    .collect(Collectors.toList());
            ReplyKeyboardMarkup markup = new
                    ReplyKeyboardMarkup(categories.toArray(KeyboardButton[]::new));
            markup.resizeKeyboard(true);
            markup.addRow(new KeyboardButton("Оформить заказ"));
            markup.addRow(new KeyboardButton("В основное меню"));
            bot.execute(new SendMessage(update.message().chat().id(),
                    "Товары").replyMarkup(markup));

        }

        private void getProducts(Long id, Update update) {
            List<Product> products = entitiesService.getProductsByCategoryId(id);
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
            for (Product product : products) {
                InlineKeyboardButton button = new
                        InlineKeyboardButton(String.format("Товар %s. Цена %.2f руб.",
                        product.getName(), product.getPrice()))
                        .callbackData(String.format("%d", product.getId()));
                markup.addRow(button);}
                bot.execute(new SendMessage(update.message().chat().id(),
                        "Товары").replyMarkup(markup));
        }

        private void totalPrice(Update update, double totalOrderAmount, ClientOrder clientOrder, Client client) {
            if (totalOrderAmount == 0.0) {
                bot.execute(new SendMessage(update.message().chat().id(), "Заказ пуст"));
            }
            else {
                List<Product> products = appRestController.getClientProduct(clientOrder.getId());
                String allProductsInOneLine = products.stream()
                        .map(product -> String.format("%s: %.2f руб", product.getName(), product.getPrice()))
                        .collect(Collectors.joining(", "));
                bot.execute(new SendMessage(update.message().chat().id(), "Заказ оформлен "  + allProductsInOneLine + " цена заказа: "
                        + totalOrderAmount));
                clientOrder.setTotal(totalOrderAmount);
                clientOrder.setStatus(2);
                appRestController.updateClientOrder(clientOrder);
                this.clientOrder = appRestController.postClientOrder(client, 1, 0.0);
                this.totalOrderAmount = 0.0;
            }
        }

    }


    private final EntitiesService entitiesService;
    private TelegramBot bot;
    public TelegramBotConnection(AppRestController appRestController, EntitiesService entitiesService)
    {
        this.appRestController = appRestController;
        this.entitiesService = entitiesService;
    }
    @PostConstruct
    public void createConnection()
    {
        bot = new TelegramBot("7121467979:AAEdVx5GzwE0He3LAEGPZUWk6-5q3DqAPHY");
        bot.setUpdatesListener(new TelegramUpdatesListener());
    }
}
