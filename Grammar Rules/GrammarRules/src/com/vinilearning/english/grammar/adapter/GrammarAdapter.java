package com.vinilearning.english.grammar.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinilearning.english.grammar.GrammarApplication;
import com.vinilearning.english.grammar.MainActivity.CATEGORY;
import com.vinilearning.english.grammar.R;
import com.vinilearning.english.grammar.bean.GrammarRule;

public class GrammarAdapter extends BaseAdapter {
	private ArrayList<GrammarRule> list;

	private Context context;

	public GrammarAdapter(Context context, ArrayList<GrammarRule> arrGrammar) {
		this.context = context;
		this.list = arrGrammar;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater vi;
			vi = LayoutInflater.from(context);
			convertView = vi.inflate(R.layout.item_grammar, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.tvTitle);
			viewHolder.description = (TextView) convertView
					.findViewById(R.id.tvDescription);
			viewHolder.ivCategory = (ImageView) convertView
					.findViewById(R.id.ivCategory);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		GrammarRule p = (GrammarRule) getItem(position);

		if (p != null) {
			viewHolder.title.setText(p.getTitle());
			viewHolder.description.setText(p.getDescription());
			Drawable image = context.getResources().getDrawable(
					R.drawable.basic);
			if (GrammarApplication.category == CATEGORY.BASIC) {
				image = context.getResources().getDrawable(R.drawable.basic);
			} else if (GrammarApplication.category == CATEGORY.INTERMEDIATE) {
				image = context.getResources().getDrawable(
						R.drawable.intermediate);
			} else if (GrammarApplication.category == CATEGORY.ADVANCED) {
				image = context.getResources().getDrawable(R.drawable.advanced);
			}
			viewHolder.ivCategory.setBackgroundDrawable(image);
		}
		return convertView;
	}

	static class ViewHolder {
		private TextView title;
		private TextView description;
		private ImageView ivCategory;
	}

}
