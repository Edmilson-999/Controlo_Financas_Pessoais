package com.example.finanaspessoais;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.finanaspessoais.R;

public class MetaNotificationWorker extends Worker {

    private static final String CHANNEL_ID = "meta_channel";

    public MetaNotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        TransacaoDao transacaoDao = db.transacaoDao();
        double totalDespesas = transacaoDao.getTotalDespesasMensais();
        UserSettings settings = db.userSettingsDao().getUserSettings();

        if (settings != null) {
            double metaGastoMensal = settings.getMetaGastoMensal();
            if (totalDespesas >= 0.8 * metaGastoMensal) {
                enviarNotificacao();
            }
        }

        return Result.success();
    }

    private void enviarNotificacao() {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Atenção! Meta de Gasto")
                .setContentText("Você atingiu 80% da sua meta de gastos! Controle-se para não ultrapassar.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Meta de Gasto";
            String description = "Notificações para metas de gasto";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
