package com.example.sopadeletras;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class Nivel3 extends AppCompatActivity implements OnClickListener {

    private char [][] matriz;
    private int size = 9, level = 3, totalPointsOnGame = 0;
    private int [] pointsForWord;
    private TextView [][] squares;
    private Game game;
    private boolean showWord = true;
    private boolean showFirtsLetter = true;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel3);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        this.setPlayer ();
        this.saveLevel();
        this.starMatriz();
    }

    public void saveLevel ()
    {
        FileOutputStream fos = null;
        String levelString = "3";
        try {
            fos = openFileOutput("nivel.txt", MODE_PRIVATE);
            fos.write(levelString.getBytes());
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
    }

    private void setPlayer ()
    {
        textView = (TextView) findViewById(R.id.user);
        FileInputStream fis = null;

        try {
            fis = openFileInput("info.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            text = br.readLine();
            textView.setText(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void starMatriz()
    {
        this.game = new Game(this.size,this.level);
        this.matriz = game.getMatriz();

        this.starViewsOnMatriz();
    }

    private void starViewsOnMatriz()
    {

        LinearLayout row = (LinearLayout) findViewById(R.id.Fila20);
        int x;
        this.squares = new TextView[this.size][this.size];

        for (int i = 0; i < this.size; i++)
            for(int j = 0; j < this.size; j++)
                this.squares[i][j] = new TextView(this);

        for (int i = 0; i<size; i++)
        {
            switch (i)
            {
                case 1: row = (LinearLayout) findViewById(R.id.Fila21); break;
                case 2: row = (LinearLayout) findViewById(R.id.Fila22); break;
                case 3: row = (LinearLayout) findViewById(R.id.Fila23); break;
                case 4: row = (LinearLayout) findViewById(R.id.Fila24); break;
                case 5: row = (LinearLayout) findViewById(R.id.Fila25); break;
                case 6: row = (LinearLayout) findViewById(R.id.Fila26); break;
                case 7: row = (LinearLayout) findViewById(R.id.Fila27); break;
                case 8: row = (LinearLayout) findViewById(R.id.Fila28); break;
            }
            for (int j = 0; j<size; j++)
            {
                this.squares[i][j].setText(Character.toString(this.matriz[i][j]));
                x = ViewCompat.generateViewId();
                this.squares[i][j].setId(x);
                this.squares[i][j].setGravity(Gravity.CENTER);
                this.squares[i][j].setTextSize(25);
                this.squares[i][j].setLayoutParams(new ActionBar.LayoutParams(122, ViewGroup.LayoutParams.WRAP_CONTENT));
                this.squares[i][j].setBackgroundResource(R.drawable.style_board);
                this.squares[i][j].setTag(R.drawable.style_board);
                row.addView(this.squares[i][j]);
                this.squares[i][j].setOnClickListener(this);
            }

        }
        this.pointsForWord = new int [this.game.getNumbersOfWords()];
        for (int i = 0; i < this.pointsForWord.length; i++)
            this.pointsForWord [i] = 0;
    }

    @Override
    public void onClick(View v)
    {

        TextView selectedLetter = (TextView) v;
        Object tag = selectedLetter.getTag();
        int id = tag == null ? -1 : Integer.parseInt(tag.toString());

        switch(id)
        {
            case R.drawable.style_board:
                selectedLetter.setBackgroundResource(R.drawable.style_board2);
                selectedLetter.setTag(R.drawable.style_board2);
                this.lookForId(selectedLetter.getId(),true);
                this.haveWon();
                break;
            case R.drawable.style_board2:
                selectedLetter.setBackgroundResource(R.drawable.style_board);
                selectedLetter.setTag(R.drawable.style_board);
                this.lookForId(selectedLetter.getId(),false);
                break;
        }
    }

    public void lookForId(int id, boolean cond)
    {
        boolean flag = false;

        for (int i = 0; i < this.size; i++)
        {
            for (int j = 0; j < this.size; j++)
            {
                if (id == this.squares[i][j].getId())
                {
                    if (cond)
                    {
                        sumPoints(this.game.getAnswers(i,j));
                    }else{
                        restPoints(this.game.getAnswers(i,j));
                    }
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
        }
    }
    public void sumPoints (boolean [] vPoints)
    {
        for (int i = 0; i < vPoints.length; i++)
            if(vPoints[i])
            {
                switch (i)
                {
                    case 0: this.pointsForWord[i]++; break;
                    case 1: this.pointsForWord[i]++; break;
                    case 2: this.pointsForWord[i]++; break;
                    case 3: this.pointsForWord[i]++; break;
                    case 4: this.pointsForWord[i]++; break;
                    case 5: this.pointsForWord[i]++; break;
                    case 6: this.pointsForWord[i]++; break;
                    case 7: this.pointsForWord[i]++; break;
                    case 8: this.pointsForWord[i]++; break;
                    case 9: this.pointsForWord[i]++; break;
                }
                if(this.pointsForWord[i] == this.game.getWordLength(i))
                    this.colorCorrectWord(i);
            }
    }

    public void colorCorrectWord (int wordNumberPosition)
    {
        Random r = new Random();
        int x = r.nextInt(15);

        boolean [][][] wholeAnswers  = this.game.getMatrizAns();
        for (int i = 0; i < wholeAnswers.length; i++)
            for (int j = 0; j < wholeAnswers[i].length; j++)
                if (wholeAnswers[i][j][wordNumberPosition])
                {
                    switch (x)
                    {
                        case 0: this.squares[i][j].setBackgroundResource(R.drawable.mode1);
                            this.squares[i][j].setTag(R.drawable.mode1);break;
                        case 1:this.squares[i][j].setBackgroundResource(R.drawable.mode2);
                            this.squares[i][j].setTag(R.drawable.mode2); break;
                        case 2:this.squares[i][j].setBackgroundResource(R.drawable.mode3);
                            this.squares[i][j].setTag(R.drawable.mode3); break;
                        case 3:this.squares[i][j].setBackgroundResource(R.drawable.mode4);
                            this.squares[i][j].setTag(R.drawable.mode4); break;
                        case 4: this.squares[i][j].setBackgroundResource(R.drawable.mode5);
                            this.squares[i][j].setTag(R.drawable.mode5);break;
                        case 5:this.squares[i][j].setBackgroundResource(R.drawable.mode6);
                            this.squares[i][j].setTag(R.drawable.mode6); break;
                        case 6:this.squares[i][j].setBackgroundResource(R.drawable.mode7);
                            this.squares[i][j].setTag(R.drawable.mode7); break;
                        case 7:this.squares[i][j].setBackgroundResource(R.drawable.mode8);
                            this.squares[i][j].setTag(R.drawable.mode8); break;
                        case 8: this.squares[i][j].setBackgroundResource(R.drawable.mode9);
                            this.squares[i][j].setTag(R.drawable.mode9);break;
                        case 9:this.squares[i][j].setBackgroundResource(R.drawable.mode10);
                            this.squares[i][j].setTag(R.drawable.mode10); break;
                        case 10: this.squares[i][j].setBackgroundResource(R.drawable.mode11);
                            this.squares[i][j].setTag(R.drawable.mode11);break;
                        case 11: this.squares[i][j].setBackgroundResource(R.drawable.mode12);
                            this.squares[i][j].setTag(R.drawable.mode12);break;
                        case 12:this.squares[i][j].setBackgroundResource(R.drawable.mode13);
                            this.squares[i][j].setTag(R.drawable.mode13); break;
                        case 13: this.squares[i][j].setBackgroundResource(R.drawable.mode14);
                            this.squares[i][j].setTag(R.drawable.mode14);break;
                        case 14:this.squares[i][j].setBackgroundResource(R.drawable.mode15);
                            this.squares[i][j].setTag(R.drawable.mode15); break;
                    }
                }

        this.cleanRedSquaresOnSoup();
    }

    public void cleanRedSquaresOnSoup ()
    {
        int id;
        for (int i = 0; i < this.squares.length; i++ )
            for (int j = 0; j < this.squares[i].length; j++)
            {
                Object tag = this.squares[i][j].getTag();
                id = tag == null ? -1 : Integer.parseInt(tag.toString());

                if (id == R.drawable.style_board2)
                {
                    this.squares[i][j].setBackgroundResource(R.drawable.style_board);
                    this.squares[i][j].setTag(R.drawable.style_board);
                    this.lookForId(this.squares[i][j].getId(),false);
                }
            }
    }

    public void restPoints (boolean [] vPoints)
    {
        for (int i = 0; i < vPoints.length; i++)
            if(vPoints[i])
            {
                switch (i)
                {
                    case 0: this.pointsForWord[i]--; break;
                    case 1: this.pointsForWord[i]--; break;
                    case 2: this.pointsForWord[i]--; break;
                    case 3: this.pointsForWord[i]--; break;
                    case 4: this.pointsForWord[i]--; break;
                    case 5: this.pointsForWord[i]--; break;
                    case 6: this.pointsForWord[i]--; break;
                    case 7: this.pointsForWord[i]--; break;
                    case 8: this.pointsForWord[i]--; break;
                    case 9: this.pointsForWord[i]--; break;
                }
            }
    }

    public void haveWon()
    {
        this.totalPointsOnGame = 0;
        for (int i  = 0; i < this.pointsForWord.length; i++)
            this.totalPointsOnGame += this.pointsForWord [i];

        if (this.totalPointsOnGame == game.getPoints(this.level))
            setVisibleButtonContinue();
    }

    public int getBackgroundColorCode (int i, int j)
    {
        Object tag = this.squares[i][j].getTag();
        int id = tag == null ? -1 : Integer.parseInt(tag.toString());
        return id;
    }

    public void revealWord (View v)
    {
        ImageView imageButton = (ImageView) v;
        if (this.showWord)
        {
            int i = 0, colorCode;
            int [][] matrizAuxiliar;
            boolean flag = true;

            while (flag)
            {
                matrizAuxiliar = this.game.getRandomWordPosition();
                if (this.pointsForWord[matrizAuxiliar[0][0]] < this.game.getWordLength(matrizAuxiliar [0][0]))
                {
                    while (i<matrizAuxiliar[1].length)
                    {
                        colorCode = getBackgroundColorCode(matrizAuxiliar[1][i],matrizAuxiliar[2][i]);
                        if (colorCode == R.drawable.style_board)
                            sumPoints(this.game.getAnswers(matrizAuxiliar[1][i],matrizAuxiliar[2][i]));

                        i++;
                    }
                    flag = false;
                }
            }
            this.haveWon();
            this.showWord = false;
            imageButton.setBackgroundColor(Color.YELLOW);
        }

    }

    public void revealFirstLetter (View v)
    {
        ImageView imageButton = (ImageView) v;
        if (this.showFirtsLetter)
        {
            int [] vec;
            int colorCode;

            while (true)
            {
                vec = this.game.getRandomWordFirstLetterPos();
                colorCode  = this.getBackgroundColorCode(vec[0], vec[1]);
                if (colorCode == R.drawable.style_board)
                {
                    sumPoints(this.game.getAnswers(vec[0],vec[1]));
                    this.squares[vec[0]][vec[1]].setBackgroundResource(R.drawable.style_board3);
                    this.squares[vec[0]][vec[1]].setTag(R.drawable.style_board3);
                    break;
                }
            }
            this.showFirtsLetter = false;
            imageButton.setBackgroundColor(Color.YELLOW);
        }
    }

    public void setVisibleButtonContinue ()
    {
        finish();
        Intent intent = new Intent(Nivel3.this, Splash3.class);
        startActivity(intent);
    }

    public void openList (View v)
    {
        ListaPalabras3 listaPalabras3 = new ListaPalabras3();
        listaPalabras3.show(getSupportFragmentManager(),"ejemplo2");
    }
}
