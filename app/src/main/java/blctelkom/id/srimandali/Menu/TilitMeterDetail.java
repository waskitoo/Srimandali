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
        setTitle(getIntent().getStringExtra("NamaKEY"));
        String link = getIntent().getStringExtra("LinkKEY");
        Log.d("INTENTLINK",link);
        webLihat = (WebView)findViewById(R.id.WVTilt);
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
        webLihat.loadUrl(link);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
