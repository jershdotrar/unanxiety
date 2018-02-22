package sh.jer.unanxiety;

import android.provider.BaseColumns;

public final class ContactTableContract {

    private ContactTableContract() {}

    public static class ContactTable implements BaseColumns {
        public static final String TABLE_NAME = "contact_entries";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_NUMBER = "number";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String _ID = "ContactEntry_ID";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " ( ContactEntry_ID INTEGER PRIMARY KEY, name TEXT, number TEXT, email TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
