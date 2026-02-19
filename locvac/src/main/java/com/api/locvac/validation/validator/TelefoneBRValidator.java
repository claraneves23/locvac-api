package com.api.locvac.validation.validator;

import com.api.locvac.validation.annotation.TelefoneBR;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelefoneBRValidator implements ConstraintValidator<TelefoneBR, String> {

        private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.isBlank()) {
                return true;
            }

            try {
                Phonenumber.PhoneNumber number =
                        phoneUtil.parse(value, "BR");

                return phoneUtil.isValidNumber(number);
            } catch (NumberParseException e) {
                return false;
            }
        }
}
