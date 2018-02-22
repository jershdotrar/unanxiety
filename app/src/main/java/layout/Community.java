package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import sh.jer.unanxiety.R;
import sh.jer.unanxiety.Thread;

public class Community extends Fragment {


    public Community() {
        // Required empty public constructor
    }

    /**
     * Inflates layout as "view"
     * Sets the title of the activity
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        getActivity().setTitle("COMMUNITY");

        /**
         * Looks for the thread_1 RelativeLayout, and sets a click listener on it
         * Switches to the Thread activity
         */

        RelativeLayout thread1 = (RelativeLayout) view.findViewById(R.id.thread_1);
        thread1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), Thread.class) );
            }
        });

        /**
         * Looks for the thread_2 RelativeLayout, and sets a click listener on it
         * Switches to the Thread activity
         */

        RelativeLayout thread2 = (RelativeLayout) view.findViewById(R.id.thread_2);
        thread2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), Thread.class) );
            }
        });

        /**
         * Looks for the thread_3 RelativeLayout, and sets a click listener on it
         * Switches to the Thread activity
         */

        RelativeLayout thread3 = (RelativeLayout) view.findViewById(R.id.thread_3);
        thread3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), Thread.class) );
            }
        });

        /**
         * Looks for the thread_4 RelativeLayout, and sets a click listener on it
         * Switches to the Thread activity
         */

        RelativeLayout thread4 = (RelativeLayout) view.findViewById(R.id.thread_4);
        thread4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), Thread.class) );
            }
        });

        /**
         * Looks for the thread_5 RelativeLayout, and sets a click listener on it
         * Switches to the Thread activity
         */

        RelativeLayout thread5 = (RelativeLayout) view.findViewById(R.id.thread_5);
        thread5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), Thread.class) );
            }
        });

        /**
         * Looks for the thread_6 RelativeLayout, and sets a click listener on it
         * Switches to the Thread activity
         */

        RelativeLayout thread6 = (RelativeLayout) view.findViewById(R.id.thread_6);
        thread6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), Thread.class) );
            }
        });

        /**
         * Returns "view" to display the layout
         */

        return view;
    }
}
