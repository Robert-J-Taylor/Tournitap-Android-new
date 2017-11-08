package roberttaylor.tournitap_android.Util;

/**
 * Created by Robert Taylor on 10/22/2017.
 */

public class Constants {
    //Database version
    public static final int DATABASE_VERSION =10;
    public static final String DATABASE_NAME ="tournmentAndroidDB";
    public static final String TABLE_NAME ="tournamentTBL";
    public static final String TABLE_NAME_USER="userTBL";

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
    //User table column names
    public static final String KEY_USER_ID="_id";
    public static final String KEY_FIRSTNAME="firstName";
    public static final String KEY_LASTNAME="lastName";
    public static final String KEY_MIDDLEINITIAL="middleInitial";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_GENDER="gender";
    public static final String KEY_EMAIL="email";
    public static final String KEY_USERNAME="userName";
}
