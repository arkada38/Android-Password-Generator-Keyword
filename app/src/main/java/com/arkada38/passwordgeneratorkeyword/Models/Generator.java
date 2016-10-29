package com.arkada38.passwordgeneratorkeyword.Models;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Generator {
    static private String _uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static private String _lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
    static private String _numbers = "1234567890";
    static private String _symbols = "()`~!@#$%^&*-+=|{}[]:;'<>,.?/";

    static private long _seed;
    static private int _m = (38 * 4 + 3) * (62 * 4 + 3);// p % 4 = 3 // q % 4 = 3 // m = p * q

    public static String generatePassword(
            String serviceName, String keyword, int passwordLength, boolean useSpecialSymbols
    ) {

        int serviceNameNumber = getSumOfUTF8BytesFromString(serviceName);
        int keywordNumber = getSumOfUTF8BytesFromString(keyword);

        _seed = serviceNameNumber + keywordNumber + passwordLength;

        //Password should be contain uppercase, lowercase, numbers and symbols
        //without repeated characters
        //and consecutive of letters or numbers
        int quantityOfSymbols = 0;
        if (useSpecialSymbols)
            quantityOfSymbols = Math.max(2, (passwordLength / 5));
        int quantityOfNumbers = passwordLength / 4;
        int quantityOfUppercaseLetters = (passwordLength - quantityOfNumbers - quantityOfSymbols) / 2;
        int quantityOfLowercaseLetters = passwordLength - quantityOfNumbers - quantityOfSymbols - quantityOfUppercaseLetters;

        String passwordOfNumbers = "";
        String passwordOfSymbols = "";
        String passwordOfUppercaseLetters = "";
        String passwordOfLowercaseLetters = "";

        //quantityOfSymbols <= quantityOfNumbers <= quantityOfUppercaseLetters <= quantityOfLowercaseLetters

        //region Составление символов для пароля
        //uppercase
        ArrayList<Character> uppercaseLetters = new ArrayList<>();
        for (char c : _uppercaseLetters.toCharArray())
            uppercaseLetters.add(c);
        for (int i = 0; i < quantityOfUppercaseLetters; i++) {
            int j = nextRandom(uppercaseLetters.size());
            passwordOfUppercaseLetters += uppercaseLetters.get(j);
            uppercaseLetters.remove(j);
        }

        _seed += serviceName.length();

        //lowercase
        ArrayList<Character> lowercaseLetters = new ArrayList<>();
        for (char c : _lowercaseLetters.toCharArray())
            lowercaseLetters.add(c);
        for (int i = 0; i < quantityOfLowercaseLetters; i++) {
            int j = nextRandom(lowercaseLetters.size());
            passwordOfLowercaseLetters += lowercaseLetters.get(j);
            lowercaseLetters.remove(j);
        }

        _seed += keyword.length();

        //numbers
        ArrayList<Character> numbers = new ArrayList<>();
        for (char c : _numbers.toCharArray())
            numbers.add(c);
        for (int i = 0; i < quantityOfNumbers; i++) {
            int j = nextRandom(numbers.size());
            passwordOfNumbers += numbers.get(j);
            numbers.remove(j);
        }

        _seed += passwordLength;

        //symbols
        ArrayList<Character> symbols = new ArrayList<>();
        for (char c : _symbols.toCharArray())
            symbols.add(c);
        for (int i = 0; i < quantityOfSymbols; i++) {
            int j = nextRandom(symbols.size());
            passwordOfSymbols += symbols.get(j);
            symbols.remove(j);
        }

        _seed += serviceName.length() + keyword.length() + passwordLength;
        //endregion

        //region Перемешивание символов для пароля и его создание
        //Creation a password
        String password = "";
        String lastSymbolForPasswordFrom = "";

        for (int i = 0; i < passwordLength; i++) {
            //Максимальное значение символов в категории последовательности для пароля
            int maxQuantity = Math.max(Math.max(passwordOfNumbers.length(), passwordOfSymbols.length()),
                    Math.max(passwordOfUppercaseLetters.length(), passwordOfLowercaseLetters.length()));

            //Новый символ пароля будет из одной из самых длинных последовательностей
            String newSymbol = "";

            //Отбираем претендентов
            if (passwordOfNumbers.length() == maxQuantity && !passwordOfNumbers.equals(lastSymbolForPasswordFrom)  && password.length() > 0)
                newSymbol += passwordOfNumbers.substring(0, 1);

            if (passwordOfSymbols.length() == maxQuantity && !passwordOfSymbols.equals(lastSymbolForPasswordFrom) && password.length() > 0)
                newSymbol += passwordOfSymbols.substring(0, 1);

            if (passwordOfUppercaseLetters.length() == maxQuantity && !passwordOfUppercaseLetters.equals(lastSymbolForPasswordFrom))
                newSymbol += passwordOfUppercaseLetters.substring(0, 1);

            if (passwordOfLowercaseLetters.length() == maxQuantity && !passwordOfLowercaseLetters.equals(lastSymbolForPasswordFrom))
                newSymbol += passwordOfLowercaseLetters.substring(0, 1);

            //Вот этот символ
            newSymbol = Character.toString(newSymbol.charAt(nextRandom(newSymbol.length())));
            _seed += 1;

            //Удаляем символ из последовательности и запоминаем последовательность
            if (passwordOfNumbers.contains(newSymbol)) {
                passwordOfNumbers = passwordOfNumbers.replace(newSymbol, "");
                lastSymbolForPasswordFrom = passwordOfNumbers;
            }
            else if (passwordOfSymbols.contains(newSymbol)) {
                passwordOfSymbols = passwordOfSymbols.replace(newSymbol, "");
                lastSymbolForPasswordFrom = passwordOfSymbols;
            }
            else if (passwordOfUppercaseLetters.contains(newSymbol)) {
                passwordOfUppercaseLetters = passwordOfUppercaseLetters.replace(newSymbol, "");
                lastSymbolForPasswordFrom = passwordOfUppercaseLetters;
            }
            else if (passwordOfLowercaseLetters.contains(newSymbol)) {
                passwordOfLowercaseLetters = passwordOfLowercaseLetters.replace(newSymbol, "");
                lastSymbolForPasswordFrom = passwordOfLowercaseLetters;
            }

            //И добавляем к паролю
            password += newSymbol;
        }
        //endregion

        return password;
    }

    private static int getSumOfUTF8BytesFromString(String s) {
        int sum = 0;
        byte bytes[] = new byte[0];
        try {
            bytes = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (byte utf8Byte : bytes)
            sum += utf8Byte & 0xFF;
        return sum;
    }

    private static int nextRandom(int n) {
        _seed = (_seed * _seed) % _m;
        double x = (n - 1) * (double)_seed / _m;
        return (int) Math.round(x);
    }
}
