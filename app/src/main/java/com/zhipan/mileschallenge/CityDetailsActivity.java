package com.zhipan.mileschallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import static com.zhipan.mileschallenge.CityFragment.PAGE_ID;

public class CityDetailsActivity extends AppCompatActivity{

    private ImageView cityImageView;
    private RecyclerView commentsRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.city_details_activity);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_action_close);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFinishAfterTransition();
            }
        });

        Intent intent = getIntent();
        int pageId = intent.getIntExtra(PAGE_ID, 0);
        int imageResource = 0;

        switch (pageId) {
            case 0:
                imageResource = R.drawable.boston;
                break;
            case 1:
                imageResource = R.drawable.new_york;
                break;
            case 2:
                imageResource = R.drawable.san_francisco;
                break;
            case 3:
                imageResource = R.drawable.washington;
                break;
        }

        cityImageView = findViewById(R.id.city_image_view);
        cityImageView.setImageResource(imageResource);

        commentsRecyclerView = findViewById(R.id.comments_recycler_view);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        commentsRecyclerView.setLayoutManager(lm);
        commentsRecyclerView.setAdapter(new CommentsAdapter());
    }
}
