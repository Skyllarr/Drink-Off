package com.violetbutterfly.drinkoff.web.security;

import java.util.HashMap;
import java.util.Map;

public enum ClientType {
    COMPANY_WEB("fed035eb-3ccc-4d34-8d0f-9d5c3355d707"),
    COMPANY_MOBILE("611c7cf8-329c-4441-8590-8a19f5dcdebe"),
    PLAYER_MOBILE("1ac10851-b579-4821-abb7-73f751f458fb"),
    UNKNOWN("");

    private final String secret;

    private static Map<String, ClientType> clientTypes = new HashMap<>();

    static {
        clientTypes.put(COMPANY_WEB.secret, COMPANY_WEB);
        clientTypes.put(COMPANY_MOBILE.secret, COMPANY_MOBILE);
        clientTypes.put(PLAYER_MOBILE.secret, PLAYER_MOBILE);
        clientTypes.put(UNKNOWN.secret, UNKNOWN);

    }

    ClientType(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }

    public static ClientType getClientType(String secret) {
        ClientType result = clientTypes.get(secret);
        return result == null ? UNKNOWN : result;
    }
}
