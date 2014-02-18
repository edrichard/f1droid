package com.edrichard.f1droid.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Parser {

	/**
	 * Download JSON by WS.
	 * @return result
	 */
	public static String downloadJSON(String flux) {
		String result = null;
		HttpURLConnection urlConnexion = null;
		
		try {
			URL url = new URL(flux);
			urlConnexion = (HttpURLConnection) url.openConnection();
			
			InputStream in = new BufferedInputStream(
					urlConnexion.getInputStream());
			result = readStream(in);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			urlConnexion.disconnect();
		}
		
		return result;
	}
	
	/**
	 * readStream function.
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private static String readStream(InputStream is) throws IOException {
	    StringBuilder sb = new StringBuilder();  
	    BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);  
	    for (String line = r.readLine(); line != null; line =r.readLine()){  
	        sb.append(line);  
	    }  
	    is.close();  
	    return sb.toString();
	}
}
