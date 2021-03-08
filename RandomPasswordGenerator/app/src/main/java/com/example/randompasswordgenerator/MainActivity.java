package com.example.randompasswordgenerator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static String generator(int n) {

        int k = 0;
        char[] password = new char[1000];

        int randInt;

        Random rand = new Random();

        boolean capCheck = false, smallCheck = false, specialCheck = false, numCheck = false;

        for (int i = 1; i <= n; i++) {
            randInt = rand.nextInt(128);

            if (randInt >= 97 && randInt <= 122)    //small letter
            {

                password[k++] = (char) randInt;
                smallCheck = true;

            } else if (randInt >= 65 && randInt <= 90) //capital letter
            {

                password[k++] = (char) randInt;
                capCheck = true;

            } else if (randInt >= 48 && randInt <= 57) //number
            {
                password[k++] = (char) randInt;
                numCheck = true;
            } else if (randInt == 33 || (randInt >= 35 && randInt <= 38) || randInt == 42 || randInt == 64)
            //special characters -- ! # $ % & * @
            {
                password[k++] = (char) randInt;
                specialCheck = true;
            } else i = i - 1;
        }

        password[k] = '\0';
        String psswd = new String(password);

        if (!(smallCheck && capCheck && numCheck && specialCheck)) {
            psswd = generator(n);
        }
        return psswd;
    }

    public void generate(View view) {

        int n = 8;
        TextInputEditText enter = findViewById(R.id.enter);
String num = enter.getText().toString();
        if(num.matches(""))
        {
            Toast.makeText(this, "Enter an integer",Toast.LENGTH_SHORT).show();
            return;
        }

        n = Integer.parseInt(num);

        if (n >= 8) {
            String ans = generator(n);
            //System.out.println(ans);

            Toast.makeText(this, "Password Generated",Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, ans, Toast.LENGTH_LONG).show();

            //View binding and changing the text of the text view to the generated password
            TextView textView = findViewById(R.id.textView);
            textView.setText(ans);

        }
        else {
            //System.out.println("Please enter a length greater than equal to 6");
            Toast.makeText(this, "Enter integer greater than 8",Toast.LENGTH_SHORT).show();
        }
    }

}