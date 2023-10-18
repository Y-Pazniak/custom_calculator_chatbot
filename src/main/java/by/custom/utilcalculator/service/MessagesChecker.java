package by.custom.utilcalculator.service;

import by.custom.utilcalculator.controller.Bot;
import by.custom.utilcalculator.repository.resources.Commands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessagesChecker {
    private final MessagesCreator messagesCreator;
    private MessagesChecker() {
        messagesCreator = MessagesCreator.getInstance();
    }

    public static MessagesChecker getInstance() {
        return MessagesCheckerHolder.MESSAGES_CHECKER;
    }

    public SendMessage checkMessageBeforeExecutionAndGetResult(Update update){
        Message message = update.getMessage();
        if (update.hasMessage() && message.hasText()) {
            return getCheckInputMessageAndGetAnswer(message);
        } else {
            return messagesCreator.getSorryMessage(message);
        }
    }

    public SendMessage getCheckInputMessageAndGetAnswer(Message message) {
        String usersMessage = message.getText();
        SendMessage sendMessage;
        switch (usersMessage) {
            case Commands.START -> sendMessage = messagesCreator.getGreetingMessage(message);
            case Commands.EAES, Commands.OTHER_COUNTRIES -> sendMessage = Bot.getBot().getOriginCarMessage(message);
            case Commands.PHYSICAL_PERSON, Commands.JURIDICAL_PERSON -> sendMessage = Bot.getBot().getTypeOfOwnerMessage(message);
            case Commands.LESS_3_YEARS_AGE, Commands.BETWEEN_3_AND_7_YEARS_AGE, Commands.MORE_7_YEARS_AGE ->
                    sendMessage = Bot.getBot().getAutoAgeMessage(message);
            case Commands.GASOLINE_TYPE_ENGINE, Commands.ELECTRIC_TYPE_ENGINE ->
                    sendMessage = Bot.getBot().getEngineTypeMessage(message);
            case Commands.VOLUME_LESS_1000_CM, Commands.VOLUME_BETWEEN_1000_2000_CM, Commands.VOLUME_BETWEEN_2000_3000_CM,
                    Commands.VOLUME_BETWEEN_3000_3500_CM, Commands.VOLUME_MORE_3500_CM ->
                    sendMessage = Bot.getBot().getEngineVolumeMessage(message);
            default -> sendMessage = messagesCreator.getSorryMessage(message);
        }
        return sendMessage;
    }

    private static class MessagesCheckerHolder {
        private static final MessagesChecker MESSAGES_CHECKER = new MessagesChecker();
    }
}
