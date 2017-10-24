package roberttaylor.tournitap_android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import roberttaylor.tournitap_android.Data.DatabaseHandler;
import roberttaylor.tournitap_android.Model.Tournament;
import roberttaylor.tournitap_android.UI.RecyclerViewAdapter;

import static roberttaylor.tournitap_android.R.string.displayTournamentCount;

public class FindTournamentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Tournament> tournamentList;
    private List<Tournament> listItems;
    private DatabaseHandler db;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText tournamentName;
    private EditText gameType;
    private EditText formatName;
    private EditText numParticipants;
    private EditText stageType;
    private EditText skillLevel;
    private EditText totalRounds;
    private EditText description;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_tournament);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                createPopDialog();
            }
        });
        db = new DatabaseHandler(this);

        TextView count = (TextView) findViewById(R.id.tournamentDisplayCount);
        count.setText("Displaying " + String.valueOf(db.getTournamentCount()) + " Tournaments");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tournamentList = new ArrayList<>();
        listItems = new ArrayList<>();

        // Get items from database
        tournamentList = db.getAllTournament();

        for (Tournament c : tournamentList) {
            Tournament tournament = new Tournament();
            tournament.setName(c.getName());
            tournament.setGameType("GameType: " + c.getGameType());
            tournament.setId(c.getId());
            tournament.setFormatName("Format: " + c.getFormatName());
            tournament.setNumParticipants("# Of Participants: " + c.getNumParticipants());
            tournament.setStageType("StageType:" + c.getStageType());
            tournament.setSkillLevel("SkillLevel:" + c.getSkillLevel());
            tournament.setTotalRounds("Total Rounds:" + c.getTotalRounds());
            tournament.setDescription(c.getDescription());

            listItems.add(tournament);

        }

        recyclerViewAdapter = new RecyclerViewAdapter(this, listItems);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void createPopDialog() {

        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        tournamentName = (EditText) view.findViewById(R.id.tournamentName);
        gameType = (EditText) view.findViewById(R.id.gameType);
        formatName = (EditText) view.findViewById(R.id.formatName);
        numParticipants = (EditText) view.findViewById(R.id.numParticipants);
        stageType = (EditText) view.findViewById(R.id.stageType);
        skillLevel =(EditText) view.findViewById(R.id.skillLevel);
        totalRounds =(EditText) view.findViewById(R.id.totalRounds);
        description=(EditText) view.findViewById(R.id.description);
        saveButton = (Button) view.findViewById(R.id.saveButton);


        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTournamentToDB(v);
            }
        });



    }

    private void saveTournamentToDB(View v) {

        Tournament tournament = new Tournament();

        String newTournament = tournamentName.getText().toString();
        String newTournamentGameType = gameType.getText().toString();
        String newTournamentFormatName = formatName.getText().toString();
        String newTournamentNumParticipants = numParticipants.getText().toString();
        String newStageType = stageType.getText().toString();
        String newSkillLevel = skillLevel.getText().toString();
        String newTotalRounds = totalRounds.getText().toString();
        String newDescription = description.getText().toString();

        tournament.setName(newTournament);
        tournament.setGameType(newTournamentGameType);
        tournament.setFormatName(newTournamentFormatName);
        tournament.setNumParticipants(newTournamentNumParticipants);
        tournament.setStageType(newStageType);
        tournament.setSkillLevel(newSkillLevel);
        tournament.setTotalRounds(newTotalRounds);
        tournament.setDescription(newDescription);

        //Save to DB
        db.addTournament(tournament);

        Snackbar.make(v, "Item Saved!", Snackbar.LENGTH_LONG).show();

        // Log.d("Item Added ID:", String.valueOf(db.getTournamentCount()));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                //start a new activity
                startActivity(new Intent(FindTournamentActivity.this, FindTournamentActivity.class));
            }
        }, 1200); //  1 second.

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.find_tournament, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sign_in) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
