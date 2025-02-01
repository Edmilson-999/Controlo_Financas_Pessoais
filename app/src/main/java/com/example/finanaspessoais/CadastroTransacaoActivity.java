package com.example.finanaspessoais;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CadastroTransacaoActivity extends AppCompatActivity {

    private EditText etTitulo, etValor;
    private Spinner spCategoria;
    private TextView tvDataSelecionada;
    private Button btnData, btnSalvar, btnCancelar;

    private String dataSelecionada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_transacao);

        etTitulo = findViewById(R.id.et_titulo);
        etValor = findViewById(R.id.et_valor);
        spCategoria = findViewById(R.id.sp_categoria);
        tvDataSelecionada = findViewById(R.id.tv_data_selecionada);
        btnData = findViewById(R.id.btn_data);
        btnSalvar = findViewById(R.id.btn_salvar);
        btnCancelar = findViewById(R.id.btn_cancelar);

        configurarCategorias();

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarTransacao();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void configurarCategorias() {
        // Exemplo de categorias
        String[] categorias = {"Alimentação", "Transporte", "Salário", "Lazer", "Outros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategoria.setAdapter(adapter);
    }

    private void mostrarDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dataSelecionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                tvDataSelecionada.setText("Data: " + dataSelecionada);
            }
        }, ano, mes, dia);

        datePickerDialog.show();
    }

    private void salvarTransacao() {
        String titulo = etTitulo.getText().toString().trim();
        String valorStr = etValor.getText().toString().trim();
        String categoria = spCategoria.getSelectedItem().toString();

        if (titulo.isEmpty() || valorStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
            return;
        }

        double valor = Double.parseDouble(valorStr);

        Toast.makeText(this, "Transação salva com sucesso!", Toast.LENGTH_SHORT).show();
        finish(); // Volta para a tela principal
    }
}
