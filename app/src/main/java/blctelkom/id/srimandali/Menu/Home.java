package blctelkom.id.srimandali.Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.UncheckedIOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import blctelkom.id.srimandali.MainActivity;
import blctelkom.id.srimandali.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private WebView mWVStat,mWVBErita;
    private SwipeRefreshLayout swp;
    private LinearLayout utama,refreshLayout;
    public static Home newInstance(){
        return new Home();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setTitle("Home");
        swp = (SwipeRefreshLayout)view.findViewById(R.id.swpHome) ;
        mWVStat = (WebView)view.findViewById(R.id.WVStatus);
        mWVBErita = (WebView)view.findViewById(R.id.WVBerita);
        utama = (LinearLayout)view.findViewById(R.id.TampilanHome);
        refreshLayout = (LinearLayout)view.findViewById(R.id.linearRefreshHome);
        //Method dibawah ini untuk Menampilkan Berita
        LoadNews();
        // Kode Di bawah ini untuk Menampilkan Fragmen Lain dari Class Fragment Home
        // Tetapi Di Handle Oleh MainActivity menggunakan Kode getActivity():
        LaporanHarian fragment = new LaporanHarian();
        fragment.setArguments(getActivity().getIntent().getExtras());
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.frame1,fragment,"LaporanHarian").commit();
        getActivity().getSupportFragmentManager().popBackStack();
        //Kode Di bawah Ini Untuk merefresh kebawah saat error (Merefresh method berita saat terjadi kesalahan)
        // Untuk mengetahui Swipe to refresh cek Lyaout XML
        swp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Method dibawah ini untuk Menampilkan Berita
                LoadNews();
            }
        });
        return view;
    }
    // Method LoadNews Harus di panggil Seperti Di atas, Jika Tidak Di ambil maka kode method tidak akan berjalan
    public void LoadNews(){
        //kode di bawah ini untuk menjalankan Refreshlayout agar icon Loading Muncul
        refreshLayout.setVisibility(refreshLayout.INVISIBLE);
        utama.setVisibility(utama.VISIBLE);
        swp.setRefreshing(true);
        // Kode di bawah ini untuk Menjalankan Jsoup
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Kode ini untuk membuat Koneksi ke URL yang di tuju
                    Document doc = Jsoup.connect("http://merapi.bgl.esdm.go.id/").get();
                    // Kode ini mangambil cssQuery pada bagian tertentu
                    final Elements berita = doc.select("div.col-sm-8");
                    // Kode ini untuk menempatkan cssQuery yang di ambil ke dala webview pada Layout agar bisa di tampilkan
                    mWVBErita.post(new Runnable() {
                        @Override
                        public void run() {
                            // Kode unuk menapilkan ke webview
                            mWVBErita.loadData(berita.html(),"text/html",null);
                        }
                    });
                    // Kode ini mangambil cssQuery pada bagian tertentu
                    Elements status = doc.select("h2");
                    // Kode ini akan mengambil seluruh <h2> dan memfilter <h2> yang terkandung "Status Merapi:"
                    for (final Element stat : status){
                        if(stat.text().contains("Status Merapi :")){
                            mWVStat.post(new Runnable() {
                                @Override
                                public void run() {
                                    // Kode unuk menapilkan ke webview
                                mWVStat.loadData("<h2>"+stat.html()+"</h2>","text/html",null);
                                }
                            });
                        }
                    }
                    // Kode di bawah ini untuk Menghandle Error
                }   catch (IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            utama.setVisibility(utama.INVISIBLE);
                            refreshLayout.setVisibility(refreshLayout.VISIBLE);
                        }
                    });
                }
            }
            //Kode ini untuk menjalankan Thread yang di buat
        }).start();
        // Kode ini Aka mematikan icon loading swipe to refresh
        swp.setRefreshing(false);
    }

}
