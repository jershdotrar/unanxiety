package sh.jer.unanxiety;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MuscleRelaxation extends AppCompatActivity {

    ImageView animCircle;
    TextView textRelax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_relaxation);

        setTitle("MUSCLE RELAXATION");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        animCircle = (ImageView) findViewById(R.id.anim_circle);
        textRelax = (TextView) findViewById(R.id.text_relax);

        final Animation growAnim = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        final Animation shrinkAnim = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        growAnim.setDuration(5000);
        shrinkAnim.setDuration(10000);

        animCircle.setAnimation(growAnim);
        growAnim.start();

        growAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                animCircle.setAnimation(shrinkAnim);
                shrinkAnim.start();
                textRelax.setText("Relax muscles");
            }
        });
        shrinkAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                animCircle.setAnimation(growAnim);
                growAnim.start();
                textRelax.setText("Tense muscles");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
