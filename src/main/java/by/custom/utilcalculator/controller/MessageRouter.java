package by.custom.utilcalculator.controller;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.service.UserProgressManager;
import by.custom.utilcalculator.service.MessagesCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageRouter {
    private final MessagesCreator messagesCreator;
    private final UserProgressManager botFieldsManager;

    private MessageRouter() {
        messagesCreator = MessagesCreator.getInstance();
        botFieldsManager = UserProgressManager.getInstance();
    }

    public static MessageRouter getInstance() {
        return MessagesCheckerHolder.MESSAGES_CHECKER;
    }

    public SendMessage route(Update update) {
        Message message = update.getMessage();
        if (!message.hasText()) {
            SendMessage sorrySendMessage = new SendMessage();
            sorrySendMessage.setChatId(update.getMessage().getChatId().toString());
            sorrySendMessage.setText(getSorryMessage());
            return sorrySendMessage;
        }
        return getCheckInputMessageAndGetAnswer(message);
    }

    //we receive message from user, check it and handle it if it is ok here
    private SendMessage getCheckInputMessageAndGetAnswer(Message message) {
        String usersMessage = message.getText();
        String userID = message.getChatId().toString();
        UserProgress userProgress = UserStorageManager.getInstance().getUserProgress(userID);
        String answer;

        switch (usersMessage) {
            case Command.START -> answer = getGreetingMessage();
            case Command.EAES, Command.OTHER_COUNTRIES ->
                    answer = botFieldsManager.processCarOrigin(usersMessage, userProgress);
            case Command.PHYSICAL_PERSON, Command.JURIDICAL_PERSON ->
                    answer = botFieldsManager.processOwnerType(usersMessage, userProgress);
            case Command.LESS_3_YEARS_AGE, Command.BETWEEN_3_AND_7_YEARS_AGE, Command.MORE_7_YEARS_AGE ->
                    answer = botFieldsManager.processCarAge(usersMessage, userProgress);
            case Command.GASOLINE_TYPE_ENGINE, Command.ELECTRIC_TYPE_ENGINE ->
                    answer = botFieldsManager.processEngineType(usersMessage, userProgress);
            case Command.VOLUME_LESS_1000_CM, Command.VOLUME_BETWEEN_1000_2000_CM, Command.VOLUME_BETWEEN_2000_3000_CM,
                    Command.VOLUME_BETWEEN_3000_3500_CM, Command.VOLUME_MORE_3500_CM ->
                    answer = botFieldsManager.processEngineVolume(usersMessage, userProgress);
            default -> answer = getSorryMessage();
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userID);
        sendMessage.setText(answer);
        return sendMessage;
    }

    public String getGreetingMessage() {
        return messagesCreator.getGreeting();
    }

    public String getSorryMessage() {
        return messagesCreator.getSorry();
    }

    private static class MessagesCheckerHolder {
        private static final MessageRouter MESSAGES_CHECKER = new MessageRouter();
    }
}
