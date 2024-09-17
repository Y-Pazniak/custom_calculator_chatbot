package by.custom.utilcalculator.controller;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.exception.UtilsborException;
import by.custom.utilcalculator.service.BundleResourcesServant;
import by.custom.utilcalculator.service.UserProgressManager;
import by.custom.utilcalculator.service.MessagesCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageRouter {
    private final MessagesCreator messagesCreator;
    private final UserProgressManager userProgressManager;

    private MessageRouter() {
        messagesCreator = MessagesCreator.getInstance();
        userProgressManager = UserProgressManager.getInstance();
    }

    public static MessageRouter getInstance() {
        return MessagesRouterHolder.MESSAGES_ROUTER;
    }

    public SendMessage route(final Update update) {
        final Message message = update.getMessage();
        if (!message.hasText()) {
            final SendMessage sorrySendMessage = new SendMessage();
            sorrySendMessage.setChatId(update.getMessage().getChatId().toString());
            sorrySendMessage.setText(getSorryMessage());
            return sorrySendMessage;
        }
        return getCheckInputMessageAndGetAnswer(message);
    }

    //we receive message from user, check it and handle it if it is ok here
    private SendMessage getCheckInputMessageAndGetAnswer(final Message message) {
        final String usersMessage = message.getText();
        final String chatID = message.getChatId().toString();
        final String answer;
        try {
            answer = route(usersMessage, chatID);
        } catch (final UtilsborException e) {
            e.printStackTrace();
            return new SendMessage(chatID, getExceptionText(e));
        }
        return new SendMessage(chatID, answer);
    }

    private String route(final String usersMessage, final String chatID) throws UtilsborException {
        final String answer;
        Command requestingCommand = Command.getCommandByKey(usersMessage);
        switch (requestingCommand) {
            case START -> {
                userProgressManager.createNewUserProgress(chatID);
                answer = getGreetingMessage();
            }
            case M1, BUSES_AND_TRUCKS, SELF_PROPELLED_VEHICLES ->
                    answer = userProgressManager.processGeneralTransportType(requestingCommand, chatID);
            case EAES, OTHER_COUNTRIES -> answer = userProgressManager.processCarOrigin(requestingCommand, chatID);
            case PHYSICAL_PERSON, JURIDICAL_PERSON_EAES, JURIDICAL_PERSON_OTHER ->
                    answer = userProgressManager.processOwnerType(requestingCommand, chatID);
            case LESS_3_YEARS_AGE, MORE_THAN_3_YEARS_AGE ->
                    answer = userProgressManager.processCarAge(requestingCommand, chatID);
            case GASOLINE_TYPE_ENGINE_M1, ELECTRIC_TYPE_ENGINE_M1, ELECTRIC_TYPE_ENGINE_BUSES,
                 GASOLINE_TYPE_ENGINE_BUSES ->
                    answer = userProgressManager.processEngineType(requestingCommand, chatID);
            case M1_VOLUME_LESS_1000_CM, M1_VOLUME_BETWEEN_1000_2000_CM, M1_VOLUME_BETWEEN_2000_3000_CM,
                 M1_VOLUME_BETWEEN_3000_3500_CM, M1_VOLUME_MORE_3500_CM, M2_VOLUME_LESS_2500_CM,
                 M2_VOLUME_BETWEEN_2500_5000_CM, M2_VOLUME_BETWEEN_5000_10000_CM, M2_VOLUME_MORE_10000_CM,
                 POWER_LESS_100, POWER_100_140, POWER_140_200, POWER_MORE_200, POWER_100_200, POWER_200_300,
                 POWER_300_400, POWER_MORE_400, POWER_LESS_170, POWER_170_250, POWER_BETWEEN_100_220, POWER_MORE_220,
                 POWER_MORE_250, POWER_100_125, POWER_BETWEEN_20_100, POWER_BETWEEN_100_300,
                 POWER_125_150, POWER_MORE_150, POWER_LESS_40, POWER_40_80, POWER_MORE_80, POWER_5_50, POWER_50_100,
                 POWER_200_250, POWER_250_300, POWER_LESS_130, POWER_130_200, POWER_MORE_300, TRAILERS_OTHER_FULL,
                 TRAILERS_OTHER_HALF, POWER_BETWEEN_5p5_30, POWER_BETWEEN_30_60, POWER_BETWEEN_60_90,
                 POWER_BETWEEN_90_130, POWER_BETWEEN_130_180, POWER_BETWEEN_180_220, POWER_BETWEEN_220_280,
                 POWER_BETWEEN_280_340, POWER_BETWEEN_340_380, POWER_MORE_380 ->
                    answer = userProgressManager.processEngineVolumeOrPower(requestingCommand, chatID);
            case N1_N3, M2_M3, TRUCK_UNITS, TRAILERS_O4 ->
                    answer = userProgressManager.processN1_N3TransportType(requestingCommand, chatID);
            case LESS_2_TONS, BETWEEN_2_5_AND_3_5_TONS, BETWEEN_3_5_AND_5_TONS, BETWEEN_5_AND_8_TONS,
                 BETWEEN_8_AND_12_TONS,
                 BETWEEN_12_AND_20_TONS, BETWEEN_20_AND_50_TONS ->
                    answer = userProgressManager.processN1_N3TransportWeight(requestingCommand, chatID);
            case TRUCK_UNITS_6_CLASS, TRUCK_UNITS_OTHER ->
                    answer = userProgressManager.processTruckUnitClass(requestingCommand, chatID);
            case TRUCK_UNITS_12_20_TONS, TRUCK_UNITS_20_50_TONS ->
                    answer = userProgressManager.processTruckUnitWeight(requestingCommand, chatID);
            case TRAILERS_04_TYPE, HALF_TRAILERS_04_TYPE ->
                    answer = userProgressManager.processTrailersO4Type(requestingCommand, chatID);
            case GRADERS, BULLDOZERS, EXCAVATORS, WHEEL_LOADERS, TAMPING_MACHINES, FRONT_LOADERS, WHEELED_CRANES,
                 PIPELAYERS, TRAILERS_OTHER, ROAD_MAINTENANCE, FORESTRY, FORWADERS, TIMBER_LOADERS, WHEELED_TRACTORS, CRAWLER_TRACTORS ->
                    answer = userProgressManager.processSelfPropelledType(requestingCommand, chatID);
            case HELP -> answer = userProgressManager.processHelpRequest(chatID);
            case null, default -> answer = getSorryMessage();
        }
        return answer;
    }

    public String getGreetingMessage() {
        return messagesCreator.getGreeting();
    }

    public String getSorryMessage() {
        return messagesCreator.getSorry();
    }

    private String getExceptionText(final UtilsborException e) {
        return BundleResourcesServant.getInstance().getString("answers." + e.getErrorCode());
    }

    private static class MessagesRouterHolder {
        private static final MessageRouter MESSAGES_ROUTER = new MessageRouter();
    }
}
