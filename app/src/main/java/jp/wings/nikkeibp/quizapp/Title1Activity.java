package jp.wings.nikkeibp.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Title1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title1);

        Button businessAndlegal = findViewById(R.id.businessandlegal);
        businessAndlegal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
                startActivity(intent);
            }
        });

        //★
        Button nextpageButton = findViewById(R.id.nextpage);
        nextpageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Title2Activity.class);
                startActivity(intent);
            }
        });

        Button management = findViewById(R.id.management);
        management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ManagementResources.class);
                startActivity(intent);

            }
        });

        Button systemStrategy = findViewById(R.id.systemStrategy);
        systemStrategy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SystemStrategy.class);
                startActivity(intent);
            }
        });

        Button developTech = findViewById(R.id.developTech);
        developTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DevelopmentTech.class);
                startActivity(intent);
            }
        });

        final Button projectManagement = findViewById(R.id.projectManagement);
        projectManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProjectManagement.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);

            }
        });

        final Button serviceMabagement = findViewById(R.id.serviceManagement);
        serviceMabagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ServiceManagement.class);
                startActivity(intent);

            }
        });
    }
}