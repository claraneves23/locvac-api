package com.api.locvac.validation.annotation;

import com.api.locvac.validation.validator.PeloMenosUmCampoValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeloMenosUmCampoValidator.class)
public @interface PeloMenosUmCampo {
}
