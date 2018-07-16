package com.violetbutterfly.drinkoff.web;

public class Uri {
    public static final String ROOT = "/";

    public static final String ROOT_OAUTH = "/oauth";
    public static final String ROOT_URI_REST = "/api";

    public static final String ROOT_URI_COMPANY = ROOT_URI_REST + "/company";
    public static final String ROOT_URI_USER = ROOT_URI_REST + "/user";
    public static final String ROOT_URI_DISCOUNT = ROOT_URI_REST + "/discounts";

    public static class Part {
        public static final String SIGN_UP_COMPANY = "/signup";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String ALL = "/**";
        public static final String ONE_SEGMENT = "/*";
        public static final String FIND = "/find";
        public static final String ME = "/me";
    }
}
