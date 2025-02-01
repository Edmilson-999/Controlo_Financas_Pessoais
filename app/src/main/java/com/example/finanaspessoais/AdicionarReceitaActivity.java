package com.example.finanaspessoais;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.finanaspessoais.databinding.ActivityAdicionarReceitaBinding;

public class AdicionarReceitaActivity extends AppCompatActivity {

    private ActivityAdicionarReceitaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdicionarReceitaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.salvarButton.setOnClickListener(v -> salvarReceita());
        binding.cancelarButton.setOnClickListener(v -> finish());
    }

    private void salvarReceita() {
        String titulo = binding.tituloEditText.getText().toString().trim();
        String valorStr = binding.valorEditText.getText().toString().trim();
        String categoria = binding.categoriaEditText.getText().toString().trim();

        if (titulo.isEmpty() || valorStr.isEmpty() || categoria.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(valorStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor inválido!", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Receita salva!", Toast.LENGTH_SHORT).show();
        finish();
    }
}