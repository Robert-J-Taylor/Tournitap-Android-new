package roberttaylor.tournitap_android.Util;

/**
 * Created by Robert Taylor on 10/22/2017.
 */

public class Constants {
    //Database version
    public static final int DATABASE_VERSION =3;
    public static final String DATABASE_NAME ="tournmentAndroidDB";
    public static final String TABLE_NAME ="tournamentTBL";

    //Tournament table columns names
    public static final String KEY_ID ="_id";
    public static final String KEY_NAME ="name";
    public static final String KEY_GAMETYPE="gameType";
    public static final String KEY_NUMPARTICIPANTS="numParticipants";
    public static final String KEY_FORMATNAME="formatName";
    public static final String KEY_STAGETYPE="stageType";
    public static final String KEY_SKILLLEVEL="skillLevel";
    public static final String KEY_DESCRIPTION="description";
    public static final String KEY_TOTALROUNDS="totalRounds";
}
