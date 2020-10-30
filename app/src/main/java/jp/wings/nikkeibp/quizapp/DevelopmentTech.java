package jp.wings.nikkeibp.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DevelopmentTech extends AppCompatActivity {

    private TextView c,q;
    private Button a1,a2,a3,a4;

    private int quizCount = 1;
    private String rightAnswer;
    private int rightCount = 0;
    private int Continuous = 0;
    private final static int Qvolume = 5;

    String quizData[][] = {

            {"システム方式設計とは","見える所","複数人評価","変更後どう？\n(一応五文字ですよね？)","データ移す",},
            {"ソフト方式設計とは","見えない所","変な所","中身どう？","入出力検査",},
            {"ブラックボックステストとは","入出力検査","責任者","見えない所","何作る？",},
            {"ホワイトボックステストとは","中身どう？","何作る？","複数人評価","入出力検査",},
            {"リグレッションテストとは","変更後どう？\n(一応五文字ですよね？)","データ移す","中身どう？","見えない所",},
    };

    ArrayList<ArrayList<String>> FEquestion4 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_development_tech);

        q = findViewById(R.id.questionLabel);
        c = findViewById(R.id.countLabel);
        a1 = findViewById(R.id.answerBtn1);
        a2 = findViewById(R.id.answerBtn2);
        a3 = findViewById(R.id .answerBtn3);
        a4 =  findViewById(R.id.answerBtn4);



        for (int i = 0; i < 5; i++) {

            ArrayList<String> tempra = new ArrayList<>();

            tempra.add(quizData[i][0]);
            tempra.add(quizData[i][1]);
            tempra.add(quizData[i][2]);
            tempra.add(quizData[i][3]);
            tempra.add(quizData[i][4]);

            FEquestion4.add(tempra);
        }

        OpenQuiz();

    }

    public void OpenQuiz(){


        c.setText("Q" + quizCount);

        Random r = new Random();
        int rNum = r.nextInt(FEquestion4.size());

        ArrayList<String> quiz = new ArrayList<>();
        quiz = FEquestion4.get(rNum);


        q.setText(quiz.get(0));
        quiz.remove(0);



        rightAnswer = quiz.get(0);

        Collections.shuffle(quiz);

        a1.setText(quiz.get(0));
        a2.setText(quiz.get(1));
        a3.setText(quiz.get(2));
        a4.setText(quiz.get(3));

        FEquestion4.remove(rNum);

    }


        public void checkAnswer(View view){

        Button answerBtn = findViewById(view.getId());
        String answerTxt = answerBtn.getText().toString();

        String notice;

        if (answerTxt.equals(rightAnswer)) {

            notice = "正解";
            rightCount++;
            Continuous++;


            if (Continuous > 1) {
                notice = Continuous + "連続正解";
            }

        }else{
            Continuous = 0;
            notice = "残念！";
        }

            AlertDialog.Builder Notice = new AlertDialog.Builder(this);
            Notice.setTitle(notice);
            Notice.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (Qvolume == quizCount){
                        Intent journey = new Intent(getApplicationContext(), ResultActivity.class);
                        journey.putExtra("RIGHT_ANSWER_COUNT",rightCount);
                        startActivity(journey);
                    }else{
                        quizCount++;
                        OpenQuiz();
                    }

                }

            });

            Notice.setCancelable(false);
            Notice.show();
        }
}