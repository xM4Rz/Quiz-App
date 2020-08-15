package egr.marzejon.diego.quizappegr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.ThreadLocalRandom;


public class QuestionInput extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "egr.marzejon.diego.quizappegr.MESSAGE";
    EditText answer;
    EditText question;
    TextView answerArea;
    TextView questionArea;
    static int n = 1;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_question_input);
        // Get the Intent that started this activity and extract the string

        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//
//        // Capture the layout's TextView and set the string as its text
//        TextView textView = findViewById(R.id.textView2);
//        textView.setText(message);
        question = (EditText) findViewById(R.id.editText2);



    }
    @Override
    public void onStop() {
        super.onStop();
        startService(new Intent(this, NotificationService.class));
    }
    public void deleteQuestions(View v){
        for (int x=0;x<6; x++) {
            File dir = getFilesDir();
            File file = new File(dir, "question" + x + ".txt");
            file.delete();
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText2);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    // write text to file
    public void WriteBtn(View v) {

        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("question" + n + ".txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(question.getText().toString());
            outputWriter.close();
            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
            n++;
            question.getText().clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Read text from file
    public void ReadBtn(View v) {
        //reading text from file
        int randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        try {
            FileInputStream fileIn=openFileInput("question" +randomNum +".txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            String answer = "";
            String question = "";
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
            questionArea.setText(question);
            answerArea.setText(answer);
            //Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

