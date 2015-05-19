package com.vinilearning.english.grammar.utils;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class JsonUtil {
	public static String loadJSONFromAsset(Context context, String jsonFileName) {
		String json = null;
		try {

			InputStream is = context.getAssets().open(jsonFileName + ".json");

			int size = is.available();

			byte[] buffer = new byte[size];

			is.read(buffer);

			is.close();

			json = new String(buffer, "UTF-8");

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;

	}
}
