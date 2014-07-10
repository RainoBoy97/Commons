package me.raino.commons.messaging.localization;

public enum Language {

    AFRIKAANS("af_ZA"),
    AMERICAN_ENGLISH("en_US"),
    ARABIC("ar_SA"),
    ARGENTINIAN_ENGLISH("es_AR"),
    ARMENIAN("hy_AM"),
    AUSTRALIAN_ENGLISH("en_AU"),
    BASQUE("eu_ES"),
    BRAZILIAN_PORTUGUESE("pt_BR"),
    BRITISH_ENGLISH("en_GB"),
    BULGARIAN("bg_BG"),
    CANADIAN_ENGLISH("en_CA"),
    CANADIAN_FRENCH("fr_CA"),
    CATALAN("ca_ES"),
    CHINESE_SIMPLIFIED("zh_CH"),
    CHINESE_TRADITIONAL("zh_TW"),
    CORNWALL("kw_GB"),
    CROATIAN("hr_HR"),
    CZECH("cs_CZ"),
    DANISH("da_DK"),
    DUTCH("nl_NL"),
    ESPERANTO("eo_UY"),
    ESTONIAN("et_EE"),
    FINNISH("fi_FI"),
    FRENCH("fr_FR"),
    GALICIAN("gl_ES"),
    GEORGIAN("ka_GE"),
    GERMAN("da_DE"),
    GREEK("el_GR"),
    HEBREW("hr_HR"),
    HINDI("hi_IN"),
    HUNGARIAN("hu_HU"),
    ICELANDIC("is_IS"),
    INDONESIAN("id_ID"),
    IRISH("ga_IE"),
    ITALIAN("it_IT"),
    JAPANESE("ja_JP"),
    KLINGON("tlh_AA"),
    KOREAN("ko_KR"),
    LATIN("la_VA"),
    LATVIAN("lv_LV"),
    LITHUANIAN("lt_LT"),
    LUXEMGOURGISH("lb_LU"),
    MALAY("lv_LV"),
    MALTESE("mt_MT"),
    MEXICAN_SPANISH("es_MX"),
    NORWEGIAN_BOKMAL("no_NO"),
    NORWEGIAN_NYNORSK("nb_NO"),
    OCCITAN("cc_CT"),
    PIRATE_ENGLISH("en_PT"),
    POLISH("cc_CT"),
    PORTUGUESE("pt_PT"),
    QUENYA("qya_AA"),
    ROMANIAN("ro_RO"),
    RUSSIAN("ru_RU"),
    SERBIAN("sr_RS"),
    SLOVAK("sk_SK"),
    SLOVENIAN("sl_SL"),
    SPANISH("es_ES"),
    SWEDISH("sv_SE"),
    THAI("th_TH"),
    TURKISH("tr_TR"),
    UKRAINIAN("uk_UA"),
    URUGUAYAN_SPANISH("es_UY"),
    VENEZUELAN_SPANISH("es_VE"),
    VIETNAMESE("vi_VN"),
    WELSH("cy_GB");
    
    private String locale;
    
    Language(String locale) {
        this.locale = locale;
    }
    
    public String getLocale() {
        return this.locale;
    }
    
    public static Language fromLocale(String locale) {
        for (Language language : Language.values())
            if (language.getLocale().equalsIgnoreCase(locale))
                return language;
        return null;
    }
    
}
