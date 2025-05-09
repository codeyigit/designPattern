package com.designpattern.kitchen.translator;

/**
 * Interface for translation services
 */
public interface TranslationService {
    String translate(String text, Language targetLanguage);
    String formatPrice(double price, Language targetLanguage);
    String translateOrderStatus(String status, Language targetLanguage);
} 