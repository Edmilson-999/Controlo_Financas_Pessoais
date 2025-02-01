package com.example.finanaspessoais;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.finanaspessoais.databinding.ActivityVisualizarTransacoesBinding;

import java.util.List;

public class VisualizarTransacoesActivity extends AppCompatActivity {

    private ActivityVisualizarTransacoesBinding binding;
    private TransacaoAdapter adapter;
    private List<Transacao> transacaoList;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisualizarTransacoesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getInstance(this);

        adapter = new TransacaoAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        carregarTransacoes();
    }

    private void carregarTransacoes() {
        new CarregarTransacoesTask().execute();
    }

    private class CarregarTransacoesTask extends AsyncTask<Void, Void, List<Transacao>> {

        @Override
        protected List<Transacao> doInBackground(Void... voids) {
            return db.transacaoDao().getAllTransacoes();
        }

        @Override
        protected void onPostExecute(List<Transacao> transacoes) {
            adapter.setTransacoes(transacoes);
        }
    }
}