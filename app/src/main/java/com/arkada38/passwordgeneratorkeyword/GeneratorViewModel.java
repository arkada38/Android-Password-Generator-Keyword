package com.arkada38.passwordgeneratorkeyword;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.arkada38.passwordgeneratorkeyword.Models.Generator;

public class GeneratorViewModel extends BaseObservable {
    public String serviceName;
    public String keyword;
    public int passwordLength;
    public boolean useSpecialSymbols;
    public String password;

    @Bindable
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
        notifyPropertyChanged(BR.serviceName);
        setPassword();
    }

    @Bindable
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        notifyPropertyChanged(BR.keyword);
        setPassword();
    }

    @Bindable
    public int getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        if (passwordLength >= 8 && passwordLength <= 32) {
            this.passwordLength = passwordLength;
            notifyPropertyChanged(BR.passwordLength);
            setPassword();
        }
    }

    @Bindable
    public boolean isUseSpecialSymbols() {
        return useSpecialSymbols;
    }

    public void setUseSpecialSymbols(boolean useSpecialSymbols) {
        this.useSpecialSymbols = useSpecialSymbols;
        notifyPropertyChanged(BR.useSpecialSymbols);
        setPassword();
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void setPassword() {
        if (!serviceName.isEmpty() && !keyword.isEmpty() && passwordLength >= 8 & passwordLength <= 32)
            this.password = Generator.generatePassword(serviceName, keyword, passwordLength, useSpecialSymbols);
        notifyPropertyChanged(BR.password);
    }

    public GeneratorViewModel() {
        this.serviceName = "";
        this.keyword = "";
        this.passwordLength = 12;
        this.useSpecialSymbols = true;
    }
}