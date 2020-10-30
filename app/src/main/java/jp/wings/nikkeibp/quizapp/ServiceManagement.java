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

public class ServiceManagement extends AppCompatActivity {

    public TextView c,q;
    private Button a1,a2,a3,a4;
    private int quizCount = 1;
    private String rightAnswer;
    private int rightCount = 0,Continuos = 0,Qvolume = 5;

    String quizData[][] = {
            {"SFAとは","営業支援","自主運用","サーバ借り","絶対必要"},
            {"バックエンドとは","見えない所","複数共有","使いやすさ","根本的改良"},
            {"フロントエンドとは","見える所","生活改善","情報格差","業務改善"},
            {"BPRとは","根本的改良","大量データ","自主運用","見えない所"},
            {"RPAとは","業務自動化","情報格差","見えることろ","営業支援"},
            {"インフラ","絶対必要","使いやすさ","営業支援","ゲーム要素"},
            {"ホスティングサービス","サーバ借り","構想具体化","見積書要求","エコな調達"},
    };

    ArrayList<ArrayList<String>> FEquestion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_management);

        c  = findViewById(R.id.countLabel);
        q = findViewById(R.id.questionLabel);
        a1 = findViewById(R.id.answerBtn1);
        a2 = findViewById(R.id.answerBtn2);
        a3 = findViewById(R.id.answerBtn3);
        a4 = findViewById(R.id.answerBtn4);

        for (int i = 0; i < 5; i++) {

            ArrayList<String> tempra = new ArrayList<>();

            tempra.add(quizData[i][0]);
            tempra.add(quizData[i][1]);
            tempra.add(quizData[i][2]);
            tempra.add(quizData[i][3]);
            tempra.add(quizData[i][4]);

            FEquestion.add(tempra);
        }

        OpenQ();
    }

    public void OpenQ(){

        c.setText("Q" + quizCount);

        Random random = new Random();
        int randNum = random.nextInt(FEquestion.size());

        ArrayList<String> quiz = new ArrayList<>();

        quiz = FEquestion.get(randNum);

        q.setText(quiz.get(0));

        quiz.remove(0);

        rightAnswer = quiz.get(0);

        Collections.shuffle(quiz);

        a1.setText(quiz.get(0));
        a2.setText(quiz.get(1));
        a3.setText(quiz.get(2));
        a4.setText(quiz.get(3));

        FEquestion.remove(randNum);
    }

    public void checkAnswer(View view){

        Button answerBtn = findViewById(view.getId());
        String answerTxt = answerBtn.getText().toString();

        String notice;

        if (answerTxt == rightAnswer){

            notice = "正解！";
            rightCount++;
            Continuos++;

            if (Continuos > 1) {

                notice = Continuos + "連続正解";
            }

        }else {

            notice = "不正解";
            Continuos = 0;
        }

        AlertDialog.Builder Notice = new AlertDialog.Builder(this);
        Notice.setTitle(notice);
        Notice.setMessage("答え:" + rightAnswer);
        Notice.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (rightCount == Qvolume){
                    Intent jorney = new Intent(getApplicationContext(), ResultActivity.class);
                    jorney.putExtra("RIGHT_ANSWER_COUNT",rightCount);
                    startActivity(jorney);
                }else{
                    quizCount++;
                    OpenQ();
                }

            }
        });

        Notice.setCancelable(false);
        Notice.show();
    }
}