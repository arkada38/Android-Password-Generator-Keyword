package com.arkada38.passwordgeneratorkeyword;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arkada38.passwordgeneratorkeyword.databinding.ActivityMainBinding;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        GeneratorForm generatorForm = null;
        try {
            generatorForm = new GeneratorForm("amazon", "horse", 12, true);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        binding.setGeneratorForm(generatorForm);
    }
}
