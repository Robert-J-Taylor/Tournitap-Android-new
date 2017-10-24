package roberttaylor.tournitap_android.Data;

//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import roberttaylor.tournitap_android.Model.Tournament;
//import roberttaylor.tournitap_android.Util.Constants;

/**
 * Created by Robert Taylor on 10/22/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;
import roberttaylor.tournitap_android.Model.Tournament;
import roberttaylor.tournitap_android.Util.Constants;
import java.util.ArrayList;
import java.util.List;


    public class DatabaseHandler extends SQLiteOpenHelper {

        private Context ctx;
        public DatabaseHandler(Context context) {
            super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
            this.ctx = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_GROCERY_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                    + Constants.KEY_ID + " INTEGER PRIMARY KEY," + Constants.KEY_NAME + " TEXT,"
                    + Constants.KEY_GAMETYPE + " TEXT,"
                    + Constants.KEY_FORMATNAME + " TEXT,"
                    + Constants.KEY_NUMPARTICIPANTS + " TEXT,"
                    + Constants.KEY_STAGETYPE + " TEXT,"
                    + Constants.KEY_SKILLLEVEL + " TEXT,"
                    + Constants.KEY_TOTALROUNDS + " TEXT,"
                    + Constants.KEY_DESCRIPTION + " TEXT);";

            db.execSQL(CREATE_GROCERY_TABLE);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

            onCreate(db);

        }

        /**
         *  CRUD OPERATIONS: Create, Read, Update, Delete Methods
         */

        //Add Tournament
        public void addTournament(Tournament tournament) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Constants.KEY_NAME, tournament.getName());
            values.put(Constants.KEY_GAMETYPE, tournament.getGameType());
            values.put(Constants.KEY_FORMATNAME, tournament.getFormatName());
            values.put(Constants.KEY_NUMPARTICIPANTS, tournament.getNumParticipants());
            values.put(Constants.KEY_STAGETYPE, tournament.getStageType());
            values.put(Constants.KEY_SKILLLEVEL, tournament.getSkillLevel());
            values.put(Constants.KEY_TOTALROUNDS, tournament.getTotalRounds());
            values.put(Constants.KEY_DESCRIPTION, tournament.getDescription());

            //Insert the row
            db.insert(Constants.TABLE_NAME, null, values);

            Log.d("Saved!!", "Saved to DB");

        }


        //Get a Tournament
        public Tournament getTournament(int id) {
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {Constants.KEY_ID,
                            Constants.KEY_NAME, Constants.KEY_GAMETYPE, Constants.KEY_FORMATNAME, Constants.KEY_NUMPARTICIPANTS, Constants.KEY_STAGETYPE,
                    Constants.KEY_SKILLLEVEL, Constants.KEY_TOTALROUNDS, Constants.KEY_DESCRIPTION},
                    Constants.KEY_ID + "=?",
                    new String[] {String.valueOf(id)}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();


            Tournament tournament = new Tournament();
            tournament.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            tournament.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
            tournament.setGameType(cursor.getString(cursor.getColumnIndex(Constants.KEY_GAMETYPE)));
            tournament.setFormatName(cursor.getString(cursor.getColumnIndex(Constants.KEY_FORMATNAME)));
            tournament.setNumParticipants(cursor.getString(cursor.getColumnIndex(Constants.KEY_NUMPARTICIPANTS)));
            tournament.setStageType(cursor.getString(cursor.getColumnIndex(Constants.KEY_STAGETYPE)));
            tournament.setSkillLevel(cursor.getString(cursor.getColumnIndex(Constants.KEY_SKILLLEVEL)));
            tournament.setTotalRounds(cursor.getString(cursor.getColumnIndex(Constants.KEY_TOTALROUNDS)));
            tournament.setDescription(cursor.getString(cursor.getColumnIndex(Constants.KEY_DESCRIPTION)));



            return tournament;
        }


        //Get all Tournaments
        public List<Tournament> getAllTournament() {
            SQLiteDatabase db = this.getReadableDatabase();

            List<Tournament> tournamentList = new ArrayList<>();

            Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {
                    Constants.KEY_ID, Constants.KEY_NAME, Constants.KEY_GAMETYPE,
                    Constants.KEY_FORMATNAME, Constants.KEY_NUMPARTICIPANTS, Constants.KEY_STAGETYPE,
                    Constants.KEY_SKILLLEVEL, Constants.KEY_TOTALROUNDS, Constants.KEY_DESCRIPTION}, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Tournament tournament = new Tournament();
                    tournament.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                    tournament.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
                    tournament.setGameType(cursor.getString(cursor.getColumnIndex(Constants.KEY_GAMETYPE)));
                    tournament.setFormatName(cursor.getString(cursor.getColumnIndex(Constants.KEY_FORMATNAME)));
                    tournament.setNumParticipants(cursor.getString(cursor.getColumnIndex(Constants.KEY_NUMPARTICIPANTS)));
                    tournament.setStageType(cursor.getString(cursor.getColumnIndex(Constants.KEY_STAGETYPE)));
                    tournament.setSkillLevel(cursor.getString(cursor.getColumnIndex(Constants.KEY_SKILLLEVEL)));
                    tournament.setTotalRounds(cursor.getString(cursor.getColumnIndex(Constants.KEY_TOTALROUNDS)));
                    tournament.setDescription(cursor.getString(cursor.getColumnIndex(Constants.KEY_DESCRIPTION)));


                    // Add to the tournamentList
                    tournamentList.add(tournament);

                }while (cursor.moveToNext());
            }

            return tournamentList;
        }


        //Updated Grocery
        public int updateGrocery(Tournament tournament) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Constants.KEY_NAME, tournament.getName());
            values.put(Constants.KEY_GAMETYPE, tournament.getGameType());
            values.put(Constants.KEY_FORMATNAME, tournament.getFormatName());
            values.put(Constants.KEY_NUMPARTICIPANTS, tournament.getNumParticipants());
            values.put(Constants.KEY_STAGETYPE, tournament.getStageType());
            values.put(Constants.KEY_SKILLLEVEL, tournament.getSkillLevel());
            values.put(Constants.KEY_TOTALROUNDS, tournament.getTotalRounds());
            values.put(Constants.KEY_DESCRIPTION, tournament.getDescription());




            //update row
            return db.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?", new String[] { String.valueOf(tournament.getId())} );
        }


        //Delete Tournament
        public void deleteTournament(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ?",
                    new String[] {String.valueOf(id)});

            db.close();

        }


        //Get count
        public int getTournamentCount() {
            String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(countQuery, null);

            return cursor.getCount();
        }
    }


