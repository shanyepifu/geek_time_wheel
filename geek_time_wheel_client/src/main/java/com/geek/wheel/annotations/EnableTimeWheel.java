package com.geek.wheel.annotations;


import com.geek.wheel.configuration.TimeWheelTaskConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({TimeWheelTaskConfiguration.class})
public @interface EnableTimeWheel {
}
