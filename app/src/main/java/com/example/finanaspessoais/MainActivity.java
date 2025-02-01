package com.example.finanaspessoais;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.view.View;
import android.widget.Button;

import com.example.finanaspessoais.MetaNotificationWorker;
import com.example.finanaspessoais.NotificationWorker;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionarReceita;
    private Button btnAdicionarDespesa;
    private Button btnVisualizarTransacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdicionarReceita = findViewById(R.id.btn_adicionar_receita);
        btnAdicionarDespesa = findViewById(R.id.btn_adicionar_despesa);
        btnVisualizarTransacoes = findViewById(R.id.btn_visualizar_transacoes);

        btnAdicionarReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdicionarReceitaActivity.class);
                startActivity(intent);
            }
        });

        btnAdicionarDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdicionarDespesaActivity.class);
                startActivity(intent);
            }
        });

        btnVisualizarTransacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VisualizarTransacoesActivity.class);
                startActivity(intent);
            }
        });

        scheduleDailyNotification();
        scheduleMetaNotification();
    }

    private void scheduleDailyNotification() {
        WorkRequest dailyWorkRequest = new PeriodicWorkRequest.Builder(NotificationWorker.class, 24, TimeUnit.HOURS)
                .setInitialDelay(calculateInitialDelay(), TimeUnit.MILLISECONDS)
                .build();

        WorkManager.getInstance(this).enqueue(dailyWorkRequest);
    }

    private void scheduleMetaNotification() {
        WorkRequest metaWorkRequest = new PeriodicWorkRequest.Builder(MetaNotificationWorker.class, 1, TimeUnit.DAYS)
                .build();

        WorkManager.getInstance(this).enqueue(metaWorkRequest);
    }

    private long calculateInitialDelay() {
        Calendar calendar = Calendar.getInstance();
        Calendar currentTime = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(currentTime)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return calendar.getTimeInMillis() - currentTime.getTimeInMillis();
    }
}
