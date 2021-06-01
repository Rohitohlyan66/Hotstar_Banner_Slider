package com.example.hotstar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    ViewPager2 pager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Removing Elevation from ToolBar---------------------------------------------------------->
        getSupportActionBar().setElevation(0);

        //List of data images that are shown in the VP2--------------------------------------------->
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.photo1);
        list.add(R.drawable.photo2);
        list.add(R.drawable.photo3);
        list.add(R.drawable.photo4);
        list.add(R.drawable.photo5);
        list.add(R.drawable.photo1);
        list.add(R.drawable.photo2);
        list.add(R.drawable.photo3);
        list.add(R.drawable.photo4);
        list.add(R.drawable.photo5);

        //Attaching adapter to VP2------------------------------------------------------------------>
        pager2 = findViewById(R.id.pager2);
        Pager2Adapter adapter = new Pager2Adapter(list, pager2);
        pager2.setAdapter(adapter);

        /*
         *List items are doubled(copied twice)
         * so I can make the VP2 to scroll left also(When app is started)
         * So setting the current item as the middle element, it can be swiped left also initially
         */
        pager2.setCurrentItem(5, true);


        //Decorating our VP2------------------------------------------------------------------------>
        pager2.setClipToPadding(false);
        pager2.setClipChildren(false);
        pager2.setOffscreenPageLimit(3);
        pager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(10));
        pager2.setPageTransformer(transformer);


        //Handling VP2 page change callbacks-------------------------------------------------------->
        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                //Sliding Automatically
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });

    }


    //Auto Slider Functionality--------------------------------------------------------------------->
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            pager2.setCurrentItem(pager2.getCurrentItem() + 1, true);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
}