package com.sofdigitalhackathon.libertypolls.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.adapters.PieFragmentAdapter;
import com.sofdigitalhackathon.libertypolls.model.Question;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PollSummary extends AppCompatActivity {
    ViewPager pager;
    Button bSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_summary);
        InitReferences();
        Init();
    }

    private void InitReferences() {
        pager = findViewById(R.id.poll_summary_pager);
        bSummary = findViewById(R.id.poll_summary_button);

        Type listType = new TypeToken<List<Question>>(){}.getType();
        List<Question> questions = new Gson().fromJson(getIntent().getStringExtra("questions"),listType);
        PieFragmentAdapter adapter = new PieFragmentAdapter(getSupportFragmentManager(),questions);
        pager.setAdapter(adapter);
    }

    private void Init() {
    }

}
