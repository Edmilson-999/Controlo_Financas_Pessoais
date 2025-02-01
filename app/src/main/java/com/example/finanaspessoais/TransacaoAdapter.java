package com.example.finanaspessoais;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransacaoAdapter extends RecyclerView.Adapter<TransacaoAdapter.ViewHolder> {

    private List<Transacao> transacoes;
    private Context context;
    private AppDatabase db; // Referência ao banco de dados

    // Construtor
    public TransacaoAdapter(Context context) {
        this.transacoes = transacoes;
        this.context = context;
        this.db = AppDatabase.getInstance(context); // Inicializando o banco de dados
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transicao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transacao transacao = transacoes.get(position);

        holder.tvTitulo.setText(transacao.getTitulo());
        holder.tvValor.setText((transacao.isReceita() ? "+ " : "- ") + "R$ " + transacao.getValor());
        holder.tvCategoriaData.setText(transacao.getCategoria() + " - " + transacao.getData());

        // Clique longo para exibir opções de editar ou remover
        holder.itemView.setOnLongClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Ação na Transação")
                    .setMessage("Deseja editar ou remover esta transação?")
                    .setPositiveButton("Editar", (dialog, which) -> {

                        Toast.makeText(context, "Editar: " + transacao.getTitulo(), Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Remover", (dialog, which) -> {
                        removerTransacao(position);
                    })
                    .setNeutralButton("Cancelar", null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return transacoes.size();
    }

    public void removerTransacao(int position) {
        Transacao transacao = transacoes.get(position);

        new Thread(() -> {
            db.transacaoDao().delete(transacao);

            ((Activity) context).runOnUiThread(() -> {
                transacoes.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Transação removida.", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    public void setTransacoes(List<Transacao> transacoes) {
    }

    // Classe ViewHolder para vincular os elementos do layout.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvValor, tvCategoriaData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tv_titulo);
            tvValor = itemView.findViewById(R.id.tv_valor);
            tvCategoriaData = itemView.findViewById(R.id.tv_categoria_data);
        }
    }
}
