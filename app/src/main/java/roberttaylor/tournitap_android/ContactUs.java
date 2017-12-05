package roberttaylor.tournitap_android;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ContactUs extends AppCompatActivity {
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        final EditText name = (EditText) findViewById(R.id.editText1);
        final EditText addy = (EditText) findViewById(R.id.editText2);
        final EditText cell = (EditText) findViewById(R.id.editText3);
        final EditText questions = (EditText) findViewById(R.id.editText4);

        Button email = (Button) findViewById(R.id.button30);
        email.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO Auto-generated method stub
                Intent email = new Intent(android.content.Intent.ACTION_SEND);

            /* Fill it with Data */
                email.setType("plain/text");
                email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"rileyespersen@gmail.com"});
                email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Lads to Leaders/Leaderettes Questions and/or Comments");
                email.putExtra(android.content.Intent.EXTRA_TEXT,
                        "name:" + name.getText().toString() + '\n' + "address:" + addy.getText().toString() + '\n' + "phone:" + cell.getText().toString() + '\n' + '\n' + questions.getText().toString() + '\n' + '\n' + "Sent from the Lads to Leaders/Leaderettes Android App.");

            /* Send it off to the Activity-Chooser */
                startActivity(Intent.createChooser(email, "Send mail..."));
            }
        } );
    } ;
}


