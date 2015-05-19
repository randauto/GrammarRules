package com.vinilearning.english.grammar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressWarnings("deprecation")
public class WebActivity extends ActionBarActivity {
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		getSupportActionBar().setTitle(getString(R.string.website_grammar));

		setupWebView();
	}

	private void setupWebView() {
		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setCacheMode(WebView.DRAWING_CACHE_QUALITY_LOW);
		webView.getSettings().setUseWideViewPort(true);
		webView.loadUrl("http://www.grammar.cl/Notes.htm");
		webView.setWebViewClient(webViewClient);
		webView.setWebChromeClient(webChromeClient);
	}

	@Override
	public void onBackPressed() {
		if (webView.canGoBack()) {
			webView.goBack();
		} else {
			super.onBackPressed();
			overridePendingTransition(R.anim.trans_right_in,
					R.anim.trans_right_out);
		}
	};

	WebChromeClient webChromeClient = new WebChromeClient() {

	};

	WebViewClient webViewClient = new WebViewClient() {
		@Override
		public void onPageStarted(WebView view, String url,
				android.graphics.Bitmap favicon) {
			super.onPageFinished(view, url);
			setProgressBarIndeterminateVisibility(true);
		};

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			setProgressBarIndeterminateVisibility(false);
		}
	};
}
