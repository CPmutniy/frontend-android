package com.sofdigitalhackathon.libertypolls.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.model.Answer;
import com.sofdigitalhackathon.libertypolls.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PieFragment extends Fragment {

    AnyChartView anyChartView;
    Pie pie;
    Question question;
    public PieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_pie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitReferences();
        Init();
    }

    private void InitReferences() {
        anyChartView = getView().findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(getView().findViewById(R.id.poll_summary_progress_bar));
        pie = AnyChart.pie();

    }

    private void Init() {
        question = new Gson().fromJson(getArguments().getString("question"),Question.class);
        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(getContext(), event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });
        int yes = 0 ;
        int no = 0;
        int desist = 0;
        for(Answer answer : question.getAnswerList()){
            switch (answer.getAnswer()){
                case Answer.ANSWER_YES: yes+=1;break;
                case Answer.ANSWER_NO: no +=1;break;
                case Answer.ANSWER_DESIST: desist+=1;break;
            }
        }
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("За", yes));
        data.add(new ValueDataEntry("Против", no));
        data.add(new ValueDataEntry("Воздержалось", desist));
        pie.data(data);

        pie.title(question.getTitle());
        pie.labels().position("outside");
        pie.palette(new String[]{"green","red","orange"});
        pie.legend().title().enabled(true);
        pie.legend().title()
                .text(String.format("Проголосовало %d человек",(yes+no+desist)))
                .padding(0d, 0d, 10d, 0d);
        pie.legend().fontSize(18);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);
        anyChartView.setChart(pie);
    }

}
