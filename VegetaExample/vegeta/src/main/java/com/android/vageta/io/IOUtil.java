package com.android.vageta.io;

import com.android.vageta.util.CharsetUtil;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.io.ByteArrayInputStream;


/**
 * Created by cocoa on 2015/10/19.18:41
 * email:385811416@qq.com
 */
public final class IOUtil {


    private static final int DEFAULT_BUFFER_SIZE = 1024;


    private IOUtil() {
    }

    /**
     * @param reader
     * @throws IOException
     */
    public static void close(final Reader reader) throws IOException {
        close((Closeable) reader);
    }

    /**
     * @param writer
     * @throws IOException
     */
    public static void close(final Writer writer) throws IOException {
        close((Closeable) writer);
    }

    /**
     * @param in
     * @throws IOException
     */
    public static void close(final InputStream in) throws IOException {
        close((Closeable) in);
    }

    /**
     * @param out
     * @throws IOException
     */
    public static void close(final OutputStream out) throws IOException {
        close(out);
    }

    /**
     * @param closeable
     * @throws IOException
     */
    private static void close(final Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }

    }

    public static InputStream strToInputStream(String str) {
        return strToInputStream(str, new CharsetUtil().getCharset("UTF-8"));
    }

    public static InputStream strToInputStream(String str, Charset charset) {
        checkNull(str);
        if (str.isEmpty()) {
            return null;
        }
        byte[] bytes = str.getBytes(charset);
        return new ByteArrayInputStream(bytes);
    }


    private static void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException("NullPointerException");
        }
    }


}