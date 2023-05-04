package ru.bsc.telegram.bot.handler.impl.command;

import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bsc.telegram.bot.handler.impl.command.domain.CommandUpdate;
import ru.bsc.telegram.bot.repository.domain.Command;

import java.util.List;
import java.util.Map;

public interface CommandHandler {

	void handle(AbsSender sender, CommandUpdate update) throws TelegramApiException;

	String getSupportedType();

}
