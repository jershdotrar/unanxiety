package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import sh.jer.unanxiety.Cycling;
import sh.jer.unanxiety.DeepBreathing;
import sh.jer.unanxiety.MuscleRelaxation;
import sh.jer.unanxiety.R;
import sh.jer.unanxiety.Sleeping;
import sh.jer.unanxiety.Walking;

public class Exercises extends Fragment {


    public Exercises() {
        // Required empty public constructor
    }

    /**
     * Inflates layout as "view"
     * Sets the title of the activity
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        getActivity().setTitle("EXERCISES");


        /**
         * Looks for the Deep Breathing RelativeLayout, and sets a click listener on it
         * Switches to the DeepBreathing activity
         */

        RelativeLayout deepBreathing = (RelativeLayout) view.findViewById(R.id.deep_breathing);
        deepBreathing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), DeepBreathing.class) );
            }
        });

        /**
         * Looks for the Muscle Relaxation RelativeLayout, and sets a click listener on it
         * Switches to the MuscleRelaxation activity
         */

        RelativeLayout muscleRelaxation = (RelativeLayout) view.findViewById(R.id.muscle_relaxation);
        muscleRelaxation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), MuscleRelaxation.class) );
            }
        });

        /**
         * Looks for the Sleeping RelativeLayout, and sets a click listener on it
         * Switches to the Sleeping activity
         */

        RelativeLayout sleeping = (RelativeLayout) view.findViewById(R.id.sleeping);
        sleeping.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), Sleeping.class) );
            }
        });

        /**
         * Looks for the Walking RelativeLayout, and sets a click listener on it
         * Switches to the Walking activity
         */

        RelativeLayout walking = (RelativeLayout) view.findViewById(R.id.walking);
        walking.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), Walking.class) );
            }
        });

        /**
         * Looks for the Cycling RelativeLayout, and sets a click listener on it
         * Switches to the Cycling activity
         */

        RelativeLayout cycling = (RelativeLayout) view.findViewById(R.id.cycling);
        cycling.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), Cycling.class) );
            }
        });

        /**
         * Returns "view" to display the layout
         */

        return view;
    }

}
