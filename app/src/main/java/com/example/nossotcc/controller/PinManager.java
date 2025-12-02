package com.example.nossotcc.controller;

import android.content.Context;
import android.content.SharedPreferences;

public class PinManager {

    private static final String PREF_NAME = "pin_pref";
    private static final String KEY_PIN = "user_pin";

    public static void salvarPin(Context context, String pin) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(KEY_PIN, pin).apply();
    }

    public static String getPin(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(KEY_PIN, null);
    }

    public static boolean existePin(Context context) {
        return getPin(context) != null;
    }

    public static boolean validarPin(Context context, String pinDigitado) {
        String salvo = getPin(context);
        return salvo != null && salvo.equals(pinDigitado);
    }
}
