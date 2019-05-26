package a16thingskidscando.a16thingsapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

    EditText recipient, sub ,msg, msg2, studentLoanTotal, studentLoanMonthly, carLoansTotal, carLoansMonthly,
                creditCardsTotal, creditCardsMonthly, sustainLifeStyle, houseTotalWorth, houseMonthlyPayment,
                numberOfYears, interestRate, currBalanceMort1, currBalanceMort2, educationCost, amountSaveEducation,
                clientLifeInsurance, clientTypeLifeInsurance, carrierLifeInsurance, spouseCarrier, spouseLifeInsurance,
                spouseTypeLifeInsurance; //TODO add all of the input field edit texts here

    Spinner typeOfLoan, childEducation; //spinners go here

    String rec, subject, spouseLifeInsuranceString, spouseTypeLifeInsuranceString, studentLoanTotalString, studentLoanMonthlyString,
            carLoansTotalString, carLoansMonthlyString, creditCardsTotalString, creditCardsMonthlyString,
            sustainLifeStyleString, houseTotalWorthString, houseMonthlyPaymentString, typeOfLoanString,
            numberOfYearsString, interestRateString, currBalanceMort1String, currBalanceMort2String,
            childEducationString, educationCostString, amountSaveEducationString, clientLifeInsuranceString,
            clientTypeLifeInsuranceString, carrierLifeInsuranceString, spouseCarrierString, textMessage, textMessage2; //TODO add all the resulting strings here (initialize)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO add all editTexts and Spinners here to initialize
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        Button sendToEmail = findViewById(R.id.submit_button);
        recipient = (EditText) findViewById(R.id.email_edit_text);
        sub = (EditText) findViewById(R.id.spouse_carrier_life);
        //TODO add edittexts here
        msg = (EditText) findViewById(R.id.spouse_life);

        msg2 = (EditText) findViewById(R.id.spouse_type_life);
        studentLoanTotal = (EditText) findViewById(R.id.student_loan_total);
        studentLoanMonthly = (EditText) findViewById(R.id.student_loan_monthly);

        carLoansTotal = (EditText) findViewById(R.id.car_loans_total);
        carLoansMonthly = (EditText) findViewById(R.id.car_loans_monthly);

        creditCardsMonthly = (EditText) findViewById(R.id.credit_cards_monthly);
        creditCardsTotal = (EditText) findViewById(R.id.credit_cards_total);

        sustainLifeStyle = (EditText) findViewById(R.id.how_much_to_sustain_lifestyle);

        houseTotalWorth = (EditText) findViewById(R.id.house_total_worth);
        houseMonthlyPayment = (EditText) findViewById(R.id.house_monthly_payment);

        numberOfYears = (EditText) findViewById(R.id.number_of_years);
        interestRate = (EditText) findViewById(R.id.interest_rate);

        typeOfLoan = (Spinner) findViewById(R.id.type_of_loan);

        currBalanceMort1 = (EditText) findViewById(R.id.current_balance_mortgage_1);
        currBalanceMort2 = (EditText) findViewById(R.id.current_balance_mortgage_2);

        childEducation = (Spinner) findViewById(R.id.child_education);

        educationCost = (EditText) findViewById(R.id.cost_of_education);

        amountSaveEducation = (EditText) findViewById(R.id.amount_saved_education);

        clientLifeInsurance = (EditText) findViewById(R.id.client_life);
        clientTypeLifeInsurance = (EditText) findViewById(R.id.type_life);

        carrierLifeInsurance = (EditText) findViewById(R.id.carrier_life);

        spouseCarrier = (EditText) findViewById(R.id.spouse_carrier_life);
        spouseLifeInsurance = (EditText) findViewById(R.id.spouse_life);
        spouseTypeLifeInsurance = (EditText) findViewById(R.id.spouse_type_life);

        sendToEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        rec = recipient.getText().toString();
        subject = sub.getText().toString();
        textMessage = msg.getText().toString();
        //all input fields should have individual variables
        //TODO make all input field variables verbose, also add strings to show in email here
        textMessage2 = msg2.getText().toString();

        studentLoanTotalString = studentLoanTotal.getText().toString();
        studentLoanMonthlyString = studentLoanMonthly.getText().toString();

        carLoansTotalString = carLoansTotal.getText().toString();
        carLoansMonthlyString = carLoansMonthly.getText().toString();

        creditCardsMonthlyString = creditCardsMonthly.getText().toString();
        creditCardsTotalString = creditCardsTotal.getText().toString();

        sustainLifeStyleString = sustainLifeStyle.getText().toString();

        houseTotalWorthString = houseTotalWorth.getText().toString();
        houseMonthlyPaymentString = houseMonthlyPayment.getText().toString();

        typeOfLoanString = typeOfLoan.getSelectedItem().toString();

        numberOfYearsString = numberOfYears.getText().toString();
        interestRateString = interestRate.getText().toString();

        currBalanceMort1String = currBalanceMort1.getText().toString();
        currBalanceMort2String = currBalanceMort2.getText().toString();

        childEducationString = childEducation.getSelectedItem().toString();

        educationCostString = educationCost.getText().toString();

        amountSaveEducationString = amountSaveEducation.getText().toString();

        clientTypeLifeInsuranceString = clientTypeLifeInsurance.getText().toString();
        clientLifeInsuranceString = clientLifeInsurance.getText().toString();

        carrierLifeInsuranceString = carrierLifeInsurance.getText().toString();
        spouseCarrierString = spouseCarrier.getText().toString();


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
                //message.setContent is what goes in the body of the email <br> is html to set a newline since it's being sent to an email address
                message.setContent( "Student Loan Total: "                                      + studentLoanTotalString        + "<br>"
                                    + "Student Loan Monthly: "                                  + studentLoanMonthlyString      + "<br>"
                                    + "Car Loans Total: "                                       + carLoansTotalString           + "<br>"
                                    + "Car Loans Monthly: "                                     + carLoansMonthlyString         + "<br>"
                                    + "Credit Cards Total: "                                    + creditCardsTotalString        + "<br>"
                                    + "Credit Cards Monthly: "                                  + creditCardsMonthlyString      + "<br>"
                                    + "Total Cost to Sustain Lifestyle: "                       + sustainLifeStyleString        + "<br>"
                                    + "House Total : "                                          + houseTotalWorthString         + "<br>"
                                    + "House Monthly Payment: "                                 + houseMonthlyPaymentString     + "<br>"
                                    + "The type of loan is: "                                   + typeOfLoanString              + "<br>" // (fixed/adjustable)
                                    + "Number of years to pay off : "                           + numberOfYearsString           + "<br>"
                                    + "The interest rate is: "                                  + interestRateString            + "<br>"
                                    + "Current Balance of First Mortgage: "                     + currBalanceMort1String        + "<br>"
                                    + "Current Balance of Second Mortgage: "                    + currBalanceMort2String        + "<br>"
                                    + "Would you like to provide for your child's education?: " + childEducationString          + "<br>" // (yes/no/part)
                                    + "Approximate cost for education: "                        + educationCostString           + "<br>"
                                    + "Amount of money save for Education: "                    + amountSaveEducationString     + "<br>"
                                    + "Client Life Insurance: "                                 + clientLifeInsuranceString     + "<br>"
                                    + "Client Type of Life Insurance: "                         + clientTypeLifeInsuranceString + "<br>"
                                    + "Client Carrier Life Insurance: "                         + carrierLifeInsuranceString    + "<br>"
                                    + "Spouse Life Insurance: "                                 + textMessage                   + "<br>"
                                    + "Spouse Type of Life Insurance: "                         + textMessage2                  + "<br>"
                                    + "Spouse Carrier Life Insurance: "                         + spouseCarrierString, "text/html; charset=utf-8");

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