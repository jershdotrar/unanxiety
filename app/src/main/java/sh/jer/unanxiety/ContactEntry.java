package sh.jer.unanxiety;

public class ContactEntry {

    /**
     * Creates the title and body Strings
     */
    private String mName;
    private String mNumber;
    private String mEmail;
    private int mEntryID;

    /**
     * Sets the values for the title and body Strings
     */
    public ContactEntry(String name, String number, String email, int id) {
        mName = name;
        mNumber = number;
        mEmail = email;
        mEntryID = id;
    }

    /**
     * Get entry title
     * Set entry title
     */
    public String getEntryName() {
        return mName;
    }
    public void setEntryTitle(String name) {
        mName = name;
    }

    /**
     * Get entry body text
     * Set entry title
     */
    public String getEntryNumber() {
        return mNumber;
    }
    public void setEntryNumber(String number) {
        mNumber = number;
    }

    public String getEntryEmail() {
        return mEmail;
    }
    public void setEntryEmail(String email) {
        mEmail = email;
    }
    public int getEntryID() {
        return mEntryID;
    }
    public void setmEntryID(int mEntryID) {
        this.mEntryID = mEntryID;
    }
}
