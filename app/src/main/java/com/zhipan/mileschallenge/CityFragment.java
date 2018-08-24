package com.zhipan.mileschallenge;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CityFragment extends Fragment {

    public static final String PAGE_ID = "pageId";
    private static final String TAG = CityFragment.class.getSimpleName();

    private int pageId = -1;

    private ImageView imageView;
    private CardView cardView;
    private TextView cityName;

    private int zoomState = 0;
    private boolean zooming = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.city_fragment, container, false);
        imageView = view.findViewById(R.id.city_image_view);
        cardView = view.findViewById(R.id.city_card_view);
        cityName = view.findViewById(R.id.city_name);

        Bundle data = getArguments();
        if (data != null) {
            pageId = data.getInt(PAGE_ID);
        }

        switch (pageId) {
            case 0:
                cityName.setText(R.string.city_name_boston);
                imageView.setImageResource(R.drawable.boston);
                break;
            case 1:
                cityName.setText(R.string.city_name_new_york);
                imageView.setImageResource(R.drawable.new_york);
                break;
            case 2:
                cityName.setText(R.string.city_name_san_francisco);
                imageView.setImageResource(R.drawable.san_francisco);
                break;
            case 3:
                cityName.setText(R.string.city_name_washington);
                imageView.setImageResource(R.drawable.washington);
                break;
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (!getUserVisibleHint() || zooming)
                    return;

                if (zoomState == 0) {
                    zoomIn();
                } else {
                    zoomOut();
                }

            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CityDetailsActivity.class);

                Pair<View, String> p1 = Pair.create((View)imageView, "city_image_view");
                Pair<View, String> p2 = Pair.create((View)cardView, "comments");
//                Pair<View, String> p3 = Pair.create((View)tvName, "text");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), p1, p2);

                intent.putExtra(PAGE_ID, pageId);
                startActivity(intent, options.toBundle());
            }
        });

        return view;
    }

    private void zoomIn() {
        if (zoomState == 1)
            return;

        zooming = true;

        final int delta = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
                getResources().getDisplayMetrics());

        ObjectAnimator cityImageAnimator= ObjectAnimator.ofFloat(imageView, "translationY", 0, -10*delta);
        cityImageAnimator.setDuration(300);
        cityImageAnimator.start();

        cityImageAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                zooming = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                zooming = false;
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        ObjectAnimator cityNameAnimator= ObjectAnimator.ofFloat(cityName, "translationY", 0, -10*delta);
        cityNameAnimator.setDuration(300);
        cityNameAnimator.start();

        ValueAnimator animation = ValueAnimator.ofInt(0, delta);
        animation.setDuration(300);

        final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
        final int leftMargin = lp.leftMargin;
        final int rightMargin = lp.rightMargin;
        final int topMargin = lp.topMargin;
        final int bottomMargin = lp.bottomMargin;

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currOffset = Integer.parseInt(valueAnimator.getAnimatedValue().toString());

                Log.i(TAG, "onAnimationUpdate: currMargin="+currOffset);

                lp.setMargins(leftMargin - 3*currOffset, topMargin+2*currOffset,
                        rightMargin - 3*currOffset, bottomMargin-6*currOffset);
                cardView.requestLayout();
            }
        });
        animation.start();

        zoomState = 1;
    }

    private void zoomOut() {
        if (zoomState == 0)
            return;

        zooming = true;

        final int delta = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
                getResources().getDisplayMetrics());

        ObjectAnimator cityImageAnimator= ObjectAnimator.ofFloat(imageView, "translationY", -10*delta, 0);
        cityImageAnimator.setDuration(300);
        cityImageAnimator.start();

        cityImageAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                zooming = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                zooming = false;
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        ObjectAnimator cityNameAnimator= ObjectAnimator.ofFloat(cityName, "translationY", -10*delta, 0);
        cityNameAnimator.setDuration(300);
        cityNameAnimator.start();

        ValueAnimator animation = ValueAnimator.ofInt(0, delta);
        animation.setDuration(300);

        final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
        final int leftMargin = lp.leftMargin;
        final int rightMargin = lp.rightMargin;
        final int topMargin = lp.topMargin;
        final int bottomMargin = lp.bottomMargin;

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currOffset = Integer.parseInt(valueAnimator.getAnimatedValue().toString());

                Log.i(TAG, "onAnimationUpdate: currMargin="+currOffset);

                lp.setMargins(leftMargin + 3*currOffset, topMargin-2*currOffset,
                        rightMargin + 3*currOffset, bottomMargin+6*currOffset);
                cardView.requestLayout();
            }
        });
        animation.start();


//        Animation zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.city_card_view_zoom_out);
//        zoomIn.setFillAfter(true);
//        cardView.startAnimation(zoomIn);

        zoomState = 0;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (!isVisibleToUser)
            zoomOut();

        Log.i(TAG, "pageId: "+pageId+"; setUserVisibleHint: "+isVisibleToUser);
    }
}
