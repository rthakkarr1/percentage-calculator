package com.example.percentageapp_exam;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get the percentages from the Intent
        float percentageA = getIntent().getFloatExtra("percentageA", 0);
        float percentageB = getIntent().getFloatExtra("percentageB", 0);
        float percentageC = getIntent().getFloatExtra("percentageC", 0);
        float percentageD = getIntent().getFloatExtra("percentageD", 0);
        float percentageF = getIntent().getFloatExtra("percentageF", 0);

        TextView percentageATextView = findViewById(R.id.percentage_a);
        TextView percentageBTextView = findViewById(R.id.percentage_b);
        TextView percentageCTextView = findViewById(R.id.percentage_c);
        TextView percentageDTextView = findViewById(R.id.percentage_d);
        TextView percentageFTextView = findViewById(R.id.percentage_f);

        percentageATextView.setText("A: " + String.format("%.2f", percentageA) + "%");
        percentageBTextView.setText("B: " + String.format("%.2f", percentageB) + "%");
        percentageCTextView.setText("C: " + String.format("%.2f", percentageC) + "%");
        percentageDTextView.setText("D: " + String.format("%.2f", percentageD) + "%");
        percentageFTextView.setText("F: " + String.format("%.2f", percentageF) + "%");

        Button plotButton = findViewById(R.id.plot_button);
        plotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plotBarChart(percentageA, percentageB, percentageC, percentageD, percentageF);
            }
        });
    }

    private void plotBarChart(float percentageA, float percentageB, float percentageC, float percentageD, float percentageF) {
        setContentView(R.layout.activity_chart);

        LinearLayout barContainer = findViewById(R.id.bar_container);
        LinearLayout labelContainer = findViewById(R.id.label_container);

        ArrayList<Float> percentages = new ArrayList<>();
        percentages.add(percentageA);
        percentages.add(percentageB);
        percentages.add(percentageC);
        percentages.add(percentageD);
        percentages.add(percentageF);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("A");
        labels.add("B");
        labels.add("C");
        labels.add("D");
        labels.add("F");

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#4CAF50"));
        colors.add(Color.parseColor("#FFEB3B"));
        colors.add(Color.parseColor("#FF9800"));
        colors.add(Color.parseColor("#F44336"));
        colors.add(Color.parseColor("#9E9E9E"));

        int barWidth = 100;
        int barSpacing = 50;
        int maxHeight = 600;

        float maxPercentage = 0;
        for (float percentage : percentages) {
            if (percentage > maxPercentage) {
                maxPercentage = percentage;
            }
        }
        for (int i = 0; i < percentages.size(); i++) {
            View bar = new View(this);
            int barHeight = (int) (percentages.get(i) / maxPercentage * maxHeight);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(barWidth, barHeight, Gravity.BOTTOM);

            params.setMargins(barSpacing, 0, 0, 0);
            bar.setLayoutParams(params);
            bar.setBackgroundColor(colors.get(i));
            barContainer.addView(bar);

            TextView label = new TextView(this);
            label.setLayoutParams(params);
            barContainer.setGravity(Gravity.BOTTOM);
            label.setGravity(Gravity.CENTER);
            label.setText(labels.get(i) + ": " + String.format("%.2f", percentages.get(i)) + "%");

            labelContainer.addView(label);
        }
    }}
