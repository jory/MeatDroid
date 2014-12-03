package com.whoisjory.meatdroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;


@SuppressLint("SetJavaScriptEnabled") public class MainActivity extends Activity {

    private WebView myWebView;
    private String lurkFn;
        
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }

        myWebView = (WebView) findViewById(R.id.webView);

        myWebView.setWebChromeClient(new MyWebChromeClient());

        WebSettings webSettings = myWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setUseWideViewPort(true);

        myWebView.loadUrl("https://chat.meatspac.es");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            myWebView.reload();
        } else if (id == R.id.action_lurk) {
            String lurkFn = "javascript:function unSad () {document.querySelector('#sad-browser').style.display = '' }";
            myWebView.loadUrl(lurkFn.toString());
            myWebView.loadUrl("javascript:unSad()");
        }
        return super.onOptionsItemSelected(item);
    }
}
