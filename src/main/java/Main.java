import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);//создаём объект api бота telegram
            botsApi.registerBot(new MyAmazingBot());//регистрируем в нем нашего бота, который тут же и создаём
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}