package egr.marzejon.diego.quizappegr;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "egr.marzejon.diego.quizappegr.MESSAGE";
    EditText textmsg;
    TextView parea;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textmsg = (EditText) findViewById(R.id.editText);
        //parea =  (TextView)findViewById(R.id.textView2);
    }

    //question input screen
    public void inputScreen(View view) {
        Intent intent = new Intent(this, QuestionInput.class);
        startActivity(intent);

    }
    //test notification screen
    public void notifScreen(View view) {
        Intent intent = new Intent(this, CreateNotification.class);
        startActivity(intent);

    }







}
