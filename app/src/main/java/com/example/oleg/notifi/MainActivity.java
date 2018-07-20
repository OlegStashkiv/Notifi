package com.example.oleg.notifi;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    int page_number = 1;
    ImageButton b_plus;
    ImageButton b_minus;
    TextView number_page;



    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        b_plus = (ImageButton) findViewById(R.id.b_plus);
        b_minus = (ImageButton) findViewById(R.id.b_minus);


        b_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page_number += 1;
                mViewPager.setAdapter(mSectionsPagerAdapter);
                mViewPager.setCurrentItem(page_number - 1);

                Appearance();
            }
        });
        b_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.cancel(page_number);
                page_number -= 1;
                mViewPager.setAdapter(mSectionsPagerAdapter);
                mViewPager.setCurrentItem(page_number - 1);
                Appearance();
            }
        });

    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button b_cr_not = (Button) rootView.findViewById(R.id.b_cr_not);
            b_cr_not.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            b_cr_not.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
       NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(getActivity(), "My chanel")
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("You create a notification")
                                    .setContentText("Notification"+getArguments().getInt(ARG_SECTION_NUMBER));

                    Notification notification = builder.build();
                    NotificationManager notificationManager;
                    notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(getArguments().getInt(ARG_SECTION_NUMBER), notification);

                }
            });
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return page_number;
        }
    }

    public void Appearance(){
        if(mViewPager.getCurrentItem() == page_number){
            b_minus.setEnabled(false);
            b_minus.setVisibility(View.INVISIBLE);
        } else {
            b_minus.setEnabled(true);
            b_minus.setVisibility(View.VISIBLE);
        }
    }
}
