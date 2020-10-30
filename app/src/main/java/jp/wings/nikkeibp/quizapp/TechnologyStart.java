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

public class TechnologyStart extends AppCompatActivity {

    private TextView c, q;
    private Button a1, a2, a3, a4;
    private int rightCount = 0, quizCount = 1, Continuos = 0, Qvolume = 5;
    private String rightAnswer;

    ArrayList<ArrayList<String>> FEquestion = new ArrayList<>();

    String quiz[][] = {
            {"インターフェースとは", "接点", "元締め", "皆に送る", "一人に送る"},
            {"ラジオボタンとは", "択一", "一本に", "輪状", "元締め経由"},
            {"フレームレートとは", "一枚何秒？", "衝突OK", "受信ルール", "128b"},
            {"正規化とは", "Ⅰ重複無し\nⅡ参照設定", "送受信ルール", "端末付近", "メモ的な"},
            {"ロールバックとは", "銀行ATM", "接続口", "時刻合わせ", "日本語画像"},
            {"ロールフォワードとは", "やりかけ消", "無線LAN名\n6文字すまぬ・・・", "橋渡し役", "32b"},
            {"LANとは", "内輪", "広い", "次の届先", "最終届先"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology_start);

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


    public void checkAnswer(View view) {

        Button answerBtn = findViewById(view.getId());
        String answerTxt = answerBtn.getText().toString();

        String notice;

        if (answerTxt == rightAnswer) {

            notice = "正解！";
            rightCount++;
            Continuos++;

            if (Continuos > 1) {

                notice = Continuos + "連続正解";
            }

        } else {

            notice = "不正解";
            Continuos = 0;
        }

        AlertDialog.Builder Notice = new AlertDialog.Builder(this);
        Notice.setTitle(notice);
        Notice.setMessage("答え:" + rightAnswer);
        Notice.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (rightCount == Qvolume) {
                    Intent jorney = new Intent(getApplicationContext(), ResultActivity.class);
                    jorney.putExtra("RIGHT_ANSWER_COUNT", rightCount);
                    startActivity(jorney);
                } else {
                    quizCount++;
                    ServiceManagement Open = new ServiceManagement();
                    OpenQ();
                }

            }
        });

        Notice.setCancelable(false);
        Notice.show();

    }
}