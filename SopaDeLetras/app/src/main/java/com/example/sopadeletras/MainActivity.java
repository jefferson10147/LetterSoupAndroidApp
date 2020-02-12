package com.example.sopadeletras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (fileExistsInStorage(MainActivity.this, "info.txt"))
           this.haveSafeFile();
    }

    public boolean fileExistsInStorage(Context context, String filename)
    {
        File file = context.getFileStreamPath(filename);
        if(file == null || !file.exists())
            return false;

        return true;
    }

    public void haveSafeFile()
    {
        Button button = (Button) findViewById(R.id.continuar);
        FileInputStream fis = null;

        try {
            fis = openFileInput("info.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            text = br.readLine();

            button.setVisibility(View.VISIBLE);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void continueGame (View view)
    {
        FileInputStream fis = null;

        try {
            fis = openFileInput("nivel.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            int levelSave;
            levelSave = Integer.parseInt(br.readLine());
            this.openLevel(levelSave);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openLevel (int levelSave)
    {
        Intent intent;
        switch (levelSave)
        {
            case 1: intent = new Intent(MainActivity.this, Nivel1.class);
                    startActivity(intent);break;
            case 2: intent = new Intent(MainActivity.this, Nivel2.class);
                    startActivity(intent); break;
            case 3: intent = new Intent(MainActivity.this, Nivel3.class);
                    startActivity(intent);break;
            case 4: intent = new Intent(MainActivity.this, Nivel4.class);
                    startActivity(intent);break;
            case 5: intent = new Intent(MainActivity.this, Nivel5.class);
                    startActivity(intent); break;
            case 6: intent = new Intent(MainActivity.this, Nivel6.class);
                    startActivity(intent); break;
        }
    }

    public void startGame (View view)
    {
        Intent intent = new Intent(MainActivity.this, Inicio.class);
        startActivity(intent);
    }

    public void openOptions (View view)
    {
        Intent intent = new Intent(MainActivity.this, Opciones.class);
        startActivity(intent);
    }

}
