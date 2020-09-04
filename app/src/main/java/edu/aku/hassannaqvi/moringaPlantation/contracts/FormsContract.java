package edu.aku.hassannaqvi.moringaPlantation.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FormsContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.moringaPlantation";

    public static abstract class FormsTable implements BaseColumns {
        public static final String TABLE_NAME = "form";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectName";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_FORMTYPE = "formtype";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_MF101 = "mf101";
        public static final String COLUMN_MF102 = "mf102";
        public static final String COLUMN_MF103 = "mf103";
        public static final String COLUMN_MF104 = "mf104";
        public static final String COLUMN_MF105 = "mf105";
        public static final String COLUMN_MF106 = "mf106";
        public static final String COLUMN_MF106x = "mf106x";
        public static final String COLUMN_MF107 = "mf107";
        public static final String COLUMN_MF107x = "mf107x";
        public static final String COLUMN_MF108 = "mf108";
        public static final String COLUMN_MF108x = "mf108x";
        public static final String COLUMN_SB = "sB";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS96x = "istatus96x";
        public static final String COLUMN_ENDINGDATETIME = "endingdatetime";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDATE = "gpsdate";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static String PATH = "forms";
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)
                .buildUpon().appendPath(PATH).build();
        public static String SERVER_URL = "sync.php";


        public static String getMovieKeyFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildUriWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
