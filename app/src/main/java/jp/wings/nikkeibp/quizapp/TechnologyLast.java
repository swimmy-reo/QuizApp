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

public class TechnologyLast extends AppCompatActivity {
    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private  Button answerBtn4;


    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>> FEquestion = new ArrayList<>();

    String quizdata[][] = {
            {"デバックとは","不具合修正","こっそり","人質取り","ピンポイント"},
            {"機密性とは","秘密にする","欠点無し","使用可","犯人お前"},
            {"完全性とは","欠点無し","こっそり","壊れない","ログ取った？"},
            {"クラッキングとは","PC有悪事","PC無悪事","自動で","パクる"},
            {"ソーシャルエンジニアリング\nとは","PC無悪事","盗んだ奴で","パス固定","HP見た人"},
            {"スパイウェアとは","パクる","PC有悪事","こっそり","人質取り"},
            {"DoS攻撃とは","テンパらす","みんなで","ID固定","HP見た人"},
            {"ゼロデイ攻撃とは","対策前に","パス盗む","HPに悪事","こっそり"},
            {"バックドアとは","裏口侵入","知らん人と","HP見た人","みんなで"},
            {"BYODとは","私物端末で","自動で","パクる","ピンポイント"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technologylast);


        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);


        for (int i = 0; i < quizdata.length; i++) {


            ArrayList<String> tmpArray = new ArrayList<>();


            tmpArray.add(quizdata[i][0]);
            tmpArray.add(quizdata[i][1]);
            tmpArray.add(quizdata[i][2]);
            tmpArray.add(quizdata[i][3]);
            tmpArray.add(quizdata[i][4]);

          //tmpArrayをquizArrayに追加する
            FEquestion.add(tmpArray);
        }

        showNextQuiz();
        }

        public void showNextQuiz() {

           //クイズカウントレベルを更新
           countLabel.setText("Q" + quizCount);

            //ランダムな数字を取得
            Random random = new Random();
            int randomNum = random.nextInt(FEquestion.size());

            //randomNumを使って、quizArrayからクイズを一つ取り出す
            ArrayList<String> quiz = FEquestion.get(randomNum);

            //問題文を表示
            questionLabel.setText(quiz.get(0));

           //正解をrightAnswerにセット
            rightAnswer = quiz.get(1);

            //クイズ配列から問題文を削除
            quiz.remove(0);

            //正解と選択肢3つをシャッフル
            Collections.shuffle(quiz);

            //回答ボタンに正解と選択3つ表示
            answerBtn1.setText(quiz.get(0));
            answerBtn2.setText(quiz.get(1));
            answerBtn3.setText(quiz.get(2));
            answerBtn4.setText(quiz.get(3));

            //このクイズをquizArrayから削除
            FEquestion.remove(randomNum);

        }

        public void checkAnswer(View view) {

        //どの回答ボタンが押されたか
            Button answerBtn = findViewById(view.getId());
            String btmText = answerBtn.getText().toString();

            String alertTitle;
            if(btmText.equals(rightAnswer)) {
                alertTitle = "正解！";
                rightAnswerCount++;
            } else {
                alertTitle = "不正解……";
            }

            //ダイアログを作成
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(alertTitle);
            builder.setMessage("答え:"+rightAnswer);
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(quizCount == QUIZ_COUNT) {
                        //結果画面へ移動
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount);
                        startActivity(intent);
                    }else{
                        quizCount++;
                        showNextQuiz();
                    }
                }
            });
            builder.setCancelable(false);
            builder.show();
        }

    }