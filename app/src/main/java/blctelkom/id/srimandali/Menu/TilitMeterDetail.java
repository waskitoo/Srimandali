package blctelkom.id.srimandali.Menu;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import blctelkom.id.srimandali.R;

public class TilitMeterDetail extends AppCompatActivity {
    private WebView webLihat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilit_meter_detail);
        displayHomeAsUpEnabled();
        // Mengambil Strig dari activity Adapter
        setTitle(getIntent().getStringExtra("NamaKEY"));
        String link = getIntent().getStringExtra("LinkKEY");
        // Inisialisasi WebView
        webLihat = (WebView)findViewById(R.id.WVTilt);
        //Mangkatifkan java script pada weview
        webLihat.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //Mengaktifkan Tombol Zoomin dan zoomout
        webLihat.getSettings().setBuiltInZoomControls(true);
        webLihat.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // When user clicks a hyperlink, load in the existing WebView
                view.loadUrl(url);
                return true;
            }
        });
        //Mangkatifkan java script pada weview
        webLihat.getSettings().setJavaScriptEnabled(true);
        // Mengkatifkan Location
        webLihat.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }


        });
        //Mengambil Link dari Adapter
        webLihat.loadUrl(link);
    }
    // Membuat Method saat Tombil Back Di sentuh
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
    // Membuat Method saat Tombol back di actionbar di sentuh
    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
