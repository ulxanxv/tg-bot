package ru.bsc.telegram.bot.main.registrar.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.bsc.telegram.bot.main.publisher.UpdatePublisher;
import ru.bsc.telegram.bot.main.registrar.anno.UpdateRegistrarComponent;
import ru.bsc.telegram.bot.main.registrar.UpdateRegistrar;
import ru.bsc.telegram.bot.main.registrar.type.UpdateRegistrarType;

@Slf4j
@RequiredArgsConstructor
@UpdateRegistrarComponent(UpdateRegistrarType.DEFAULT)
public class DefaultUpdateRegistrar implements UpdateRegistrar {

	private final UpdatePublisher updatePublisher;

	@Override
	public void registerUpdate(AbsSender sender, Update update) {
		updatePublisher.publishUpdate(sender, update);
	}
}
