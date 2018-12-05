package ro.greenerpastures.animationssampleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeTransform;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    private ViewGroup containerView;
    private View rocketView;
    private boolean isAnimationStarted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerView = findViewById(R.id.container);
        rocketView = findViewById(R.id.image);

        containerView.setOnClickListener(v -> triggerAnimation());
    }

    private void triggerAnimation() {
        // Reset the animation if needed
        if (isAnimationStarted) {
            resetAnimation();
            isAnimationStarted = false;
        } else {
            // Otherwise, start the animation
            startAnimationViaViewPropertyAnimator();
//            startAnimationViaObjectAnimator();
//            startAnimationViaTransition();
            isAnimationStarted = true;
        }
    }

    private void startAnimationViaViewPropertyAnimator() {
        rocketView.animate()
                .translationY(-containerView.getHeight())
                .rotationY(180)
                .setDuration(2000)
                .setInterpolator(new AccelerateInterpolator())
                .start();
    }

    private void startAnimationViaTransition() {
        TransitionManager.beginDelayedTransition(containerView, new ChangeTransform().setDuration(2000));
        rocketView.setTranslationY(-containerView.getHeight());
        rocketView.setRotationY(180);
    }

    private void startAnimationViaObjectAnimator() {
        // TODO: Implement via ObjectAnimator
    }

    private void resetAnimation() {
        rocketView.animate().cancel();
        rocketView.setTranslationY(0);
        rocketView.setRotationY(0);
    }
}
