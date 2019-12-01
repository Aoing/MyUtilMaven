package com.aoing;

import java.io.*;
import java.net.Socket;

public class PicThread implements Runnable {
    private Socket socket;
    private String[] args;
    public PicThread(Socket socket, String[] args) {
        this.socket = socket;
        this.args = args;
    }

    @Override
    public void run() {
        int count = 1;
        String ip = socket.getInetAddress().getHostAddress();

        try {
            InputStream inputStream = socket.getInputStream();

            byte[] bufin = new byte[1024];

            File file = new File(args[0]+ip + ".jpg");
//            File file = new File("/home/aoing/Desktop/MyUtilDemo/upLoadPic/pic/"+ip + ".jpg");

            //�ж��ļ��Ƿ���ڣ�����Ѵ�����������
            while (file.exists()){
                file = new File(args[0]+ip +"(" + (count++) +")"+ ".jpg");
//                file = new File("/home/aoing/Desktop/MyUtilDemo/upLoadPic/pic/"+ip +"(" + (count++) +")"+ ".jpg");
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            int m = 0;

            while ((m = inputStream.read(bufin)) != -1){
                fileOutputStream.write(bufin);
            }

            //�ֽڴ���
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("�ϴ��ɹ�".getBytes());

//            OutputStream outputStream = socket.getOutputStream();
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
//            outputStreamWriter.write("�ϴ��ɹ�");

            fileOutputStream.close();

            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException(ip+"�ϴ�ʧ��");
        }
    }
}
