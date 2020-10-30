package jp.wings.nikkeibp.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



    public class ResultActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);

            TextView resultLabel = findViewById(R.id.resultLabel);
            TextView totalScoreLabel = findViewById(R.id.totalScoreLabel);
            int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);
            resultLabel.setText(score + " /5");

            SharedPreferences prefs = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
            int totalscore = prefs.getInt("totalscore", 0);

            totalscore += score;

            resultLabel.setText(score + " / 5");
            totalScoreLabel.setText("トータルスコア:" + totalscore);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("totalscore", totalscore);
            editor.apply();

            Button returnButton = findViewById(R.id.returnTop);
            returnButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Title1Activity.class);
                    startActivity(intent);
                }
            });
        }

    }


