package com.example.finanaspessoais;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraficoDespesasActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_despesas);

        pieChart = findViewById(R.id.pieChart);

        // Configurar gráfico
        setupPieChart();

        // Carregar dados
        loadChartData();
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(40f);
        pieChart.setTransparentCircleRadius(45f);
        pieChart.setCenterText("Despesas");
        pieChart.setCenterTextSize(18);
        pieChart.setEntryLabelTextSize(14f);
        pieChart.setEntryLabelColor(Color.BLACK);

        Legend legend = pieChart.getLegend(); // Corrigido: Removido o casting desnecessário
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        legend.setEnabled(true);
    }

    private void loadChartData() {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            List<Transacao> transacoes = db.transacaoDao().getAllTransacoes();

            Map<String, Double> despesasPorCategoria = new HashMap<>();
            for (Transacao transacao : transacoes) {
                if (!transacao.isReceita()) { // Verifica se é despesa
                    String categoria = transacao.getCategoria();
                    double valor = transacao.getValor();
                    despesasPorCategoria.put(categoria,
                            despesasPorCategoria.getOrDefault(categoria, 0.0) + valor);
                }
            }

            ArrayList<PieEntry> entries = new ArrayList<>();
            for (Map.Entry<String, Double> entry : despesasPorCategoria.entrySet()) {
                entries.add(new PieEntry(entry.getValue().floatValue(), entry.getKey()));
            }

            PieDataSet dataSet = new PieDataSet(entries, "Categorias");
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            dataSet.setValueTextSize(14f);
            dataSet.setValueTextColor(Color.BLACK);

            PieData data = new PieData(dataSet);
            runOnUiThread(() -> {
                pieChart.setData(data);
                pieChart.invalidate(); // Atualizar o gráfico
            });
        }).start();
    }
}
