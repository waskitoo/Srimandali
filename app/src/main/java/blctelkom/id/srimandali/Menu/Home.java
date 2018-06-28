package blctelkom.id.srimandali.Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

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
        mWVStat = (WebView)view.findViewById(R.id.WVStatus);
        mWVBErita = (WebView)view.findViewById(R.id.WVBerita);
        LoadNews();
        LaporanHarian fragment = new LaporanHarian();
        fragment.setArguments(getActivity().getIntent().getExtras());
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.frame1,fragment,"LaporanHarian").commit();
        getActivity().getSupportFragmentManager().popBackStack();
        return view;
    }
    public void LoadNews(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://merapi.bgl.esdm.go.id/").get();
                    final Elements berita = doc.select("div.col-sm-8");
                    mWVBErita.post(new Runnable() {
                        @Override
                        public void run() {
                            mWVBErita.loadData(berita.html(),"text/html",null);
                        }
                    });
                    Log.d("Berita:",berita.text());
                    Elements status = doc.select("h2");
                    for (final Element stat : status){
                        if(stat.text().contains("Status Merapi :")){
                            mWVStat.post(new Runnable() {
                                @Override
                                public void run() {
                                mWVStat.loadData("<h2>"+stat.html()+"</h2>","text/html",null);
                                }
                            });
                            Log.d("HOMESTAT:",stat.text());
                        }
                    }
                } catch (UncheckedIOException e) {
                    Log.d("HOMESTAT:","CEK INTERNET");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
