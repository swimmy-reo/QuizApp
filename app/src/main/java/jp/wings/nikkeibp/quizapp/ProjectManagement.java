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

public class ProjectManagement extends AppCompatActivity {

    private TextView c,q;
    private Button a1,a2,a3,a4;

    private int quizCount = 1;
    private String rightAnswer;
    private int Continuos = 0;
    private int Qvolume = 5;
    private int rightCount = 0;

    String quizData[][] = {

            {"プロジェクトマネージャーとは","要員管理","範囲","丸矢印数字","Ⅰ成果物\nⅡ必要なもの",},
            {"ステークホルダーとは","関係者全員","ゴールがある","横線が期間","分割",},
            {"スコープとは","Ⅰ成果物\nⅡ必要なもの","始終決める","ゴールがある","窓口",},
            {"ガントチャートとは","横線が期間","分割","片方口出し","完成度調べ",},
            {"アローダイアグラムとは","丸矢印数字","要員管理","石橋叩く","関係者全員",},

    };

    ArrayList<ArrayList<String>> FEquestion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_management);

        c = findViewById(R.id.countLabel);
        q = findViewById(R.id.questionLabel);
        a1 = findViewById(R.id.answerBtn1);
        a2 = findViewById(R.id.answerBtn2);
        a3 = findViewById(R.id.answerBtn3);
        a4 = findViewById(R.id.answerBtn4);

        for(int i = 0; i < 5; i++){

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

            ArrayList<String> quiz =  new ArrayList<>();

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
        if (rightAnswer.equals(answerTxt)){

            notice = "正解だよ！";
            rightCount++;
            Continuos++;
            if (Continuos > 1){
                notice = Continuos + "連続正解";
            }
        }else{
            notice = "不正解です";
            Continuos = 0;
        }

            AlertDialog.Builder Notice = new AlertDialog.Builder(this);
            Notice.setTitle(notice);
            Notice.setMessage("答え" + rightAnswer);
            Notice.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (Qvolume == quizCount){
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