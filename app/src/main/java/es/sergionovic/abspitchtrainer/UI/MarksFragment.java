package es.sergionovic.abspitchtrainer.UI;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import es.sergionovic.abspitchtrainer.DB.DataBaseHandler;
import es.sergionovic.abspitchtrainer.Model.Statistic;
import es.sergionovic.abspitchtrainer.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarksFragment extends android.support.v4.app.Fragment {

    private Context context;

    private PieChart pieChart;
    private HorizontalBarChart barChart;
    private BarChart barChart2;
    View layout;

    public MarksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_marks, container, false);

        context = getActivity();

        createPieGraphic();

        createHorizontalBarGraphic();

        createLineGraphic();

        return layout;
    }

    private void createLineGraphic() {
        List<Statistic> statistics = getStatitics();
        float pitchCounter = 0, intervalCounter = 0, scaleCounter = 0, chordCounter = 0, rythmCounter = 0;
        float pitchSucces = 0, intervalSucces = 0, scaleSucces = 0, chordSucces = 0, rythmSucces = 0;
        for (int i = 0; i < statistics.size(); i++) {
            switch (statistics.get(i).getExercise_id()) {
                case "0":
                    pitchCounter += 1;
                    if (statistics.get(i).isSuccess())
                        pitchSucces += 1;
                    break;
                case "1":
                    intervalCounter += 1;
                    if (statistics.get(i).isSuccess())
                        intervalSucces += 1;
                    break;
                case "2":
                    scaleCounter += 1;
                    if (statistics.get(i).isSuccess())
                        scaleSucces += 1;
                    break;
                case "3":
                    chordCounter += 1;
                    if (statistics.get(i).isSuccess())
                        chordSucces += 1;
                    break;
                case "4":
                    rythmCounter += 1;
                    if (statistics.get(i).isSuccess())
                        rythmSucces += 1;
                    break;
            }
        }

        float pitchMean = (pitchSucces * 100) / pitchCounter;
        if (pitchMean == 0)
            pitchMean = 1;

        float intervalMean = (intervalSucces * 100) / intervalCounter;
        if (intervalMean == 0)
            intervalMean = 1;

        float scaleMean = (scaleSucces * 100) / scaleCounter;
        if (scaleMean == 0)
            scaleMean = 1;

        float chordsMean = (chordSucces * 100) / chordCounter;
        if (chordsMean == 0)
            chordsMean = 1;

        float rythmMean = (rythmSucces * 100) / rythmCounter;
        if (rythmMean == 0)
            rythmMean = 1;

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(pitchMean, 0));
        entries.add(new BarEntry(intervalMean, 1));
        entries.add(new BarEntry(scaleMean, 2));
        entries.add(new BarEntry(chordsMean, 3));
        entries.add(new BarEntry(rythmMean, 4));

        BarDataSet dataSet = new BarDataSet(entries, "");

        dataSet.setValueTextSize(18);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        String[] labels = getResources().getStringArray(R.array.tabs);

        barChart2 = new BarChart(context);
        barChart2 = (BarChart) layout.findViewById(R.id.barChart2);

        BarData data = new BarData(labels, dataSet);

        barChart2.setData(data);
        barChart2.setDescription("");

        barChart2.animateY(1000);
    }

    private void createPieGraphic() {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(18f, 0));
        entries.add(new Entry(22f, 1));
        entries.add(new Entry(45f, 2));
        entries.add(new Entry(5f, 3));
        entries.add(new Entry(10f, 4));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setValueTextSize(18);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        String[] labels = getResources().getStringArray(R.array.tabs);

        pieChart = new PieChart(context);
        pieChart = (PieChart) layout.findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(false);

        PieData data = new PieData(labels, dataSet);
        pieChart.setData(data);
        pieChart.setDescription("");
        pieChart.setTouchEnabled(false);
        pieChart.animateY(1000);

    }

    public void createHorizontalBarGraphic() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(18f, 0));
        entries.add(new BarEntry(22f, 1));
        entries.add(new BarEntry(45f, 2));
        entries.add(new BarEntry(5f, 3));
        entries.add(new BarEntry(10f, 4));

        BarDataSet dataSet = new BarDataSet(entries, "");

        dataSet.setValueTextSize(18);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        String[] labels = getResources().getStringArray(R.array.tabs);

        barChart = new HorizontalBarChart(context);
        barChart = (HorizontalBarChart) layout.findViewById(R.id.barChart);

        BarData data = new BarData(labels, dataSet);

        barChart.setData(data);
        barChart.setDescription("");

        barChart.animateY(1000);


    }

    public List<Statistic> getStatitics() {
        return DataBaseHandler.getStatistic(getActivity());
    }

}
