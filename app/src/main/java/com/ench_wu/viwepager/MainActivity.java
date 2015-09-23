package com.ench_wu.viwepager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;

    private ArrayList<bean> list = new ArrayList<bean>();
    private ImageView image;
    private TextView doc;
    private LinearLayout dotLayout;
    private View point;
    /**
     * 循环
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initData();
        initListener();
    }


    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                upDateChange();
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initUI() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        doc = (TextView) findViewById(R.id.tv_doc);
        dotLayout = (LinearLayout) findViewById(R.id.dot_layout);
    }

    private void initData() {
        list.add(new bean(R.mipmap.a, "AAAAAAAAAA"));
        list.add(new bean(R.mipmap.b, "BBBBBBBBBB"));
        list.add(new bean(R.mipmap.c, "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC"));
        list.add(new bean(R.mipmap.d, "DDDDDDDDDDDDDDDD"));
        list.add(new bean(R.mipmap.e, "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE"));

        initDots();

        viewPager.setAdapter(new MyPagerAdapter());

        int Current = Integer.MAX_VALUE / 2;
        int values = Current % list.size();
        viewPager.setCurrentItem(Current - values);
        upDateChange();

        handler.sendEmptyMessageDelayed(0, 2000);
    }

    private void initDots() {
        for (int i = 0; i < list.size(); i++) {
            point = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            params.leftMargin = 5;
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.selector_dot);

            dotLayout.addView(point);
        }
    }

    /**
     * 更新文本和dot
     */
    private void upDateChange() {
        int currentItem = viewPager.getCurrentItem() % list.size();

        for (int i = 0; i < dotLayout.getChildCount(); i++) {
            dotLayout.getChildAt(i).setEnabled(i == currentItem);
        }

        doc.setText(list.get(currentItem).getDoc());
    }


    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        /**
         *相当于baseAdapter里的getView
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = View.inflate(MainActivity.this, R.layout.item_image, null);
            image = (ImageView) view.findViewById(R.id.image);
            image.setImageResource(list.get(position % list.size()).getDrawId());

            container.addView(view);

            return view;
        }
    }
}
