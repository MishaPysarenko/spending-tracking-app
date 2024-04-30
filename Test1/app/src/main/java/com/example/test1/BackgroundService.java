package com.example.test1;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

// Пример службы для работы в фоновом режиме
public class BackgroundService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Выполните вашу логику здесь, например, отправка уведомлений
        sendNotification();

        // Возвращаем значение, указывающее на то, что служба должна быть повторно запущена, если она была прервана системой
        return START_STICKY;
    }

    private void sendNotification() {
        // Создание уведомления
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Уведомление")
                .setContentText("Ваше уведомление здесь")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Отправка уведомления
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Обработка отсутствия разрешений
            return;
        }
        notificationManager.notify(1, builder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
