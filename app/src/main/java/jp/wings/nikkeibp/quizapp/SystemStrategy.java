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

public class SystemStrategy extends AppCompatActivity {

    private TextView c,q;
    private Button a1,a2,a3,a4;

    private int Quiz_Volume = 5;
    private int Q_Count = 1;
    private String rightAnswer3;
    private int Continous = 0;
    private int rightCount = 0;

    String quizData3[][] = {
            {"SFAとは","営業支援","自主運用","サーバ借り","絶対必要"},
            {"バックエンドとは","見えない所","複数共有","使いやすさ","根本的改良"},
            {"フロントエンドとは","見える所","生活改善","情報格差","業務改善"},
            {"BPRとは","根本的改良","大量データ","自主運用","見えない所"},
            {"RPAとは","業務自動化","情報格差","見えることろ","複数共有"},
            {"インフラ","絶対必要","使いやすさ","営業支援","ゲーム要素"},
            {"ホスティングサービス","サーバ借り","構想具体化","見積書要求","エコな調達"},
    };

    ArrayList<ArrayList<String>> FEquestion3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_strategy);

        c = findViewById(R.id.countLabel_3);
        q = findViewById(R.id.questionLabel_3);
        a1 = findViewById(R.id.answerBtn1_3);
        a2 = findViewById(R.id.answerBtn2_3);
        a3 = findViewById(R.id.answerBtn3_3);
        a4 = findViewById(R.id.answerBtn4_3);



        for (int i = 0; i < 5; i++) {

            ArrayList<String> tmpra3 = new ArrayList<>();

            tmpra3.add(quizData3[i][0]);
            tmpra3.add(quizData3[i][1]);
            tmpra3.add(quizData3[i][2]);
            tmpra3.add(quizData3[i][3]);
            tmpra3.add(quizData3[i][4]);

            FEquestion3.add(tmpra3);
        }

        OpenQuiz3();
    }


        public void OpenQuiz3(){

        c.setText("Q" + Q_Count);

        ArrayList<String> quiz3 = new ArrayList<>();

            Random r3 = new Random();
            int randomNum3 = r3.nextInt(FEquestion3.size());

            quiz3 = FEquestion3.get(randomNum3);

            q.setText(quiz3.get(0));

            quiz3.remove(0);

            rightAnswer3 = quiz3.get(0);
            Collections.shuffle(quiz3);

            a1.setText(quiz3.get(0));
            a2.setText(quiz3.get(1));
            a3.setText(quiz3.get(2));
            a4.setText(quiz3.get(3));

            FEquestion3.remove(randomNum3);

        }

        public void checkAnswer3(View view){

            Button answerBtn3 = findViewById(view.getId());
            String answerTxt3 = answerBtn3.getText().toString();

            String notice3;


            if (answerTxt3.equals(rightAnswer3)) {

                notice3 = "正解です！";
                rightCount++;
                Continous++;

                if (Continous > 1){

                    notice3 = Continous + "連続正解";

                }
            }else{

                notice3 = "不正解です";
                Continous = 0;

            }

            AlertDialog.Builder Notice = new AlertDialog.Builder(this);
            Notice.setTitle(notice3);
            Notice.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (Q_Count == Quiz_Volume){

                        Intent journey = new Intent(getApplicationContext(), ResultActivity.class);
                        journey.putExtra("RIGHT_ANSWER_COUNT",rightCount);
                        startActivity(journey);
                    }else{

                        Q_Count++;
                        OpenQuiz3();
                    }

                }
            });

            Notice.setCancelable(false);
            Notice.show();


            }
    }


