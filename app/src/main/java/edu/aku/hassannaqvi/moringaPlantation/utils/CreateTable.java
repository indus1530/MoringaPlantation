package edu.aku.hassannaqvi.moringaPlantation.utils;

import edu.aku.hassannaqvi.moringaPlantation.contracts.BLRandomContract.BLRandomTable;
import edu.aku.hassannaqvi.moringaPlantation.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.moringaPlantation.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.moringaPlantation.contracts.VersionAppContract.VersionAppTable;

public final class CreateTable {

    public static final String DATABASE_NAME = "moringa_plantation.db";
    public static final String DB_NAME = "moringa_plantation_copy.db";
    public static final String PROJECT_NAME = "moringa_plantation";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_DEVICEID + " TEXT,"
            + FormsTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsTable.COLUMN_SYSDATE + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT,"
            + FormsTable.COLUMN_USERNAME + " TEXT,"
            + FormsTable.COLUMN_MF101 + " TEXT,"
            + FormsTable.COLUMN_MF102 + " TEXT,"
            + FormsTable.COLUMN_MF103 + " TEXT,"
            + FormsTable.COLUMN_MF104 + " TEXT,"
            + FormsTable.COLUMN_MF105 + " TEXT,"
            + FormsTable.COLUMN_MF106 + " TEXT,"
            + FormsTable.COLUMN_MF107 + " TEXT,"
            + FormsTable.COLUMN_MF108 + " TEXT,"
            + FormsTable.COLUMN_GPSLAT + " TEXT,"
            + FormsTable.COLUMN_GPSLNG + " TEXT,"
            + FormsTable.COLUMN_GPSDATE + " TEXT,"
            + FormsTable.COLUMN_GPSACC + " TEXT,"
            + FormsTable.COLUMN_APPVERSION + " TEXT,"
            + FormsTable.COLUMN_SB + " TEXT,"
            + FormsTable.COLUMN_ENDINGDATETIME + " TEXT,"
            + FormsTable.COLUMN_ISTATUS + " TEXT,"
            + FormsTable.COLUMN_ISTATUS96x + " TEXT,"
            + FormsTable.COLUMN_SYNCED + " TEXT,"
            + FormsTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.COLUMN_FULL_NAME + " TEXT"
            + " );";

    public static final String SQL_CREATE_VERSIONAPP = "CREATE TABLE " + VersionAppTable.TABLE_NAME + " (" +
            VersionAppTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VersionAppTable.COLUMN_VERSION_CODE + " TEXT, " +
            VersionAppTable.COLUMN_VERSION_NAME + " TEXT, " +
            VersionAppTable.COLUMN_PATH_NAME + " TEXT " +
            ");";

    public static final String SQL_CREATE_BL_RANDOM = "CREATE TABLE " + BLRandomTable.TABLE_NAME + "("
            + BLRandomTable.COLUMN_ID + " TEXT,"
            + BLRandomTable.COLUMN_P_CODE + " TEXT,"
            + BLRandomTable.COLUMN_EB_CODE + " TEXT,"
            + BLRandomTable.COLUMN_LUID + " TEXT,"
            + BLRandomTable.COLUMN_HH + " TEXT,"
            + BLRandomTable.COLUMN_STRUCTURE_NO + " TEXT,"
            + BLRandomTable.COLUMN_FAMILY_EXT_CODE + " TEXT,"
            + BLRandomTable.COLUMN_HH_HEAD + " TEXT,"
            + BLRandomTable.COLUMN_CONTACT + " TEXT,"
            + BLRandomTable.COLUMN_HH_SELECTED_STRUCT + " TEXT,"
            + BLRandomTable.COLUMN_RANDOMDT + " TEXT,"
            + BLRandomTable.COLUMN_SNO_HH + " TEXT );";


/*    public static final String SQL_ALTER_FORMS = "ALTER TABLE " +
            FormsTable.TABLE_NAME + " ADD COLUMN " +
            FormsTable.COLUMN_SYSDATE + " TEXT";
    public static final String SQL_ALTER_CHILD_TABLE = "ALTER TABLE " +
            ChildTable.TABLE_NAME + " ADD COLUMN " +
            ChildTable.COLUMN_SYSDATE + " TEXT";*/
}
