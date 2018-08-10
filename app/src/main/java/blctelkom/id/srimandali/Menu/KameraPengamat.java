package blctelkom.id.srimandali.Menu;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.UncheckedIOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import blctelkom.id.srimandali.MainActivity;
import blctelkom.id.srimandali.Menu.Adapter.KameraPengamatAdapter;
import blctelkom.id.srimandali.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KameraPengamat extends Fragment {
    // Membuat Variable yang di tentukan
    private ArrayList<String> dataTempat;
    private ArrayList<String> dataGambar;
    private KameraPengamatAdapter mAdapter;
    private Thread t,x;
    int count;

    public static KameraPengamat newInstance(){
        return new KameraPengamat();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kamera_pengamat, container, false);
        ((MainActivity) getActivity())
                .setTitle("Kamera Pengamat");
        // Inisialisasi
        dataTempat = new ArrayList<String>();
        dataGambar = new ArrayList<String>();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.RVKamera);
        // Untuk RecylerView Silahkan Di pelajari pada android vundamental
        // RecylerView Akan menginflate seluruh Layout KameraPengamat
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new KameraPengamatAdapter(dataTempat,dataGambar,getActivity());
        // Adapter recylerview akan di Update
        recyclerView.setAdapter(mAdapter);
        // Method Untuk Menampilkan Data
        LoadData();
        // Kode di bawah ini akan Merefresh data setiap 11 detik
        t = new  Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    try {
                        // Waktu untuk refresh setiap 11 detik
                        Thread.sleep(11000);
                        if (getActivity()==null){
                            return;
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (((MainActivity) getActivity()).running.get()){
                                    //Kode di bawah ini untuk nampilkan data
                                    LoadData();
                                }
                            }
                        });
                        // Kode untuk menghadle error
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // Kode untuk menjalankan thread di atas
        t.start();

        return view;
    }
    // Metdon untuk menampilkan data
    private void LoadData(){
        // Thread untuk Jsoup agar bisa berjalan
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Mengambil seluruh elemen dari link URL
                    Document doc = Jsoup.connect("http://merapi.bgl.esdm.go.id/viewer_images/index.php").get();
                    // Mengambil Class div.innerWrapper Untuk Mengambil Nama Kamera
                    Elements camera = doc.select("div.innerWrapper");
                    // Mengambil Class div.innerWrapper Untuk Mengambil Url Gambar
                    Elements image = doc.getElementsByTag("img");
                    //Menghapus Array Yang Terisi
                    dataTempat.clear();dataGambar.clear();
                    // Perulangan untuk Megambil String da Gambar dan di tampilkan ke dalam RecylerView
                    for (Element cam : image){
                        // Menambahkan Nama Kamera Kedalam RecylerView
                        dataTempat.add(cam.attr("alt"));
                        // Menambahkan Url Kedalam RecylerView
                        dataGambar.add(cam.absUrl("src"));
                        Log.d("CameraData1:", cam.absUrl("src"));
                        Log.d("CameraData1:",cam.attr("alt"));
                        // Kode untuk Memberi tahu bahwa RectlerView kalau data berubah
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyDataSetChanged();
                            }
                        });

                    }
                    // Catch untuk Menghandle Jika terjadi Kesalahan
                }catch (UncheckedIOException e){
                    if (getActivity()==null){
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(), "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                        }
                    });

                }catch (NullPointerException e) {
                    if (getActivity()==null){
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(), "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch (SocketTimeoutException e){
                    if (getActivity()==null){
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(), "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onResume() {
        ((MainActivity) getActivity()).running.set(true);
        super.onResume();
    }
}
