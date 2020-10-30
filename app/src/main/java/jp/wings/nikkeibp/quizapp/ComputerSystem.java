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

public class ComputerSystem extends AppCompatActivity {

    private TextView c, q;
    private Button a1, a2, a3, a4;
    private int rightCount = 0, quizCount = 1, Continuos = 0, Qvolume = 5;
    private String rightAnswer;

    ArrayList<ArrayList<String>> FEquestion = new ArrayList<>();

    String quiz[][] = {

            {"GPU", "画像処理", "トイレ行く", "弱点克服", "一つずつ"},
            {"メモリ", "作業机的な", "ONでも抜差\n6文字すまぬ・・・", "電波ルール", "ピッ！"},
            {"RAM", "読書可", "くっ付ける", "対象観測", "動作変換"},
            {"SRAM", "容量小", "挿せばOK", "一点集中", "分散"},
            {"DRAM", "容量大", "なりすまし", "なんか安心", "片方予備"},
            {"EEPROM", "電気消去可", "レプリカ", "類友", "直ぐに"},
            {"ストライピング", "交互に保存", "複数に保存", "サーバ依存", "動画配信"},
            {"ミラーリング", "複数に保存", "交互に保存", "故障しなさ", "ミスなし"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_system);

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