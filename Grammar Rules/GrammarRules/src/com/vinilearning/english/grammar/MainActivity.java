package com.vinilearning.english.grammar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	private boolean doubleBackToExitPressedOnce = false;

	private Button btnBasic, btnIntermediate, btnAdvanced;

	public enum CATEGORY {
		BASIC, INTERMEDIATE, ADVANCED
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

	}

	private void initView() {
		btnBasic = (Button) findViewById(R.id.btnBasic);
		btnIntermediate = (Button) findViewById(R.id.btnInter);
		btnAdvanced = (Button) findViewById(R.id.btnAdvanced);

		btnBasic.setOnClickListener(this);
		btnIntermediate.setOnClickListener(this);
		btnAdvanced.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.trans_left_in,
					R.anim.trans_left_out);
			return true;
		}

		if (id == R.id.action_view_online) {
			Intent intent = new Intent(this, WebActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.trans_left_in,
					R.anim.trans_left_out);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			return;
		}

		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Please click BACK again to exit",
				Toast.LENGTH_SHORT).show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 2000);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, ItemListActivity.class);
		switch (v.getId()) {
		case R.id.btnBasic:
			GrammarApplication.category = CATEGORY.BASIC;
			startActivity(intent);
			overridePendingTransition(R.anim.trans_left_in,
					R.anim.trans_left_out);
			break;

		case R.id.btnInter:
			GrammarApplication.category = CATEGORY.INTERMEDIATE;
			startActivity(intent);
			overridePendingTransition(R.anim.trans_left_in,
					R.anim.trans_left_out);
			break;

		case R.id.btnAdvanced:
			GrammarApplication.category = CATEGORY.ADVANCED;
			startActivity(intent);
			overridePendingTransition(R.anim.trans_left_in,
					R.anim.trans_left_out);
			break;

		default:
			break;
		}
	}
}
