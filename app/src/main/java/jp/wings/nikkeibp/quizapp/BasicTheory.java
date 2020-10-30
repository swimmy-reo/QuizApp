package jp.wings.nikkeibp.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BasicTheory extends AppCompatActivity {

    private TextView counLabel, questionLabel;
    private Button answer1, answer2, answer3, answer4;
    private int rightCount = 0, quizCount = 1, continuos = 0, quizAmount = 5;
    private String rightAnswer;

    String quiz[][] = {
            {"アナログとは", "うにょーん", "カクカク", "ほぼ掛け算", "ほぼ足し算"},
            {"インタプリタ言語とは", "逐次翻訳", "うにょーん", "全翻訳", "最大値決め"},
            {"M（メガ）とは", "10^6", "10^3", "10^9", "10^12"},
            {"G(ギガ)とは", "10^9", "10^6", "10^3", "10^12"},
            {"μ(マイクロ)とは", "10^-6", "10^-9", "10^-3", "10^-12"},
            {"n(ナノ）とは", "10^-9", "10^-3", "10^-6", "10^-12"},
    };

    ArrayList<ArrayList<String>> FEquestion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_theory);

        c = findViewById(R.id.countLabel);
        q = findViewById(R.id.questionLabel);
        a1 = findViewById(R.id.answerBtn1);
        a2 = findViewById(R.id.answerBtn2);
        a3 = findViewById(R.id.answerBtn3);
        a4 = findViewById(R.id.answerBtn4);

        for (int i = 0; i < 5; i++) {

            ArrayList<String> tempra = new ArrayList<>();

            tempra.add(quiz[i][0]);
            tempra.add(quiz[i][1]);
            tempra.add(quiz[i][2]);
            tempra.add(quiz[i][3]);
            tempra.add(quiz[i][4]);

            FEquestion.add(tempra);
        }

        makeQuiz();
    }

    public void makeQuiz(){

        counLabel.setText("Q" + quizCount);

        Random random = new Random();
        int randomNumber = random.nextInt(FEquestion.size());

        ArrayList<String> quiz = new ArrayList<>();
        quiz = FEquestion.get(randomNumber);

        questionLabel.setText(quiz.get(0));

        quiz.remove(0);

        rightAnswer = quiz.get(0);

        Collections.shuffle(quiz);

        answer1.setText(quiz.get(0));
        answer2.setText(quiz.get(1));
        answer3.setText(quiz.get(2));
        answer4.setText(quiz.get(3));

        FEquestion.remove(randomNumber);
    }

    public void checkAnswer(View view){

        Button answerBtn = findViewById(view.getId());
        String answerText = answerBtn.getText().toString();

        String notification;

        if (answerText == answerText){

            notification = "正解です";
            rightCount++;
            continuos++;

            if (continuos > 1){

                notification = continuos + "連続正解です";

            }
        }else{




        }






















    }

}


