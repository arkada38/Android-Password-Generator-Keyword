package com.arkada38.passwordgeneratorkeyword;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arkada38.passwordgeneratorkeyword.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    GeneratorViewModel generatorForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        generatorForm = new GeneratorViewModel();

        binding.setGeneratorForm(generatorForm);
    }

    public void copyToClipboard(View view) {
        String password = ((TextView) findViewById(R.id.passwordTv)).getText().toString();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Password", password);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Password is copied", Toast.LENGTH_SHORT).show();
    }
}
