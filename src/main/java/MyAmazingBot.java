import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;

public class MyAmazingBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "onlineOrenburgBot";
    }

    @Override
    public String getBotToken() {
        return "1495728727:AAHIKsUU4gnW6RfSO64WXMwKu89ifwR6bYs";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();//создается объект телеграмовское соощение из переданного update
        if ((message !=null && message.hasText())) {//если сообщение не пустое и имеется текст, то
            switch (message.getText()){
                case "/start":
                    sendMsg(message, "Старт");
                    break;
                case "/help":
                    sendMsg(message, "Helpin");
                default:

            }
        }
    }
    public synchronized void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);//включаем возможность разметки
        sendMessage.setChatId(message.getChatId().toString());//устанавливаем id чата в который будем
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
             execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        //    log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    private void sendMessage(SendMessage sendMessage) {
    }
}