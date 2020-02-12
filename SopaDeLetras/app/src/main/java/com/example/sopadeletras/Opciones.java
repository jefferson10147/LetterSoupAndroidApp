package com.example.sopadeletras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Opciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);


        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

    }

    public void openMenu(View view)
    {
        finish();
        Intent intent = new Intent(Opciones.this, MainActivity.class);
        startActivity(intent);
    }
}
