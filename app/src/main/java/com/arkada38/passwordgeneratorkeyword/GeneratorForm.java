package com.arkada38.passwordgeneratorkeyword;

import com.arkada38.passwordgeneratorkeyword.Models.Generator;

import java.io.UnsupportedEncodingException;

public class GeneratorForm {
    private final String serviceName;
    private final String keyword;
    private final int passwordLength;
    private final boolean useSpecialSymbols;
    private final String password;

    public GeneratorForm(String serviceName, String lastName, int passwordLength, boolean useSpecialSymbols) throws UnsupportedEncodingException {
        this.serviceName = serviceName;
        this.keyword = lastName;
        this.passwordLength = passwordLength;
        this.useSpecialSymbols = useSpecialSymbols;
        this.password = Generator.generatePassword(serviceName, keyword, passwordLength, useSpecialSymbols);
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public int getPasswordLength() {
        return this.passwordLength;
    }

    public boolean getUseSpecialSymbols() {
        return this.useSpecialSymbols;
    }

    public String getPassword() {
        return this.password;
    }
}