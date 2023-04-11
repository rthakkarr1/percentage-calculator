package com.example.percentageapp_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Declare EditTexts
    EditText totalStudents, groupA, groupB, groupC, groupD, groupF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditTexts
        totalStudents = findViewById(R.id.total_students);
        groupA = findViewById(R.id.group_a);
        groupB = findViewById(R.id.group_b);
        groupC = findViewById(R.id.group_c);
        groupD = findViewById(R.id.group_d);
        groupF = findViewById(R.id.group_f);

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePercentages();
            }
        });
    }

    private void calculatePercentages() {
        int total = Integer.parseInt(totalStudents.getText().toString());
        int a = Integer.parseInt(groupA.getText().toString());
        int b = Integer.parseInt(groupB.getText().toString());
        int c = Integer.parseInt(groupC.getText().toString());
        int d = Integer.parseInt(groupD.getText().toString());
        int f = Integer.parseInt(groupF.getText().toString());

        float percentageA = (float) a / total * 100;
        float percentageB = (float) b / total * 100;
        float percentageC = (float) c / total * 100;
        float percentageD = (float) d / total * 100;
        float percentageF = (float) f / total * 100;

        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
        intent.putExtra("percentageA", percentageA);
        intent.putExtra("percentageB", percentageB);
        intent.putExtra("percentageC", percentageC);
        intent.putExtra("percentageD", percentageD);
        intent.putExtra("percentageF", percentageF);

        startActivity(intent);
    }
}
