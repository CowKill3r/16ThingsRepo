package a16thingskidscando.a16thingsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button submitButton;
    EditText userEmail;
    Boolean everythingGood = true;

    //initialize the variables and give a value
    String email_address = "stoch.octavian@gmail.com";//user email address
    String email_subject = "test subject";//email subject goes here
    String email_body = "this is a test body for the email app";//email body goes here
    String email_chooserTitle = "super tester";//chooser title goes here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO add all editTexts and Spinners here to initialize
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
        userEmail = (EditText) findViewById(R.id.email_edit_text);
    }

    //TODO email results to email that the user has put in
    @Override
    public void onClick (View v){
        //TODO Check to see if the user has or hasn't filled out all forms
        if (everythingGood){
            Toast.makeText(getApplicationContext(), "We are redirecting you to your email application.", Toast.LENGTH_LONG).show();
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[] { email_address });
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My subject");

            startActivity(Intent.createChooser(emailIntent, "Email your information to the following..."));
        }
        else {
            Toast.makeText(getApplicationContext(), "You forgot to fill in some data. Please fill in all necessary forms.", Toast.LENGTH_LONG).show();
        }
    }


}