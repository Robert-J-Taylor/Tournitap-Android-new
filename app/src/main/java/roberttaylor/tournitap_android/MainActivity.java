package roberttaylor.tournitap_android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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

import roberttaylor.tournitap_android.Data.DatabaseHandler;
import roberttaylor.tournitap_android.Model.Tournament;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText tournamentName;
    private EditText gameType;
    private EditText formatName;
    private EditText numParticipants;
    private Button saveButton;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.tournitap_logo_web);
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
        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.register){
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.findTournament) {
            Intent intent = new Intent(MainActivity.this, FindTournamentActivity.class);
            startActivity(intent);
        } else if (id == R.id.createTournament) {
            createPopupDialog();
        } else if (id == R.id.searchFriends) {

        } else if (id == R.id.contactUs) {

        } else if (id == R.id.aboutUs) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void createPopupDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        tournamentName = (EditText) view.findViewById(R.id.tournamentName);
        gameType = (EditText) view.findViewById(R.id.gameType);
        formatName =(EditText) view.findViewById(R.id.formatName);
        numParticipants =(EditText) view.findViewById(R.id.numParticipants);
        saveButton = (Button) view.findViewById(R.id.saveButton);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Todo: Save to db
                //Todo: Go to next screen

                if (!tournamentName.getText().toString().isEmpty()
                        && !gameType.getText().toString().isEmpty()) {
                    saveGroceryToDB(v);
                }

            }
        });
    }
    private void saveGroceryToDB(View v) {

        Tournament tournament = new Tournament();

        String newTournament = tournamentName.getText().toString();
        String newTournamentGameType = gameType.getText().toString();
        String newTournamentFormatName = formatName.getText().toString();
        String newTournamentNumParticipants = numParticipants.getText().toString();

        tournament.setName(newTournament);
        tournament.setGameType(newTournamentGameType);
        tournament.setFormatName(newTournamentFormatName);
        tournament.setNumParticipants(newTournamentNumParticipants);

        //Save to DB
        db.addTournament(tournament);

        Snackbar.make(v, "Item Saved!", Snackbar.LENGTH_LONG).show();

        // Log.d("Item Added ID:", String.valueOf(db.getGroceriesCount()));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                //start a new activity
                startActivity(new Intent(MainActivity.this, FindTournamentActivity.class));
            }
        }, 1200); //  1 second.

    }

}
