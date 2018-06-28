package blctelkom.id.srimandali.Menu;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import blctelkom.id.srimandali.MainActivity;
import blctelkom.id.srimandali.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetaKRB extends Fragment {

    private WebView webLihat;
    private FloatingActionButton fab2;
    public static PetaKRB newInstance(){
        return new PetaKRB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_peta_krb, container, false);
        ((MainActivity) getActivity())
                .setTitle("Peta KRB");
        webLihat = (WebView)view.findViewById(R.id.WVPeta);
        webLihat.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webLihat.getSettings().setBuiltInZoomControls(true);
        webLihat.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // When user clicks a hyperlink, load in the existing WebView
                view.loadUrl(url);
                return true;
            }
        });
        webLihat.getSettings().setJavaScriptEnabled(true);
        webLihat.getSettings().setGeolocationEnabled(true);
        webLihat.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }


        });
        webLihat.loadUrl("https://www.google.com/maps/d/viewer?mid=1x_yr195HxNUSN_yQLTYlhEvErT2V4TXv&ll=-7.540415%2C110.446606&z=12");
        webLihat.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        return view;
    }
}
