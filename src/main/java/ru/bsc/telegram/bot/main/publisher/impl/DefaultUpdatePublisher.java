package ru.bsc.telegram.bot.main.publisher.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bsc.telegram.bot.listener.domain.TelegramUpdate;
import ru.bsc.telegram.bot.main.publisher.UpdatePublisher;

@Component
@RequiredArgsConstructor
public class DefaultUpdatePublisher implements UpdatePublisher {

	private final ApplicationEventPublisher eventPublisher;

	@Override
	public void publishUpdate(AbsSender sender, Update update) {
		eventPublisher.publishEvent(new TelegramUpdate(this, sender, update));
	}
}
