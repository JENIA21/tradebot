package org.example.commands;

public enum Response {

    START("Введите /help чтобы узнать воможные команды"),
    HELP("Возможные комманды /help, /сhangeCurrency, /rate, /getAllUsers, /addCurrency");

    private final String response;

    Response(String response) {
        this.response = response;

    }

    @Override
    public String toString() {
        return response;
    }
}
