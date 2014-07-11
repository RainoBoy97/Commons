package me.raino.commons.messaging.localization;

public enum Language {

    AFRIKAANS("Afrikaans", "af_ZA"),
    AMERICAN_ENGLISH("English (US)", "en_US"),
    ARABIC("اللغة العربية", "ar_SA"),
    ARGENTINIAN_ENGLISH("Español (Ar)", "es_AR"),
    ARMENIAN("Հայերեն", "hy_AM"),
    AUSTRALIAN_ENGLISH("Australian English", "en_AU"),
    BASQUE("Euskara", "eu_ES"),
    BRAZILIAN_PORTUGUESE("Português (Br)", "pt_BR"),
    BRITISH_ENGLISH("English (UK)", "en_GB"),
    BULGARIAN("Български", "bg_BG"),
    CANADIAN_ENGLISH("Canadian English", "en_CA"),
    CANADIAN_FRENCH("Français (Ca)", "fr_CA"),
    CATALAN("Català", "ca_ES"),
    CHINESE_SIMPLIFIED("简体中文", "zh_CH"),
    CHINESE_TRADITIONAL("繁體中文", "zh_TW"),
    CORNWALL("Kernowek", "kw_GB"),
    CROATIAN("Hrvatski", "hr_HR"),
    CZECH("Čeština", "cs_CZ"),
    DANISH("Dansk", "da_DK"),
    DUTCH("Nederlands", "nl_NL"),
    ESPERANTO("Esperanto (Mondo)", "eo_UY"),
    ESTONIAN("Eesti", "et_EE"),
    FINNISH("Suomi", "fi_FI"),
    FRENCH("Français (Fr)", "fr_FR"),
    GALICIAN("Galego", "gl_ES"),
    GEORGIAN("ქართული", "ka_GE"),
    GERMAN("Deutsch", "da_DE"),
    GREEK("Ελληνικά", "el_GR"),
    HEBREW("עברית", "hr_HR"),
    HINDI("हिन्दी", "hi_IN"),
    HUNGARIAN("Magyar", "hu_HU"),
    ICELANDIC("Íslenska", "is_IS"),
    INDONESIAN("Bahasa Indonesia", "id_ID"),
    IRISH("Gaeilge", "ga_IE"),
    ITALIAN("Italiano", "it_IT"),
    JAPANESE("日本語", "ja_JP"),
    KLINGON("tlhIngan Hol", "tlh_AA"),
    KOREAN("한국어", "ko_KR"),
    LATIN("Lingua Latina", "la_VA"),
    LATVIAN("Latviešu", "lv_LV"),
    LITHUANIAN("Lietuvių", "lt_LT"),
    LUXEMGOURGISH("Lëtzebuergesch", "lb_LU"),
    MALAY("Bahasa Melayu", "lv_LV"),
    MALTESE("Malti", "mt_MT"),
    MEXICAN_SPANISH("Español (Me)", "es_MX"),
    NORWEGIAN_BOKMAL("Norsk", "no_NO"),
    NORWEGIAN_NYNORSK("Norsk Nynorsk", "nb_NO"),
    OCCITAN("Occitan", "cc_CT"),
    PIRATE_ENGLISH("Pirate Speak ", "en_PT"),
    POLISH("Polski", "cc_CT"),
    PORTUGUESE("Portuguese (Po)", "pt_PT"),
    QUENYA("Quenya", "qya_AA"),
    ROMANIAN("Română", "ro_RO"),
    RUSSIAN("Русский", "ru_RU"),
    SERBIAN("Српски", "sr_RS"),
    SLOVAK("Slovenčina", "sk_SK"),
    SLOVENIAN("Slovenščina", "sl_SL"),
    SPANISH("Español (Es)", "es_ES"),
    SWEDISH("Svenska", "sv_SE"),
    THAI("ภาษาไทย", "th_TH"),
    TURKISH("Türkçe", "tr_TR"),
    UKRAINIAN("Українська", "uk_UA"),
    URUGUAYAN_SPANISH("Español (Ur)", "es_UY"),
    VENEZUELAN_SPANISH("Español (Ve)", "es_VE"),
    VIETNAMESE("Tiếng Việt", "vi_VN"),
    WELSH("Cymraeg", "cy_GB");

    private String language;
    private String locale;

    Language(String language, String locale) {
        this.locale = locale;
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getLocale() {
        return this.locale;
    }

    public static Language fromLocale(String locale) {
        for (Language l : Language.values())
            if (l.getLocale().equalsIgnoreCase(locale))
                return l;
        return null;
    }

    public static Language fromLanguage(String language) {
        for (Language l : Language.values())
            if (l.getLanguage().equalsIgnoreCase(language))
                return l;
        return null;
    }

}
