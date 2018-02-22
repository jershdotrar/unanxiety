package layout;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import sh.jer.unanxiety.ContactEntryDbHelper;
import sh.jer.unanxiety.Entry;
import sh.jer.unanxiety.EntryAdapter;
import sh.jer.unanxiety.JournalEntryDbHelper;
import sh.jer.unanxiety.JournalTableContract;
import sh.jer.unanxiety.R;

public class Journal extends ListFragment {

    /**
     * Creates the ListView, Adapter, ArrayList, TextViews, Button, and Boolean classes for later
     */

    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private EntryAdapter adapter;
    private ListView listView;
    private TextView mTitle;
    private TextView mBody;
    private Button mButton;
    private Boolean fieldsVisible = false;

    public Journal() {
        // Required empty public constructor
    }

    /**
     * Inflates layout as "view"
     * Sets the title of the activity
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_journal, container, false);
        getActivity().setTitle("JOURNAL");

        /**
         * Sets the values for each of the above classes, and the title of the activity
         */

        mButton = (Button) view.findViewById(R.id.button);
        mTitle = (TextView) view.findViewById(R.id.title);
        mTitle.setVisibility(View.GONE);
        mBody = (TextView) view.findViewById(R.id.body);
        mBody.setVisibility(View.GONE);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * If the EditText fields are invisible, this makes them visible and changes the button text
                 */

                if (fieldsVisible == false) {
                    mTitle.setVisibility(View.VISIBLE);
                    mBody.setVisibility(View.VISIBLE);
                    fieldsVisible = true;
                    mButton.setText("Submit");
                } else {
                    String title = String.valueOf(mTitle.getText());
                    String body = String.valueOf(mBody.getText());
                    if (!title.equalsIgnoreCase("") && !body.equalsIgnoreCase("")) {
                        //Retrieving this just in case, might not need if we're re-query'ing every time we update
                        int newId = insertEntry(title, body);
                        retrieveEntries();
                        adapter.notifyDataSetChanged();
                        fieldsVisible = false;
                        mTitle.setText("");
                        mBody.setText("");
                        mTitle.setVisibility(View.GONE);
                        mBody.setVisibility(View.GONE);
                    } else
                        Toast.makeText(getContext(), "You must enter a title and a body.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * Sets the ListView's Adapter to display the user's input from the EditText fields
         */

        adapter = new EntryAdapter(getActivity(), R.layout.entrylist_view, entries);
        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        /**
         * Returns "view" to display the layout
         */

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        retrieveEntries();

        super.onCreate(savedInstanceState);

    }

    public void retrieveEntries() {
        JournalEntryDbHelper mDbHelper = new JournalEntryDbHelper(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        String columnID = JournalTableContract.JournalTable._ID;
        String columnTitle = JournalTableContract.JournalTable.COLUMN_NAME_TITLE;
        String columnEntry = JournalTableContract.JournalTable.COLUMN_NAME_ENTRY;
        String tableName = JournalTableContract.JournalTable.TABLE_NAME;

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                columnID,
                columnTitle,
                columnEntry
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = JournalTableContract.JournalTable._ID + " DESC";

        Cursor cursor = db.query(
                tableName,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<Integer> itemIds = new ArrayList<Integer>();
        ArrayList<String> itemTitles = new ArrayList<String>();
        ArrayList<String> itemEntries = new ArrayList<String>();
        while(cursor.moveToNext()) {
            int itemId = (int) cursor.getLong(cursor.getColumnIndexOrThrow(columnID));
            String itemTitle = cursor.getString(cursor.getColumnIndexOrThrow(columnTitle));
            String itemEntry = cursor.getString(cursor.getColumnIndexOrThrow(columnEntry));
            itemIds.add(itemId);
            itemTitles.add(itemTitle);
            itemEntries.add(itemEntry);
        }
        cursor.close();
        entries.clear();

        for (int index = 0; index < itemIds.size(); index++) {
            Entry entry = new Entry(itemTitles.get(index), itemEntries.get(index), itemIds.get(index));
            entries.add(entry);
        }
    }

    public int insertEntry(String title, String entry) {
        JournalEntryDbHelper mDbHelper = new JournalEntryDbHelper(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        String columnID = JournalTableContract.JournalTable._ID;
        String columnTitle = JournalTableContract.JournalTable.COLUMN_NAME_TITLE;
        String columnEntry = JournalTableContract.JournalTable.COLUMN_NAME_ENTRY;
        String tableName = JournalTableContract.JournalTable.TABLE_NAME;

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(columnTitle, title);
        values.put(columnEntry, entry);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(tableName, null, values);


        return (int) newRowId;
    }
}
