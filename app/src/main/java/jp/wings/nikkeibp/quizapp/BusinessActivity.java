package jp.wings.nikkeibp.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BusinessActivity extends AppCompatActivity {

    private TextView c;
    private TextView questionLabel_1;
    private Button answerBtn1_1;
    private Button answerBtn1_2;
    private Button answerBtn1_3;
    private Button answerBtn1_4;

    private String rightAnswer1;
    private int rightAnswerCount1 = 0;
    private int quizCount1 = 1;
    static private int Quiz_Count1 = 5;
    ArrayList<ArrayList<String>> FEquestion1 = new ArrayList<>();

    String quizData1[][] = {
            {"経営資源とは？","人物金情報","根本的改善","人材管理","生活改善",},
            {"ブレーンストリーミング","アイデア！","理想と現実","点","作り手無罪",},
            {"BC","とりま継続","社会の事も","現場外研修","不正確認",},
            {"CSR","社会の事も","人物金情報","工業ルール","生活改善",},
            {"OJT","現場研修","累積比率","ばらつき","根本的改善",},
            {"リテラシ","知識や理解","工業ルール","アイデア！","社会のことも",},
            {"パレート図","累積比率","ばらつき","理想と現実","点",},
            {"コンプライアンス","ルール守る","作り手無罪","NO解雇","知識や理解",},
            {"コーポレートガバナンス","不正確認","人材管理","工業ルール","とりま継続",},
            {"JIS","工業ルール","分析ルール","3段評価","能力判定",},
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_and_legal_affairs);

        c = findViewById(R.id.countLabel_1);
        questionLabel_1 = findViewById(R.id.questionLabel_1);
        answerBtn1_1 = findViewById(R.id.answerBtn1_1);
        answerBtn1_2 = findViewById(R.id.answerBtn1_2);
        answerBtn1_3 = findViewById(R.id.answerBtn1_3);
        answerBtn1_4 = findViewById(R.id.answerBtn1_4);

        for(int i = 0; i < quizData1.length; i++) {

            ArrayList<String> tempra1 = new ArrayList<>();

            tempra1.add(quizData1[i][0]);
            tempra1.add(quizData1[i][1]);
            tempra1.add(quizData1[i][2]);
            tempra1.add(quizData1[i][3]);
            tempra1.add(quizData1[i][4]);

            FEquestion1.add(tempra1);
        }

        showNextQuiz1();
    }

    private void showNextQuiz1() {

            c.setText("Q"+quizCount1);

            Random random = new Random();
            int randNum1 = random.nextInt(FEquestion1.size());

            ArrayList<String> quiz1 = FEquestion1.get(randNum1);

            questionLabel_1.setText(quiz1.get(0));

            rightAnswer1 = quiz1.get(1);

            quiz1.remove(0);

            Collections.shuffle(quiz1);

            answerBtn1_1.setText(quiz1.get(0));
            answerBtn1_2.setText(quiz1.get(1));
            answerBtn1_3.setText(quiz1.get(2));
            answerBtn1_4.setText(quiz1.get(3));

            FEquestion1.remove(randNum1);
            }

    public void checkAnswer(View view) {

            Button answerBtn1 = findViewById(view.getId());
            String btmtext1 = answerBtn1.getText().toString();

            String alertTitle_1;
            if (btmtext1.equals(rightAnswer1)) {
            alertTitle_1 = "大正解";
            rightAnswerCount1++;
            } else {
            alertTitle_1 = "不正解！";
            }

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle(alertTitle_1);
            builder1.setMessage("答え"+rightAnswerCount1);
            builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount1 == Quiz_Count1) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT"   , rightAnswerCount1);
                    startActivity(intent);
                } else {
                    quizCount1++;
                    showNextQuiz1();

                }
            }

        });
        builder1.setCancelable(false);
        builder1.show();
    }



}
