package ru.bsc.telegram.bot.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bsc.telegram.bot.main.config.TelegramBotProperties;
import ru.bsc.telegram.bot.main.registrar.UpdateRegistrar;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

	private final TelegramBotProperties properties;
	private final UpdateRegistrar updateRegistrar;

	public TelegramBot(TelegramBotProperties properties, UpdateRegistrar updateRegistrar) {
		super(properties.getToken());
		this.properties = properties;
		this.updateRegistrar = updateRegistrar;
	}

	@Override
	public void onUpdateReceived(Update update) {
		updateRegistrar.registerUpdate(this, update);
	}

	@Override
	public String getBotUsername() {
		return properties.getName();
	}
}
