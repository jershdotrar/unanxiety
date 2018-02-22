package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import sh.jer.unanxiety.NewMedication;
import sh.jer.unanxiety.R;

public class Medication extends Fragment {


    public Medication() {
        // Required empty public constructor
    }

    /**
     * Inflates layout as "view"
     * Sets the title of the activity
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medication, container, false);
        getActivity().setTitle("MEDICATION REMINDERS");

        /**
         * Looks for the med_1 RelativeLayout, and sets a click listener on it
         * Switches to the Med1 activity
         */

        RelativeLayout med1 = (RelativeLayout) view.findViewById(R.id.med_1);
        med1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), NewMedication.class) );
            }
        });

        /**
         * Looks for the med_2 RelativeLayout, and sets a click listener on it
         * Switches to the Med2 activity
         */

        RelativeLayout med2 = (RelativeLayout) view.findViewById(R.id.med_2);
        med2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), NewMedication.class) );
            }
        });

        /**
         * Finds the new_medication_button and listens to hear if it is clicked
         * On click, it displays a message
         */

        Button newMedication = (Button) view.findViewById(R.id.new_medication_button);
        newMedication.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(),"DEMO LIMIT: Medication list full!",Toast.LENGTH_LONG).show();
            }
        });

        /**
         * Returns "view" to display the layout
         */

        return view;
    }

}
