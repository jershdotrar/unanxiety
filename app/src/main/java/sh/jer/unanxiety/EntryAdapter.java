package sh.jer.unanxiety;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class EntryAdapter extends ArrayAdapter<Entry> {

    private ArrayList<Entry> entries;
    private ListView listView1;
    private JournalEntryDbHelper mDbHelper;
    private Button deleteButton;

    /**
     *  Override the constructor for ArrayAdapter
     *  The only variable we care about now ArrayList<Contact> objects
     *  it is the list of the objects we want to display
     *
     * @param context
     * @param resource
     * @param entries
     */
    public EntryAdapter (Context context, int resource, ArrayList<Entry> entries) {
        super(context, resource, entries);
        this.entries = entries;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       // Assign the view we're converting to a local variable
       View listItemView = convertView;

        /* Check if the view's null.
           If so, "inflate" (show) the view
         */
        if(listItemView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listItemView = inflater.inflate(R.layout.entrylist_view, null);
        }

        Entry currentEntry = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.journal_entry_title);
        titleTextView.setText(currentEntry.getEntryTitle());

        TextView bodyTextView = (TextView) listItemView.findViewById(R.id.journal_entry_body);
        bodyTextView.setText(currentEntry.getEntryBody());

        Button deleteBtn = (Button) listItemView.findViewById(R.id.journal_delete_button);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the entry from the arraylist
                Entry entryToDelete = entries.get(position);

                // Instantiate the database helper
                JournalEntryDbHelper mDbHelper = new JournalEntryDbHelper(getContext());
                SQLiteDatabase db = mDbHelper.getReadableDatabase();

                // Define 'where' part of query.
                String selection = JournalTableContract.JournalTable._ID + " LIKE ?";
                // Specify arguments in placeholder order.
                String[] selectionArgs = { String.valueOf(entryToDelete
                        .getEntryID()) };
                // Issue SQL statement.
                db.delete(JournalTableContract.JournalTable.TABLE_NAME, selection, selectionArgs);

                // Remove the object from the entries arraylist and refresh the adapter
                entries.remove(entryToDelete);
                notifyDataSetChanged();
            }
        });

        return listItemView;
    }
}
