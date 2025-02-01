package com.example.finanaspessoais;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaTransacoesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransacaoAdapter adapter;
    private List<Transacao> transacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_transacoes);

        AppDatabase db = AppDatabase.getInstance(this);

        // Obter as transações do banco de dados
        List<Transacao> transacoes = db.transacaoDao().getAllTransacoes();

        RecyclerView recyclerView = findViewById(R.id.recycler_transacoes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        transacoes = new ArrayList<>();
        transacoes.add(new Transacao("Salário", 5000.00, "Salário", "01/01/2025", true));
        transacoes.add(new Transacao("Almoço", 50.00, "Alimentação", "02/01/2025", false));
        transacoes.add(new Transacao("Transporte", 20.00, "Transporte", "02/01/2025", false));

        adapter = new TransacaoAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
