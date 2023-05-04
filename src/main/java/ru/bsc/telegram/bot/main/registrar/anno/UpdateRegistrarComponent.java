package ru.bsc.telegram.bot.main.registrar.anno;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import ru.bsc.telegram.bot.main.registrar.type.UpdateRegistrarType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(UpdateRegistrarComponentCondition.class)
public @interface UpdateRegistrarComponent {

	UpdateRegistrarType value();

}
