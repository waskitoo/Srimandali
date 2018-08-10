package blctelkom.id.srimandali;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.atomic.AtomicBoolean;

import blctelkom.id.srimandali.Menu.Chating.Chating;
import blctelkom.id.srimandali.Menu.Home;
import blctelkom.id.srimandali.Menu.KameraPengamat;
import blctelkom.id.srimandali.Menu.Kontak;
import blctelkom.id.srimandali.Menu.Login;
import blctelkom.id.srimandali.Menu.PetaKRB;
import blctelkom.id.srimandali.Menu.TiltMeter;

// UNTUK MEMBUAT ACTIVITY SEPERTI INI
public class MainActivity extends AppCompatActivity {
    // Identifikasi Seluruh variable yang ingin di gunakan
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private FloatingActionButton fab1,fab2;
    private String tabName[] = new String[]{"HOME","Kontak","Peta KRB","Kamera Pengamat","Tilt Meter"};
    public final AtomicBoolean running = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inisialisasi Variable dengan id yang di gunakan pada layaout
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("sriMANDALI");
        toolbar.setLogo(R.drawable.logo_mini);
        toolbar.setSubtitle("BPBD Kab.KLATEN");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        //kode di bawah untuk Menampilkan dan Menghilangkan floating buton pada maps
        // dan kode di bawah berguna untuk mematikan refresh pada kamera pengamat
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==2){
                    fab2.show();
                    running.set(false);
                }else if (tab.getPosition()==3){
                    fab2.hide();
                    running.set(true);
                }
                else {
                    fab2.hide();
                    running.set(false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        //Kode di bawah untuk Mengeksekusi saat Floating button Maps di Tekan dan akan membuka Maps di HP Android
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setComponent(ComponentName.unflattenFromString("com.google.android.apps.maps/com.google.android.maps.MapsActivity"));
                intent.setData(Uri.parse("https://www.google.com/maps/d/viewer?mid=1x_yr195HxNUSN_yQLTYlhEvErT2V4TXv&ll=-7.540415%2C110.446606&z=12"));
                startActivity(intent);
            }
        });
        //Kode di bawah untuk Mengeksekusi saat Floating button Chat di Tekan den akan berganti activity ke activity chat
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new Home();
                case 1:
                    return new Kontak();
                case 2:
                    return new PetaKRB();
                case 3:
                    return new KameraPengamat();
                case 4:
                    return new TiltMeter();
            }
            return null;
        }

        @Override
        public int getCount() {return 5;}

        @Override
        public CharSequence getPageTitle(int position) {return tabName[position];}

        @Override
        public int getItemPosition(@NonNull Object object) {
            return mSectionsPagerAdapter.POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            return super.instantiateItem(container, position);
        }
    }

    @Override
    protected void onPause() {
        running.set(false);
        super.onPause();
    }
}

