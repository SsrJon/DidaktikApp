package com.example.didaktikapp.sopadeletras.features;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.didaktikapp.sopadeletras.features.wordsearch.WordSearchActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, WordSearchActivity.class);
        startActivity(intent);
        finish();
    }
}
