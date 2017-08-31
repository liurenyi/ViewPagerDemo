package com.lry.viewpagerdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ArrayList<ImageView> imageViews;
    private LinearLayout dotLinearLayout;
    private int currentIndex = 0;
    private int[] backgroundViews = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        for (int i = 0; i < backgroundViews.length; i++) {
            ImageView Images = new ImageView(this);
            Images.setImageResource(backgroundViews[i]);
            imageViews.add(Images);
            ImageView dotImages = new ImageView(this);
            if (i == currentIndex) {
                dotImages.setImageResource(R.drawable.dot_selected);
            } else {
                dotImages.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.leftMargin = 10;
            }
            dotImages.setLayoutParams(params);
            dotLinearLayout.addView(dotImages);
        }

        adapter = new MyAdapter(imageViews);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(this);

    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        dotLinearLayout = (LinearLayout) findViewById(R.id.dot_linearLayout);
        imageViews = new ArrayList<>();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentIndex = position;
        for (int i = 0; i < dotLinearLayout.getChildCount(); i++) {
            ImageView views = (ImageView) dotLinearLayout.getChildAt(i);
            if (i == currentIndex) {
                views.setImageResource(R.drawable.dot_selected);
            } else {
                views.setImageResource(R.drawable.dot_normal);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class MyAdapter extends PagerAdapter {

        public ArrayList<ImageView> imageViews;

        public MyAdapter(ArrayList<ImageView> imageViews) {
            this.imageViews = imageViews;
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }
    }
}
