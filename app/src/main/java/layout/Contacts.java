package layout;


import android.app.DialogFragment;
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

import sh.jer.unanxiety.ContactEntry;
import sh.jer.unanxiety.ContactEntryAdapter;
import sh.jer.unanxiety.ContactEntryDbHelper;
import sh.jer.unanxiety.ContactTableContract;
import sh.jer.unanxiety.R;

import static android.R.id.list;

public class Contacts extends ListFragment {

    /**
     * Creates the ListView, Adapter, ArrayList, TextViews, Button, and Boolean classes for later
     */

    private ArrayList<ContactEntry> entries = new ArrayList<ContactEntry>();
    private ContactEntryAdapter adapter;
    private ListView listView;
    private TextView mName;
    private TextView mNumber;
    private TextView mEmail;
    private Button mButton;
    private Boolean fieldsVisible = false;
    private ContactEntryDbHelper mDbHelper;

    public Contacts() {
        // Required empty public constructor
    }

    /**
     * Inflates layout as "view"
     * Sets the title of the activity
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        getActivity().setTitle("CONTACTS");

        /**
         * Sets the values for each of the above classes, and the title of the activity
         */

        mButton = (Button) view.findViewById(R.id.button);
        mName = (TextView) view.findViewById(R.id.name);
        mName.setVisibility(View.GONE);
        mNumber = (TextView) view.findViewById(R.id.number);
        mNumber.setVisibility(View.GONE);
        mEmail = (TextView) view.findViewById(R.id.email);
        mEmail.setVisibility(View.GONE);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * If the EditText fields are invisible, this makes them visible and changes the button text
                 */

                if (fieldsVisible == false) {
                    mName.setVisibility(View.VISIBLE);
                    mNumber.setVisibility(View.VISIBLE);
                    mEmail.setVisibility(View.VISIBLE);
                    fieldsVisible = true;
                    mButton.setText("Submit");
                } else {
                    String name = String.valueOf(mName.getText());
                    String number = String.valueOf(mNumber.getText());
                    String email = String.valueOf(mEmail.getText());
                    if (!name.equalsIgnoreCase("") && !number.equalsIgnoreCase("") && !email.equalsIgnoreCase("")) {
                        //Retrieving this just in case, might not need if we're re-query'ing every time we update
                        int newId = insertEntry(name, number, email);
                        retrieveEntries();
                        adapter.notifyDataSetChanged();
                        fieldsVisible = false;
                        mName.setText("");
                        mNumber.setText("");
                        mEmail.setText("");
                        mName.setVisibility(View.GONE);
                        mNumber.setVisibility(View.GONE);
                        mEmail.setVisibility(View.GONE);

                    } else
                        Toast.makeText(getContext(), "You must enter a name, number, and email.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * Sets the ListView's Adapter to display the user's input from the EditText fields
         */

        adapter = new ContactEntryAdapter(getActivity(), R.layout.entrylist_contacts_view, entries);
        listView = (ListView) view.findViewById(list);
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
        mDbHelper = new ContactEntryDbHelper(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        String columnID = ContactTableContract.ContactTable._ID;
        String columnName = ContactTableContract.ContactTable.COLUMN_NAME_NAME;
        String columnNumber = ContactTableContract.ContactTable.COLUMN_NAME_NUMBER;
        String columnEmail = ContactTableContract.ContactTable.COLUMN_NAME_EMAIL;
        String tableName = ContactTableContract.ContactTable.TABLE_NAME;

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                columnID,
                columnName,
                columnNumber,
                columnEmail
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = ContactTableContract.ContactTable._ID + " DESC";

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
        ArrayList<String> itemNames = new ArrayList<String>();
        ArrayList<String> itemNumbers = new ArrayList<String>();
        ArrayList<String> itemEmails = new ArrayList<String>();
        while(cursor.moveToNext()) {
            int itemId = (int) cursor.getLong(cursor.getColumnIndexOrThrow(columnID));
            String itemName = cursor.getString(cursor.getColumnIndexOrThrow(columnName));
            String itemNumber = cursor.getString(cursor.getColumnIndexOrThrow(columnNumber));
            String itemEmail = cursor.getString(cursor.getColumnIndexOrThrow(columnEmail));
            itemIds.add(itemId);
            itemNames.add(itemName);
            itemNumbers.add(itemNumber);
            itemEmails.add(itemEmail);

        }
        cursor.close();
        entries.clear();

        for (int index = 0; index < itemIds.size(); index++) {
            ContactEntry entry = new ContactEntry(itemNames.get(index), itemNumbers.get(index), itemEmails.get(index), itemIds.get(index));
            entries.add(entry);
        }
    }

    public int insertEntry(String name, String number, String email) {
        ContactEntryDbHelper mDbHelper = new ContactEntryDbHelper(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        String columnID = ContactTableContract.ContactTable._ID;
        String columnName = ContactTableContract.ContactTable.COLUMN_NAME_NAME;
        String columnNumber = ContactTableContract.ContactTable.COLUMN_NAME_NUMBER;
        String columnEmail = ContactTableContract.ContactTable.COLUMN_NAME_EMAIL;
        String tableName = ContactTableContract.ContactTable.TABLE_NAME;

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(columnName, name);
        values.put(columnNumber, number);
        values.put(columnEmail, email);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(tableName, null, values);


        return (int) newRowId;
    }
}
