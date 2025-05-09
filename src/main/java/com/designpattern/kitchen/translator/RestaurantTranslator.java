package com.designpattern.kitchen.translator;

import java.util.HashMap;
import java.util.Map;
import java.util.Currency;
import java.util.Locale;

/**
 * Adapter class that handles translations for the restaurant system
 */
public class RestaurantTranslator implements TranslationService {
    private final Map<String, Map<Language, String>> statusTranslations;
    private final Map<Language, Locale> localeMap;

    public RestaurantTranslator() {
        this.statusTranslations = initializeStatusTranslations();
        this.localeMap = initializeLocaleMap();
    }

    private Map<String, Map<Language, String>> initializeStatusTranslations() {
        Map<String, Map<Language, String>> translations = new HashMap<>();
        
        // Status translations
        Map<Language, String> receivedTranslations = new HashMap<>();
        receivedTranslations.put(Language.ENGLISH, "Order Received");
        receivedTranslations.put(Language.TURKISH, "Sipariş Alındı");
        receivedTranslations.put(Language.JAPANESE, "注文受付");
        translations.put("RECEIVED", receivedTranslations);

        Map<Language, String> preparingTranslations = new HashMap<>();
        preparingTranslations.put(Language.ENGLISH, "Preparing");
        preparingTranslations.put(Language.TURKISH, "Hazırlanıyor");
        preparingTranslations.put(Language.JAPANESE, "準備中");
        translations.put("PREPARING", preparingTranslations);

        Map<Language, String> readyTranslations = new HashMap<>();
        readyTranslations.put(Language.ENGLISH, "Ready");
        readyTranslations.put(Language.TURKISH, "Hazır");
        readyTranslations.put(Language.JAPANESE, "準備完了");
        translations.put("READY", readyTranslations);

        return translations;
    }

    private Map<Language, Locale> initializeLocaleMap() {
        Map<Language, Locale> locales = new HashMap<>();
        locales.put(Language.ENGLISH, new Locale("en", "US"));
        locales.put(Language.TURKISH, new Locale("tr", "TR"));
        locales.put(Language.JAPANESE, new Locale("ja", "JP"));
        return locales;
    }

    @Override
    public String translate(String text, Language targetLanguage) {
        // In a real system, this would use a proper translation service
        return text + " (" + targetLanguage + ")";
    }

    @Override
    public String formatPrice(double price, Language targetLanguage) {
        Locale locale = localeMap.get(targetLanguage);
        Currency currency = Currency.getInstance(locale);
        return String.format(locale, "%.2f %s", price, currency.getSymbol(locale));
    }

    @Override
    public String translateOrderStatus(String status, Language targetLanguage) {
        Map<Language, String> translations = statusTranslations.get(status);
        if (translations != null) {
            String translation = translations.get(targetLanguage);
            if (translation != null) {
                return translation;
            }
        }
        return status; // Fallback to original status if no translation found
    }
} 