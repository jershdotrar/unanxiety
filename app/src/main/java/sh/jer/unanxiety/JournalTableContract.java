package sh.jer.unanxiety;

import android.provider.BaseColumns;

public final class JournalTableContract {

    private JournalTableContract() {}

    public static class JournalTable implements BaseColumns {
        public static final String TABLE_NAME = "journal_entries";
        public static final String COLUMN_NAME_ENTRY = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String _ID = "JournalEntry_ID";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " ( JournalEntry_ID INTEGER PRIMARY KEY, entry TEXT, title TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
