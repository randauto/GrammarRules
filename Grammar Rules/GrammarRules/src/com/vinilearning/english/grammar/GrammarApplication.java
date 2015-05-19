package com.vinilearning.english.grammar;

import java.util.ArrayList;

import android.app.Application;

import com.vinilearning.english.grammar.MainActivity.CATEGORY;
import com.vinilearning.english.grammar.bean.GrammarRule;

public class GrammarApplication extends Application {
	public static CATEGORY category = CATEGORY.BASIC;
	public static ArrayList<GrammarRule> arrGrammarBasic;
	public static ArrayList<GrammarRule> arrGrammarIntermediate;
	public static ArrayList<GrammarRule> arrGrammarAdvanced;

	@Override
	public void onCreate() {
		super.onCreate();
	}
}
