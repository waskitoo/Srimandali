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
        dataTempat = new ArrayList<String>();
        dataGambar = new ArrayList<String>();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.RVKamera);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new KameraPengamatAdapter(dataTempat,dataGambar,getActivity());
        recyclerView.setAdapter(mAdapter);
        LoadData();
        Log.d("RunningAct::",""+((MainActivity) getActivity()).running.get());
        t = new  Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    try {
                        Thread.sleep(11000);
                        if (getActivity()==null){
                            return;
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //mAdapter.notifyItemRemoved(count);
                                count++;
                                Log.d("Waktu:",""+count);
                                if (((MainActivity) getActivity()).running.get()){
                                    LoadData();
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

        return view;
    }
    private void LoadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://merapi.bgl.esdm.go.id/viewer_images/index.php").get();
                    Elements camera = doc.select("div.innerWrapper");
                    Elements image = doc.getElementsByTag("img");
                    Log.d("DataM:", image.toString());
                    dataTempat.clear();dataGambar.clear();
                    for (Element cam : image){
                        dataTempat.add(cam.attr("alt"));
                        dataGambar.add(cam.absUrl("src"));
                        Log.d("CameraData1:", cam.absUrl("src"));
                        Log.d("CameraData1:",cam.attr("alt"));
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //mAdapter.notifyItemChanged(0,dataGambar.size());
                                mAdapter.notifyDataSetChanged();
                                //mAdapter.notifyItemRangeRemoved(0,dataTempat.size());
                                //
                            }
                        });

                    }

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

}
