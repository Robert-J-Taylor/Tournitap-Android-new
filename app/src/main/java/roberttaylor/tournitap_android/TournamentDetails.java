package roberttaylor.tournitap_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TournamentDetails extends AppCompatActivity {
    private TextView tournamentName;
    private TextView gameType;
    private TextView formatName;
    private TextView numParticipants;
    private int tournamentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_details);
        tournamentName = (TextView) findViewById(R.id.tournamentNameDet);
        gameType = (TextView) findViewById(R.id.gameTypeDet);
        formatName = (TextView) findViewById(R.id.formatNameDet);
        numParticipants= (TextView) findViewById(R.id.numParticipantsDet);


        Bundle bundle = getIntent().getExtras();

        if ( bundle != null ) {
            tournamentName.setText(bundle.getString("tournamentName"));
            gameType.setText(bundle.getString("gameType"));
            formatName.setText(bundle.getString("formatName"));
            numParticipants.setText(bundle.getString("numParticipants"));
            tournamentId = bundle.getInt("id");

        }

    }
}
