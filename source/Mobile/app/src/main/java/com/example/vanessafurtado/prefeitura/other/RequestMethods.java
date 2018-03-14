package com.example.vanessafurtado.prefeitura.other;

public enum RequestMethods {
    GET("GET"),
    POST("POST");

    private final String text;

    RequestMethods(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
