package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import sh.jer.unanxiety.MainActivity;
import sh.jer.unanxiety.NewMedication;
import sh.jer.unanxiety.R;

public class Home extends Fragment {

    /**
     * Constructs the Java class
     */

    public Home() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**
         * Inflates the layout for this fragment as "view"
         */

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /**
         * Sets the title of the activity
         */

        getActivity().setTitle("HOME");

        /**
         * Finds the SMS button and listens to hear if it is clicked
         * On click, it sends a text, then displays a confirmation message
         */

        Button smsButton = (Button) view.findViewById(R.id.contact_button);
        smsButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String phoneNo = "+5556";
                String msg = "UNANXIETY: I am having an anxiety attack, please call me immediately.";
                SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, msg, null, null);
                Toast.makeText(getActivity(),"You sent a text, check your messages!",Toast.LENGTH_LONG).show();
            }
            });

        /**
         * Finds the Journal button  and listens to hear if it is clicked
         * On click, it switches to the Journal fragment
         */

        Button journalButton = (Button) view.findViewById(R.id.journal_button);
        journalButton.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, new Journal());
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });

        /**
         * Returns "view" to display the layout
         */

        return view;
    }

}
