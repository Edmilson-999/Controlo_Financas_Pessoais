package com.example.finanaspessoais;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;

@Entity(tableName = "user_settings")
public class UserSettings {

    @Dao
    public interface UserSettingsDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(UserSettings settings);

        @Query("SELECT * FROM user_settings LIMIT 1")
        UserSettings getUserSettings();
    }


    @PrimaryKey(autoGenerate = true)
    private int id;
    private double metaGastoMensal;

    public UserSettings(double metaGastoMensal) {
        this.metaGastoMensal = metaGastoMensal;
    }

    public double getMetaGastoMensal() {
        return metaGastoMensal;
    }

    public void setMetaGastoMensal(double metaGastoMensal) {
        this.metaGastoMensal = metaGastoMensal;
    }

    // Getter e Setter para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
