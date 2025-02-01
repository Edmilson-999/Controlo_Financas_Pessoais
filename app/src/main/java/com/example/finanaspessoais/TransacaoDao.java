package com.example.finanaspessoais;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransacaoDao {

    @Insert
    void insert(Transacao transacao);

    @Update
    void update(Transacao transacao);

    @Delete
    void delete(Transacao transacao);

    // Query para obter todas as transações, ordenadas por data
    @Query(value = "SELECT * FROM transacoes ORDER BY data DESC")
    List<Transacao> getAllTransacoes();

    // Query para obter o total de despesas mensais (somente despesas, não receitas)
    @Query("SELECT SUM(valor) FROM transacoes WHERE valor < 0")
    double getTotalDespesasMensais();
}
