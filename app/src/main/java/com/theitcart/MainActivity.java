package com.theitcart;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);

        setContentView(webView);

        if (isConnected()) {
            webView.setWebViewClient(new CustomWebViewClient());
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.loadUrl("http://theitcart.com");
        } else {
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    // Check Network Connection
    boolean isConnected() {
        ConnectivityManager con_manager = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected());
    }

    public class CustomWebViewClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (isConnected()) {
                view.loadUrl(url);
            } else {
                Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

       /* private ProgressDialog dialog;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (dialog == null) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.show();
            }
            dialog.setMessage("Loading...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            dialog.hide();
        }*/

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
            // super.onReceivedError(view, request, error);
        }
    }

}
