package ru.bsc.telegram.bot.handler.impl.command.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bsc.telegram.bot.handler.impl.command.CommandHandler;
import ru.bsc.telegram.bot.handler.impl.command.domain.CommandUpdate;

import static ru.bsc.telegram.bot.repository.domain.Command.DEFAULT_CODE;

@Component
public class DefaultCommandHandler implements CommandHandler {

	@Override
	public void handle(AbsSender sender, CommandUpdate update) throws TelegramApiException {
		sender.execute(
				SendMessage.builder()
						.chatId(update.getMessage().getChatId())
						.text(update.getCommand().getDescription())
						.build()
		);
	}

	@Override
	public String getSupportedType() {
		return DEFAULT_CODE;
	}
}
