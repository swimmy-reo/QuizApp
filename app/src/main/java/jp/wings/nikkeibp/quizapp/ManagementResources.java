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

public class ManagementResources extends AppCompatActivity {


    private TextView c;
    private TextView q2;
    private Button b1, b2, b3, b4;

    private int quizCount = 1;
    private int QuizCount = 5;
    private String rightAnswer;
    private int rightCount;
    private int Continuous = 0;

    ArrayList<ArrayList<String>> FEquestion2 = new ArrayList<>();
    String quizData2[][] = {
            {"コアコンピタンスとは", "自社の強み", "自社株購入", "工場なし", "強弱脅威",},
            {"アウトソーシングとは", "外注", "流通経路", "成長と占有", "全員標的",},
            {"ベンチマーキングとは", "敵に学ぶ", "体験談", "サクラ標的", "グループ分け",},
            {"M＆Aとは", "合併と買収", "自社の強み", "外注", "流通経路",},
            {"ＳＷＯＴ分析とは", "強弱脅威", "敵に学ぶ", "製品の一生", "自社の強み",},
            {"PPM","成長と占有","強弱脅威","参考書","製品の一生"},
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_resources);

        c = findViewById(R.id.countLabel_2);
        q2 = findViewById(R.id.questionLabel_2);
        b1 = findViewById(R.id.answerBtn1_2);
        b2 = findViewById(R.id.answerBtn2_2);
        b3 = findViewById(R.id.answerBtn3_2);
        b4 = findViewById(R.id.answerBtn4_2);



        for(int i = 0; i<quizData2.length;i++){
        ArrayList<String> tempra2 = new ArrayList<>();


        tempra2.add(quizData2[i][0]);
        tempra2.add(quizData2[i][1]);
        tempra2.add(quizData2[i][2]);
        tempra2.add(quizData2[i][3]);
        tempra2.add(quizData2[i][4]);

        FEquestion2.add(tempra2);

    }

        showQuiz2();

}

                public void showQuiz2(){

                c.setText("Q" + quizCount);

                Random r = new Random();
                int randNum = r.nextInt(FEquestion2.size());

                ArrayList<String> quiz1 = FEquestion2.get(randNum);

                q2.setText(quiz1.get(0));

                quiz1.remove(0);

                rightAnswer = quiz1.get(0);

                Collections.shuffle(quiz1);

                b1.setText(quiz1.get(0));
                b2.setText(quiz1.get(1));
                b3.setText(quiz1.get(2));
                b4.setText(quiz1.get(3));

                FEquestion2.remove(randNum);
    }


        public void checkAnswer(View view) {

        Button answerBtn = findViewById(view.getId());
        String answerText = answerBtn.getText().toString();

        String notice;
        if (answerText.equals(rightAnswer)) {

            notice = "正解です";
            Continuous++;
            rightCount++;

            if(Continuous > 1) {
                notice = Continuous + "連続正解";
            }

            }else{
            notice = "不正解……";
            Continuous = 0;
        }

            AlertDialog.Builder Notice = new AlertDialog.Builder(this);
            Notice.setTitle(notice);
            Notice.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (quizCount == QuizCount) {

                        Intent journey = new Intent(getApplicationContext(), ResultActivity.class);
                        journey.putExtra("RIGHT_ANSWER_COUNT",rightCount);
                        startActivity(journey);
                    }else{
                        quizCount++;
                        showQuiz2();

                    }
                }
            });

            Notice.setCancelable(false);
            Notice.show();
    }
}

