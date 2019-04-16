package com.watson.apitest.socket;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/169:43 AM
 */
@Slf4j
@Setter
public class SocketTest {


    private String address;
    private int port;
    private int timeOut= 5 * 60 * 1000;
    private String encodeType="gbk";

    /**
     * 设置缓冲区大小
     * @param BUFFER_SIZE
     */
    public void setBUFFER_SIZE(int BUFFER_SIZE) {
        this.BUFFER_SIZE = BUFFER_SIZE;
    }

    private int BUFFER_SIZE=1024*1024;

    public SocketTest(String address, int port) {
        this.address=address;
        this.port=port;
    }
    public String  sendMessage(String message){
        Socket client = null;
        OutputStream os = null;
        InputStream is = null;
        BufferedReader bufferedReader = null;
        char[] data = new char[BUFFER_SIZE];
        try {
            client = new Socket();
            client.connect(new InetSocketAddress(InetAddress.getByName(address), port),timeOut);
            client.setSoTimeout(timeOut);
            os = new BufferedOutputStream(client.getOutputStream());
            is = new BufferedInputStream(client.getInputStream());
            // 发送报文
            os.write(message.getBytes(encodeType));
            os.flush();
            // 接收服务器的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), encodeType));
            in.read(data);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {

                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }

        return new String(data).trim();
    }



    public byte[]  sendMessage(byte[] bb,int byteSize){
        Socket client = null;
        OutputStream os = null;
        InputStream is = null;
        BufferedReader bufferedReader = null;
        byte[] data = new byte[byteSize];
        byte [] response=null;
        try {
            client = new Socket();
            client.connect(new InetSocketAddress(InetAddress.getByName(address), port),timeOut);
            client.setSoTimeout(timeOut);
            os = new BufferedOutputStream(client.getOutputStream());
            is = new BufferedInputStream(client.getInputStream());
            // 发送报文
            os.write(bb);
            os.flush();
            // 接收服务器的响应
            int read = client.getInputStream().read(data);
            response=new byte[read];
            System.arraycopy(data, 0, response, 0, read);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {

                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }

        return response;
    }
}
