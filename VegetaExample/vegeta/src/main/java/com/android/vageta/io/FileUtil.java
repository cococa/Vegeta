package com.android.vageta.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by cocoa on 2015/10/19.16:30
 * email:385811416@qq.com
 */
public class FileUtil {

    public static final int KB_1 = 1024;

    private FileUtil() {
    }

    public static File getFile(File file) {
        if (file == null) {
            throw new NullPointerException("file is null ");
        }
        return file;
    }

    public static File getFile(String fileString) {
        if (fileString == null || "".equals(fileString)) {
            throw new NullPointerException("fileString is null ");
        }
        return new File(fileString);
    }

    /**
     * �����ļ���
     *
     * @param directoryString
     * @return
     */
    public static boolean createDirectory(String directoryString) {
        File file = getFile(directoryString);
        return createDirectory(file);
    }

    /**
     * �����ļ���
     *
     * @param directoryFile
     * @return
     */
    public static boolean createDirectory(File directoryFile) {
        if (!directoryFile.exists()) {
            return directoryFile.mkdirs();
        }
        return true;
    }

    /**
     * �����ļ�
     *
     * @param filePathString
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean createFile(String filePathString, String fileName) throws IOException {
        File file = getFile(filePathString);
        return createFile(file, fileName);
    }

    /**
     * �����ļ�
     *
     * @param
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean createFile(File filePath, String fileName) throws IOException {
        if (filePath == null || fileName == null || "".equals(fileName)) {
            throw new NullPointerException("createFile() file is null....");
        }
        if (filePath.exists()) {
            return createNewFile(filePath, fileName);
        } else {
            boolean t = createDirectory(filePath);
            if (t) {
                return createNewFile(filePath, fileName);
            }
            return false;
        }
    }

    /**
     * �����ļ�
     *
     * @param filePath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean createNewFile(File filePath, String fileName) throws IOException {
        File file = new File(filePath.toString() + File.separator + fileName);
        return file.createNewFile();
    }

    /**
     * ɾ���ļ�
     *
     * @param fileString
     * @return
     * @throws FileNotFoundException
     */
    public static boolean deleteFile(String fileString) throws FileNotFoundException {
        File file = getFile(fileString);
        return deleteFile(file);
    }

    /**
     * ɾ���ļ�
     *
     * @param
     * @return
     * @throws FileNotFoundException
     */
    public static boolean deleteFile(File _file) throws FileNotFoundException {
        File file = getFile(_file);

        if (!file.isFile() || !file.exists()) {
            throw new FileNotFoundException(file.toString() + "===> is not found");
        }
        return file.delete();
    }

    /**
     * ɾ���ļ����ļ���
     *
     * @param dirFile
     * @return
     */
    public static boolean deleteDirectory(String dirFile) {
        File file = getFile(dirFile);
        return deleteDirectory(file);
    }

    /**
     * ɾ���ļ����ļ���
     *
     * @param
     * @return
     */
    public static boolean deleteDirectory(File _file) {
        File dirFile = getFile(_file);
        if (dirFile.isDirectory()) {
            File[] chidlFiles = dirFile.listFiles();
            for (File file : chidlFiles) {
                deleteDirectory(file);
            }
        }
        return dirFile.delete();
    }

    /**
     * ��ȡ������
     *
     * @param fileString
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getInputStream(String fileString) throws IOException {
        File file = getFile(fileString);
        return getInputStream(file);
    }

    /**
     * ��ȡ������
     *
     * @param
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getInputStream(File _file) throws IOException {
        File file = getFile(_file);
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException(file + "= exists and it is a Directory");
            }
            if (!file.canRead()) {
                throw new IOException(file + "=exists  is FIle but can not read");
            }
        } else {
            throw new FileNotFoundException(file + "is not fonud");
        }
        return new FileInputStream(file);
    }

    /**
     * ��ȡ�����
     *
     * @param _fileString
     * @param append
     * @return
     * @throws IOException
     */
    public static OutputStream getOutoutStream(String _fileString, final boolean append) throws IOException {
        File file = getFile(_fileString);
        return getOutoutStream(file, append);
    }

    /**
     * ��ȡ�����
     *
     * @param _file
     * @param append
     * @return
     * @throws IOException
     */
    public static OutputStream getOutoutStream(File _file, final boolean append) throws IOException {
        File file = getFile(_file);
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException(file + "=exists but it is a Directory");
            }
            if (!file.canWrite()) {
                throw new IOException(file + "=exists  is file but can not write");
            }
        } else {
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                throw new NullPointerException("getOutoutStream() parentFile is null");
            }
            if (!parentFile.exists()) {
                if (!parentFile.mkdirs() || !parentFile.exists() || !parentFile.isDirectory()) { // ��������棬ֻ��Ҫ����parentFile���ɣ�����Ҫ�����ļ�
                    throw new FileNotFoundException(
                            "getOutoutStream()  parentFile is not exists or cant not be cearte, check your file ");
                }
            }
        }
        return new FileOutputStream(file, append);
    }


    /**
     * �漰���ļ��Ķ�д��Ҫ����IOUtil
     */


    /**
     * ���Ҫ�滮��ioutil��
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] getByte(File file) throws IOException {
        InputStream inStream = getInputStream(file);
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] in2b = null;
        byte[] buff = new byte[KB_1];
        int rc = 0;
        try {
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            in2b = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (swapStream != null) {
                    swapStream.close();
                }
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return in2b;
    }

}
