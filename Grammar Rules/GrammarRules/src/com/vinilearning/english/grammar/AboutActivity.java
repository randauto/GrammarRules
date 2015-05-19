package com.vinilearning.english.grammar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.webkit.WebView;

public class AboutActivity extends ActionBarActivity {
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);
		getSupportActionBar().setTitle(getString(R.string.action_settings));

		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setCacheMode(WebView.DRAWING_CACHE_QUALITY_LOW);
		webView.getSettings().setUseWideViewPort(true);
		webView.loadUrl("http://www.grammar.cl/");
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	}
}
