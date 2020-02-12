package com.example.sopadeletras;

import android.graphics.Color;
import android.widget.TextView;

import java.util.Random;
public class Game{

    private char [][] matriz;
    private char [][] matrizAux;
    private boolean [][][] matrizAsnwers;
    private int [] wordLength;
    private int [] firstLetterPosI;
    private int [] firstLetterPosJ;
    private String [] sentences = {"carro barco metro avion moto tren","arroz avena pollo carne" +
                                    " pasta huevo sopa cafe", "lobo oso toro vaca perro gato mono caballo" +
                                    " loro zorro", "jupiter mercurio saturno neptuno cometa tierra" +
                                    " urano pluton venus marte luna sol", "islandia noruega holanda" +
                                    " mexico egipto grecia suecia italia canada japon china rusia india suiza",
                                    "santiago caracas bogota berlin atenas cayena dublin panama quito " +
                                            "tokio viena lima praga moscu roma oslo"};
    private char [] abc = {'a','b','c','d','e','f','g','h',
            'i','j','k','l','m','n','o','p',
            'q','r','s','t','u','v','x','w','y','z'};
    private Random r = new Random();
    private int row,col, size, level, cont, cont2, actualWord, numbersOfWords;

    public Game (int size, int level)
    {
        String [] words = this.sentences[level-1].split(" ");
        this.size = size;
        this.level = level;
        this.numbersOfWords = words.length;
        this.matriz = new char [this.size][this.size];
        this.matrizAux = new char [this.size][this.size];
        this.matrizAsnwers = new boolean [this.size][this.size][words.length];
        this.firstLetterPosI = new int [this.numbersOfWords];
        this.firstLetterPosJ = new int [this.numbersOfWords];

        this.setWordsLength(words);
        this.cleanMatriz(this.matriz);
        this.cleanMatriz(this.matrizAsnwers);
        this.setPosicion(words);
        this.randomABC(this.matriz);
    }

    public void setWordsLength (String [] words)
    {
        this.wordLength = new int [words.length];
        for (int i = 0; i < words.length ; i++)
            wordLength[i] = words[i].length();
    }

    public void cleanMatriz (char [][] matrizToClean)
    {
        for (int i = 0; i < size; i++)
            for (int j = 0; j< size; j++)
                matrizToClean[i][j] = '0';

    }

    public void cleanMatriz (boolean [][][] matrizToClean)
    {
        for(int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                for (int k = 0; k < matrizToClean[i][j].length; k++)
                    matrizToClean[i][j][k] = false;
    }

    public void cleanVector(char [] vector)
    {
        for (int i = 0; i < vector.length; i++)
            vector [i] = '0';
    }

    public void setPosicion (String [] words)
    {
        int rand, x = 0, i = 0;
        boolean flag = true;
        char [] vAux = new char [8];
        String word;
        while (i<words.length)
        {
            word = words[i];
            this.actualWord = i;
            i++;
            do
            {
                rand = r.nextInt(8);
                if(vAux[rand] != '1')
                {
                    switch(rand)
                    {
                        case 0: flag = horizontal(word); vAux [0] = '1'; break;
                        case 1: flag = horizontalI(word); vAux [1] = '1'; break;
                        case 2: flag = vertical(word); vAux [2] = '1'; break;
                        case 3: flag = verticalI(word); vAux [3] = '1'; break;
                        case 4: flag = diagonalL(word); vAux [4] = '1'; break;
                        case 5: flag = diagonalLI(word); vAux [5] = '1'; break;
                        case 6: flag = diagonalR(word); vAux [6] = '1'; break;
                        case 7: flag = diagonalRI(word); vAux [7] = '1'; break;
                    }
                }
                for(int z = 0; z<vAux.length; z++)
                    if(vAux[z] == '1')
                        x++;

                if (x == 8)
                {
                    cleanMatriz(this.matriz);
                    cleanMatriz(this.matrizAsnwers);
                    i=0;
                    break;
                }
            }while(flag);
            this.cleanVector(vAux);
        }

    }

    public boolean horizontal (String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while(condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.col + word.length() )<= this.size)
            {
                this.cont = 0; this.cont2 = 0;
                for (int i = col; i < (word.length()+col); i++)
                {
                    if (this.matriz[this.row][i] == '0' || matriz[this.row][i] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                }
                if(this.cont == word.length())
                {
                    this.firstLetterPosI[this.actualWord] = this.row;
                    this.firstLetterPosJ[this.actualWord] = this.col;
                    for(int i = 0; i<word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
			            this.matrizAsnwers[this.row][this.col][this.actualWord] = true;
                        this.col++;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row,this.col);
        }
        return true;
    }
    public boolean horizontalI(String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while(condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.col +1) - word.length() >= 0)
            {
                this.cont = 0; this.cont2 = 0;
                int x = col;
                while (x>=((this.col+1)-word.length()))
                {
                    if (this.matriz[this.row][x] == '0' || this.matriz[this.row][x] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                    x--;
                }
                if(this.cont == word.length())
                {
                    this.firstLetterPosI[this.actualWord] = this.row;
                    this.firstLetterPosJ[this.actualWord] = this.col;
                    for(int i = 0; i<word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
			            this.matrizAsnwers[this.row][this.col][this.actualWord] = true;
    			        this.col--;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row, this.col);
        }
        return true;
    }
    public boolean vertical (String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if(this.row + word.length() <= this.size)
            {
                this.cont = 0; this.cont2 = 0;
                for (int i = this.row; i < (word.length()+this.row); i++)
                {
                    if (this.matriz[i][this.col] == '0' || this.matriz[i][this.col] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                }
                if (this.cont  == word.length())
                {
                    this.firstLetterPosI[this.actualWord] = this.row;
                    this.firstLetterPosJ[this.actualWord] = this.col;
                    for(int i = 0; i<word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
                        this.matrizAsnwers[this.row][this.col][this.actualWord] = true;
       			        this.row ++;
                    }
                    return false;
                }

            }
            condicion = getOnMatrizAux(this.row,this.col);
        }
        return true;
    }
    public boolean verticalI (String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.row + 1) - word.length() >= 0)
            {
                this.cont = 0; this.cont2 = 0;
                for (int i = this.row; i >= (this.row + 1) - word.length(); i--)
                {
                    if (this.matriz[i][this.col] == '0' || this.matriz[i][this.col] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                }
                if (cont  == word.length())
                {
                    this.firstLetterPosI[this.actualWord] = this.row;
                    this.firstLetterPosJ[this.actualWord] = this.col;
                    for(int i = 0; i<word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
			            this.matrizAsnwers[this.row][this.col][this.actualWord] = true;
      			        this.row --;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row,this.col);
        }
        return true;
    }
    public boolean diagonalL(String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.row + 1) - word.length() >= 0 && this.col + word.length() <= this.size)
            {
                this.cont = 0; this.cont2 = 0;
                int x = this.row, j = this.col;
                while(x >= ((this.row + 1) - word.length()) && j <= (this.col + word.length()))
                {
                    if(this.matriz[x][j] == '0' || this.matriz[x][j] == word.charAt(cont2))
                        this.cont++;

                    this.cont2++;
                    x--; j++;
                }
                if(this.cont == word.length())
                {
                    this.firstLetterPosI[this.actualWord] = this.row;
                    this.firstLetterPosJ[this.actualWord] = this.col;
                    for(int i = 0; i < word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
			            this.matrizAsnwers[this.row][this.col][this.actualWord] = true;
                        this.row--; this.col++;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row,this.col);
        }
        return true;
    }
    public boolean diagonalLI(String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if(this.row + word.length() <= this.size && (this.col + 1) - word.length() >= 0)
            {
                this.cont = 0; this.cont2 = 0;
                int x = this.row, j = this.col;
                while(x <= (this.row +  word.length()) && j >= ((this.col+1) - word.length()))
                {
                    if(this.matriz[x][j] == '0' || this.matriz[x][j] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                    x++; j--;
                }
                if(this.cont == word.length())
                {
                    this.firstLetterPosI[this.actualWord] = this.row;
                    this.firstLetterPosJ[this.actualWord] = this.col;
                    for(int i = 0; i < word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
			            this.matrizAsnwers[this.row][this.col][this.actualWord] = true;
			            this.row++; this.col--;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row, this.col);
        }
        return true;
    }
    public boolean diagonalR(String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.row + 1) - word.length() >= 0 && (this.col + 1) - word.length() >= 0)
            {
                cont = 0; cont2 = 0;
                int x = this.row, j = this.col;
                while(x >= ((this.row + 1) -  word.length()) && j >= ((this.col + 1) - word.length()))
                {
                    if(this.matriz[x][j] == '0' || this.matriz[x][j] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                    x--; j--;
                }
                if(this.cont == word.length())
                {
                    this.firstLetterPosI[this.actualWord] = this.row;
                    this.firstLetterPosJ[this.actualWord] = this.col;
                    for(int i = 0; i < word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
			            this.matrizAsnwers[this.row][this.col][this.actualWord] = true;
                        this.row--; this.col--;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row, this.col);
        }
        return true;
    }
    public boolean diagonalRI(String word)
    {
        cleanMatriz(this.matrizAux);
        int condicion = 0;
        while (condicion != this.size*this.size)
        {
            this.row = r.nextInt(this.size);
            this.col = r.nextInt(this.size);
            if((this.row + word.length()) <= this.size && (this.col + word.length()) <= this.size)
            {
                this.cont = 0; this.cont2 = 0;
                int x = this.row, j = this.col;
                while(x < (this.row + word.length()) && j < (this.col + word.length()))
                {
                    if(this.matriz[x][j] == '0' || this.matriz[x][j] == word.charAt(this.cont2))
                        this.cont++;

                    this.cont2++;
                    x++; j++;
                }
                if(this.cont == word.length())
                {
                    this.firstLetterPosI[this.actualWord] = this.row;
                    this.firstLetterPosJ[this.actualWord] = this.col;
                    for(int i = 0; i < word.length(); i++)
                    {
                        this.matriz[this.row][this.col] = word.charAt(i);
			            this.matrizAsnwers[this.row][this.col][this.actualWord] = true;
                        this.row++; this.col++;
                    }
                    return false;
                }
            }
            condicion = getOnMatrizAux(this.row, this.col);
        }
        return true;
    }
    public int getOnMatrizAux (int r, int c)
    {
        int counting1 = 0;
        this.matrizAux [r][c] = '1';

        for (int i = 0; i<this.size; i++)
            for (int j = 0; j<this.size; j++)
                if (this.matrizAux[i][j] == '1')
                    counting1 ++;

        return counting1;
    }

    public char [][] getMatriz (){return this.matriz;}

    public char getABC (int i){return this.abc[i];}

    public int getWordLength (int i){ return this.wordLength[i];}

    public int getNumbersOfWords (){return this.numbersOfWords;}

    public boolean [][][] getMatrizAns () {return this.matrizAsnwers;}

    public void randomABC (char [][] matrizR)
    {
        int x;
        for(int i = 0; i<this.size; i++)
            for (int j = 0; j<this.size; j++)
                if(matrizR[i][j] == '0')
                {
                    x = r.nextInt(26);
                    matrizR [i][j] = getABC(x);
                }
    }

    public boolean [] getAnswers (int r, int c)
    {
        boolean [] vectorAux = new boolean [this.numbersOfWords];

        for(int i  = 0; i < this.numbersOfWords; i++ )
            vectorAux[i] = this.matrizAsnwers[r][c][i];

        return vectorAux;
    }

    public int getPoints (int level)
    {
        int totalPointsOnlevel = 0;
        String [] words = sentences[level-1].split(" ");

        for (int i = 0; i < words.length; i++ )
            totalPointsOnlevel += words[i].length();

        return totalPointsOnlevel;
    }

    public int [][] getRandomWordPosition ()
    {
        int wordNumber =  this.r.nextInt(this.numbersOfWords);
        String [] words = this.sentences[this.level-1].split(" ");
        int [][] positions = new int[3][words[wordNumber].length()];
        int x = 0;
        positions [0][0] = wordNumber;

        for (int i = 0; i < this.size; i++)
            for (int j = 0; j < this.size; j++)
                if(this.matrizAsnwers[i][j][wordNumber])
                {
                    positions [1][x] = i;
                    positions [2][x] = j;
                    x++;
                }

        return positions;
    }

    public int [] getRandomWordFirstLetterPos ()
    {
        int [] vec = new int [2];
        int x = this.r.nextInt(this.numbersOfWords);
        vec[0] = this.firstLetterPosI[x];
        vec [1] = this.firstLetterPosJ[x];

        return vec;
    }
}