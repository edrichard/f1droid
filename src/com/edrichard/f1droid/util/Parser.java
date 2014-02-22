package com.edrichard.f1droid.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class util : Parser.
 * @author edrichard.
 */
public final class Parser {

    /**
     * Constructor.
     */
    private Parser() {
        super();
    }

    /**
     * Download JSON by WS.
     * @param flux is JSON on WS
     * @return result of string
     */
    public static String downloadJSON(final String flux) {
        String result = null;
        HttpURLConnection urlConnexion = null;

        try {
            URL url = new URL(flux);
            urlConnexion = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(
                    urlConnexion.getInputStream());
            result = readStream(in);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            urlConnexion.disconnect();
        }

        return result;
    }

    /**
     * readStream function.
     * @param is stream
     * @return buffer
     * @throws IOException exception
     */
    private static String readStream(final InputStream is) throws IOException {
        final int stream = 1000;
        StringBuilder sb = new StringBuilder();
        BufferedReader r =
                new BufferedReader(new InputStreamReader(is), stream);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
