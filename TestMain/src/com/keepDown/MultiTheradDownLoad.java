package com.keepDown;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Encode:UTF-8
 * 多线程的断点下载程序，根据输入的url和指定线程数，来完成断点续传功能。
 * 每个线程支负责某一小段的数据下载；再通过RandomAccessFile完成数据的整合。
 */
public class MultiTheradDownLoad {

    private String filepath = null;
    private String filename = null;
    private String tmpfilename = null;

    private int threadNum = 0;

    private CountDownLatch latch = null;//设置一个计数器，代码内主要用来完成对缓存文件的删除

    private long fileLength = 0l;
    private long threadLength = 0l;
    private long[] startPos;//保留每个线程下载数据的起始位置。
    private long[] endPos;//保留每个线程下载数据的截止位置。

    private boolean bool = false;

    private URL url = null;

    //有参构造函数，先构造需要的数据
    public MultiTheradDownLoad(String filepath, int threadNum) {
        this.filepath = filepath;
        this.threadNum = threadNum;
        startPos = new long[this.threadNum];
        endPos = new long[this.threadNum];
        latch = new CountDownLatch(this.threadNum);
    }

    /*
     * 组织断点续传功能的方法
     */
    public void downloadPart() {

        File file = null;
        File tmpfile = null;
        HttpURLConnection httpcon = null;

        //在请求url内获取文件资源的名称；此处没考虑文件名为空的情况，此种情况可能需使用UUID来生成一个唯一数来代表文件名。
        filename = filepath.substring(filepath.lastIndexOf('/') + 1,
                filepath.contains("?") ? filepath.lastIndexOf('?') : filepath.length());
        tmpfilename = filename + "_tmp";

        try {
            url = new URL(filepath);
            httpcon = (HttpURLConnection) url.openConnection();
            setHeader(httpcon);
            fileLength = httpcon.getContentLengthLong();//获取请求资源的总长度。

            file = new File(filename);
            tmpfile = new File(tmpfilename);

            threadLength = fileLength / threadNum;//每个线程需下载的资源大小。
            System.out.println("fileName: " + filename + " ," + "fileLength= "
                    + fileLength + " the threadLength= " + threadLength);

            if (file.exists() && file.length() == fileLength) {
                System.out.println("the file you want to download has exited!!");
                return;
            } else {
                setBreakPoint(startPos, endPos, tmpfile);
                ExecutorService exec = Executors.newCachedThreadPool();
                for (int i = 0; i < threadNum; i++) {
                    exec.execute(new DownLoadThread(startPos[i], endPos[i],this, i, tmpfile, latch));
                }
                latch.await();//当你的计数器减为0之前，会在此处一直阻塞。
                exec.shutdown();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (file.length() == fileLength) {
            if (tmpfile.exists()) {
                System.out.println("delete the temp file!!");
                tmpfile.delete();
            }
        }
    }

    /*
     * 断点设置方法，当有临时文件时，直接在临时文件中读取上次下载中断时的断点位置。
     * 没有临时文件，即第一次下载时，重新设置断点。
     * rantmpfile.seek()跳转到一个位置的目的是为了让各个断点存储的位置尽量分开。
     * 这是实现断点续传的重要基础。
     */
    private void setBreakPoint(long[] startPos, long[] endPos, File tmpfile) {
        RandomAccessFile rantmpfile = null;
        try {
            if (tmpfile.exists()) {
                System.out.println("the download has continued!!");
                rantmpfile = new RandomAccessFile(tmpfile, "rw");
                for (int i = 0; i < threadNum; i++) {
                    rantmpfile.seek(8 * i + 8);
                    startPos[i] = rantmpfile.readLong();

                    rantmpfile.seek(8 * (i + 1000) + 16);
                    endPos[i] = rantmpfile.readLong();

                    System.out.println("the Array content in the exit file: ");
                    System.out.println("thre thread" + (i + 1) + " startPos:"+ startPos[i] + ", endPos: " + endPos[i]);
                }
            } else {
                System.out.println("the tmpfile is not available!!");
                rantmpfile = new RandomAccessFile(tmpfile, "rw");
                //最后一个线程的截止位置大小为请求资源的大小
                for (int i = 0; i < threadNum; i++) {
                    startPos[i] = threadLength * i;
                    if (i == threadNum - 1) {
                        endPos[i] = fileLength;
                    } else {
                        endPos[i] = threadLength * (i + 1) - 1;
                    }
                    rantmpfile.seek(8 * i + 8);
                    rantmpfile.writeLong(startPos[i]);

                    rantmpfile.seek(8 * (i + 1000) + 16);
                    rantmpfile.writeLong(endPos[i]);
                    System.out.println("the Array content: ");
                    System.out.println("thre thread" + (i + 1) + " startPos:" + startPos[i] + ", endPos: " + endPos[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rantmpfile != null) {
                    rantmpfile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 实现下载功能的内部类，通过读取断点来设置向服务器请求的数据区间。
     */
    class DownLoadThread implements Runnable {

        private long startPos;
        private long endPos;
        private MultiTheradDownLoad task = null;
        private RandomAccessFile downloadfile = null;
        private int id;
        private File tmpfile = null;
        private RandomAccessFile rantmpfile = null;
        private CountDownLatch latch = null;

        public DownLoadThread(long startPos, long endPos, MultiTheradDownLoad task, int id, File tmpfile,
                              CountDownLatch latch) {
            this.startPos = startPos;
            this.endPos = endPos;
            this.task = task;
            this.tmpfile = tmpfile;
            try {
                this.downloadfile = new RandomAccessFile(this.task.filename,"rw");
                this.rantmpfile = new RandomAccessFile(this.tmpfile, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {

            HttpURLConnection httpcon = null;
            InputStream is = null;
            int length = 0;
            System.out.println("the thread " + id + " has started!!");

            while (true) {
                try {
                    httpcon = (HttpURLConnection) task.url.openConnection();
                    setHeader(httpcon);

                    //防止网络阻塞，设置指定的超时时间；单位都是ms。超过指定时间，就会抛出异常
                    httpcon.setReadTimeout(20000);//读取数据的超时设置
                    httpcon.setConnectTimeout(20000);//连接的超时设置
                    if (startPos < endPos) {
                        //向服务器请求指定区间段的数据，这是实现断点续传的根本。
                        httpcon.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
                        System.out.println("Thread " + id + " the total size:---- " + (endPos - startPos));

                        downloadfile.seek(startPos);
                        //全部传输完成或者部分传输完成则断开当前连接
                        if (httpcon.getResponseCode() != HttpURLConnection.HTTP_OK
                                && httpcon.getResponseCode() != HttpURLConnection.HTTP_PARTIAL) {
                            this.task.bool = true;
                            httpcon.disconnect();
                            downloadfile.close();
                            System.out.println("the thread ---" + id + " has done!!");
                            latch.countDown();//计数器自减
                            break;
                        }
                        is = httpcon.getInputStream();//获取服务器返回的资源流
                        long count = 0l;
                        byte[] buf = new byte[1024];

                        while (!this.task.bool && (length = is.read(buf)) != -1) {
                            count += length;
                            downloadfile.write(buf, 0, length);
                            //不断更新每个线程下载资源的起始位置，并写入临时文件；为断点续传做准备
                            startPos += length;
                            rantmpfile.seek(8 * id + 8);
                            rantmpfile.writeLong(startPos);
                        }
                        System.out.println("the thread " + id + " total load count: " + count);

                        //关闭流
                        is.close();
                        httpcon.disconnect();
                        downloadfile.close();
                        rantmpfile.close();
                    }
                    latch.countDown();//计数器自减
                    System.out.println("the thread " + id + " has done!!");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /*
     * 为一个HttpURLConnection模拟请求头，伪装成一个浏览器发出的请求
     */
    private void setHeader(HttpURLConnection con) {
        con.setRequestProperty("User-Agent",
                "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");
        con.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
        con.setRequestProperty("Accept-Encoding", "aa");
        con.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        con.setRequestProperty("Keep-Alive", "300");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("If-Modified-Since", "Fri, 02 Jan 2009 17:00:05 GMT");
        con.setRequestProperty("If-None-Match", "\"1261d8-4290-df64d224\"");
        con.setRequestProperty("Cache-Control", "max-age=0");
        con.setRequestProperty("Referer","http://10.10.96.132:8088/fuMinOpenAccountByStep.htm");
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args){
        //String filepath = "http://127.0.0.1:8080/file/loadfile.mkv";
        //String filepath = "http://127.0.0.1:8080/doc/fumin/断点续传大文件.doc";
        String filepath = "http://10.10.96.132:8088/images/abandon.png";
        //String filepath = "http://10.10.96.132:8088/doc/fumin/富民电票极速贴现协议.pdf";
        MultiTheradDownLoad load = new MultiTheradDownLoad(filepath ,4);
        load.downloadPart();
    }

}
