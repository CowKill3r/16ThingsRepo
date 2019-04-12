package a16thingskidscando.a16thingsapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText recipient, sub ,msg;
    String rec, subject, textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO add all editTexts and Spinners here to initialize
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        Button sendToEmail = findViewById(R.id.submit_button);
        recipient = (EditText) findViewById(R.id.email_edit_text);
        sub = (EditText) findViewById(R.id.spouse_carrier_life);
        msg = (EditText) findViewById(R.id.spouse_life);

        sendToEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        rec = recipient.getText().toString();
        subject = sub.getText().toString();
        textMessage = msg.getText().toString();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("testeremail3345@gmail.com","increaseDoors332Woods");
            }
        });

        pdialog = ProgressDialog.show(context,"", "Sending Mail...", true);

        RetrieveFeedTask task = new RetrieveFeedTask();
        task.execute();
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testeremail3345@gmail.com"));
                message.setRecipient(Message.RecipientType.TO, InternetAddress.parse(rec)[0]);
                message.setContent(textMessage, "text/html; charset=utf-8");

                Transport.send(message);
            } catch(MessagingException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            recipient.setText("");
            msg.setText("");
            sub.setText("");
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }
}