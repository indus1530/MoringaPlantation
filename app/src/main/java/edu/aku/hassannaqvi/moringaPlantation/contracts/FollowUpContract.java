package edu.aku.hassannaqvi.moringaPlantation.contracts;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class FollowUpContract {

    private static final String TAG = "FollowUp_CONTRACT";

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.moringaPlantation";


    public static abstract class TableFollowUp implements BaseColumns {

        public static final String TABLE_NAME = "followup";

        public static final String _ID = "id";
        public static final String COLUMN_MP101 = "mp101";
        public static final String COLUMN_FSYSDATE = "fsysdate";
        public static final String COLUMN_FTID = "ftid";

        public static final String SERVER_URI = "followup.php";

        public static String PATH = "followup";

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)
                .buildUpon().appendPath(PATH).build();

        public static String getMovieKeyFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildUriWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}