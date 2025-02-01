package com.example.finanaspessoais;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdicionarDespesaActivity extends AppCompatActivity {

    private EditText tituloEditText;
    private EditText valorEditText;
    private EditText categoriaEditText;
    private Button salvarButton;
    private Button cancelarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_despesa);

        tituloEditText = findViewById(R.id.tituloEditText);
        valorEditText = findViewById(R.id.valorEditText);
        categoriaEditText = findViewById(R.id.categoriaEditText);
        salvarButton = findViewById(R.id.salvarButton);
        cancelarButton = findViewById(R.id.cancelarButton);

        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdicionarDespesaActivity.this, "Despesa salva!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
