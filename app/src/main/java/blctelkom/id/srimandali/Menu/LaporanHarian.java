package blctelkom.id.srimandali.Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.UncheckedIOException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import blctelkom.id.srimandali.MainActivity;
import blctelkom.id.srimandali.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaporanHarian extends Fragment {
    private WebView webView;

    public LaporanHarian newInstance(){
        return new LaporanHarian();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_laporan_harian, container, false);
        ((MainActivity) getActivity())
                .setTitle("Laporan Harian");
        webView = (WebView)view.findViewById(R.id.WVLaporan);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        LoadNews();

        return view;
    }
    private void LoadNews(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://merapi.bgl.esdm.go.id/pub/data.php")
                            .get();
                    final Elements berita = doc.select("div.box-pengumuman");
                        Log.d("Berita:",berita.html());
                        String pageHtml = berita.html();
                        webView.post(new Runnable() {
                            @Override
                            public void run() {
                                webView.loadData(berita.html().split("<h2>Laporan Harian</h2> ")[1],"text/html", null);
                            }
                        });

                } catch (UncheckedIOException e){
                    if (getActivity()==null){
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(), "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (IOException e) {
                    if (getActivity()==null){
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getActivity(), "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }



}
