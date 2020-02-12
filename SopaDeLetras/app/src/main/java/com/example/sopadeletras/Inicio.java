package com.example.sopadeletras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Inicio extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        editText = (EditText) findViewById(R.id.edit_text);
    }


    public void backToMenu (View v)
    {
        finish();
        Intent intent = new Intent(Inicio.this, MainActivity.class);
        startActivity(intent);
    }
    public void save(View v)
    {
        String text = editText.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput("info.txt", MODE_PRIVATE);
            fos.write(text.getBytes());
            editText.getText().clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        initGame();
    }

    public void initGame ()
    {
        finish();
        Intent intent = new Intent(Inicio.this, Splash.class);
        startActivity(intent);
    }

}
