package sh.jer.unanxiety;

public class Entry {

    /**
     * Creates the title and body Strings
     */
    private String mTitle;
    private String mBody;
    private int mEntryID;

    /**
     * Sets the values for the title and body Strings
     */
    public Entry(String title, String body, int id) {
        mTitle = title;
        mBody = body;
        mEntryID = id;
    }

    /**
     * Get entry title
     * Set entry title
     */
    public String getEntryTitle() {
        return mTitle;
    }
    public void setEntryTitle(String title) {
        mTitle = title;
    }

    /**
     * Get entry body text
     * Set entry title
     */
    public String getEntryBody() {
        return mBody;
    }
    public void setEntryBody(String body) {
        mBody = body;
    }
    public int getEntryID() {
        return mEntryID;
    }
    public void setmEntryID(int mEntryID) {
        this.mEntryID = mEntryID;
    }
}
