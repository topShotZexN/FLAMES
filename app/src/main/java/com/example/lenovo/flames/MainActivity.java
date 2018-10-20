package com.example.lenovo.flames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.*;

public class MainActivity extends AppCompatActivity {

    private String firstName, secondName, result;

    private EditText firstNameInput, secondNameInput;
    private Button submit, reset;
    private TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIView();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameInput.getText().toString();
                secondName = secondNameInput.getText().toString();
                result = compute(firstName, secondName);
                display.setText(result);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameInput.setText(null);
                secondNameInput.setText(null);
                display.setText(null);
            }
        });
    }

    private String compute(String name1, String name2){
        String s = "";
        // logic
        int k = findFlamesNumber(name1, name2); // k is flames number.
        int relation = remaining(6, k);
        switch (relation){
            case 0:
                s = name2 + " wants to have sex with " + name1;
                break;
            case 1:
                s = name2 + " is Enemy of " + name1;
                break;
            case 2:
                s = name2 + " wants to marry " + name1;
                break;
            case 3:
                s = name2 + " is attracted towards " + name1;
                break;
            case 4:
                s = name2 + " is in love with " + name1;
                break;
            case 5:
                s = name2 + " is just a friend of " + name1;
                break;
            default:
                s = "Error! Please Report.";
        }
        return s;
    }

    private int remaining(int n, int k){
        int r = 0;
        for(int i = 2; i <= n; i++){
            r = (r + k) % i;
        }
        return r;
    }

    private int findFlamesNumber(String name1, String name2){
        name1 = name1.toLowerCase();
        name2 = name2.toLowerCase();
        int lengthName1 = name1.length(), lengthName2 = name2.length(), i, j, count, flamesNumber = 0;
        int frequencyName1[] = new int[26];
        int frequencyName2[] = new int[26];
        char ch;
        for(i = 97; i < 123; i++){
            count = 0;
            ch = (char)i;
            for(j = 0; j < lengthName1; j++){
                if(ch == name1.charAt(j)){
                    count++;
                }
            }
            frequencyName1[i - 97] = count;
            count = 0;
            for(j = 0; j < lengthName2; j++){
                if(ch == name2.charAt(j)){
                    count++;
                }
            }
            frequencyName2[i - 97] = count;
        }
        for(i = 0; i < 26; i++){
            flamesNumber = flamesNumber + abs(frequencyName1[i] - frequencyName2[i]);
        }
        return flamesNumber;
    }

    void setupUIView(){
        firstNameInput = (EditText)findViewById(R.id.name1);
        secondNameInput = (EditText)findViewById(R.id.name2);

        submit = (Button)findViewById(R.id.submitButton);
        reset = (Button)findViewById(R.id.resetButton);
        display = (TextView)findViewById(R.id.displayResult);
    }
}
