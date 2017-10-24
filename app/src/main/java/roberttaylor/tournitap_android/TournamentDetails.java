package roberttaylor.tournitap_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import static roberttaylor.tournitap_android.R.string.skillLevel;

public class TournamentDetails extends AppCompatActivity {
    private TextView tournamentName;
    private TextView gameType;
    private TextView formatName;
    private TextView numParticipants;
    private TextView stageType;
    private TextView skillLevel;
    private TextView totalRounds;
    private TextView description;
    private int tournamentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_details);
        tournamentName = (TextView) findViewById(R.id.tournamentNameDet);
        gameType = (TextView) findViewById(R.id.gameTypeDet);
        formatName = (TextView) findViewById(R.id.formatNameDet);
        numParticipants= (TextView) findViewById(R.id.numParticipantsDet);
        stageType = (TextView) findViewById(R.id.stageTypeDet);
        skillLevel =(TextView) findViewById(R.id.skillLevelDet);
        totalRounds=(TextView) findViewById(R.id.totalRoundsDet);
        description=(TextView) findViewById(R.id.descriptionDet);


        Bundle bundle = getIntent().getExtras();

        if ( bundle != null ) {
            tournamentName.setText(bundle.getString("tournamentName"));
            gameType.setText(bundle.getString("gameType"));
            formatName.setText(bundle.getString("formatName"));
            numParticipants.setText(bundle.getString("numParticipants"));
            stageType.setText(bundle.getString("stageType"));
            tournamentId = bundle.getInt("id");
            skillLevel.setText(bundle.getString("skillLevel"));
            totalRounds.setText(bundle.getString("totalRounds"));
            description.setText(bundle.getString("description"));

        }

    }
}
