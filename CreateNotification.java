package egr.marzejon.diego.quizappegr;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class CreateNotification extends Activity {
    static final int READ_BLOCK_SIZE = 100;
    static String s="";
    static String answer = "";
    static String question = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create_notification);
        Intent intent = getIntent();
    }

    public void createNotif(View view)

    {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
        try {
            FileInputStream fileIn=openFileInput("question" +randomNum +".txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
                String[] parts = s.split(":");
                question = parts[0]; // 004
                answer = parts[1];
            }
            InputRead.close();
            //question.setText(s);
            //questionArea.setText(question);
            //answerArea.setText(answer);
            //Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);


        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("StudyPal")
                //     .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(question)
                .setContentText(answer)
                .setContentInfo("Study Time!");

        notificationManager.notify(/*notification id*/1, notificationBuilder.build());
    }

}
