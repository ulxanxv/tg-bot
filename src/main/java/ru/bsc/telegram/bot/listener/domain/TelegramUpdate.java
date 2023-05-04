package ru.bsc.telegram.bot.listener.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Getter
public class TelegramUpdate extends ApplicationEvent {

	private final AbsSender sender;
	private final Update event;

	public TelegramUpdate(Object source, AbsSender sender, Update event) {
		super(source);
		this.sender = sender;
		this.event = event;
	}
}
