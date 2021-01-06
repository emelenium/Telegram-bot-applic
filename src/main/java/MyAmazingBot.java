import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

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
        SendMessage sendMessage = new SendMessage();//инициализация, отправляемого сообщения, создаем объект SendMessage библиотеки telegram
        sendMessage.enableMarkdown(true);//включаем возможность разметки
        sendMessage.setChatId(message.getChatId().toString());//устанавливаем id чата в который будем
        sendMessage.setReplyToMessageId(message.getMessageId());//установка id сообщения, на которое будем отвечать
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);//добавляем в отправляемое сообщение клавиатуру, созданную в нашем методе
            execute(sendMessage);//непосредственная реализация отправки сообщения
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();//создаем объект клавиатуру
        sendMessage.setReplyMarkup(replyKeyboardMarkup);//устанавливаем разметку для нашей клавиатуры
        replyKeyboardMarkup.setSelective(true);//устанавливаем параметр, который определяет вывод клавиатуры для всех пользователей (или для одного конкретного)
        replyKeyboardMarkup.setResizeKeyboard(true);//возможность изменять клавиатуру
        replyKeyboardMarkup.setOneTimeKeyboard(true);//скрывать или не скрывать клавиатуру после использования
        KeyboardRow keyboardFirstRow = new KeyboardRow();//создание объекта строчки кнопок
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/settings"));
        List<KeyboardRow> keyboardRowList= new ArrayList<>();//создаем List кнопок клавиатуры
        keyboardRowList.add(keyboardFirstRow);//добавляем первую строчку кнопок
        replyKeyboardMarkup.setKeyboard(keyboardRowList);//устанавливаем клавиатуру, определенную в List(е)



    }
}