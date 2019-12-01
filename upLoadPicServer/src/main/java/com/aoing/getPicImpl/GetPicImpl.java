package com.aoing.getPicImpl;

import com.aoing.PicThread;
import com.aoing.getPicService.GetPicService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GetPicImpl implements GetPicService {
    @Override
    public void getPic() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10007);
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("/home/aoing/Desktop/MyUtilDemo/upLoadPic/pic/A.png");
        byte[] buf = new byte[1024];
        int n = 0;
        while ((n = inputStream.read(buf)) != -1){
            fileOutputStream.write(buf, 0, n);
        }

        OutputStream outputStream = accept.getOutputStream();

        outputStream.write("上传成功了呢".getBytes());

        fileOutputStream.close();
        accept.close();
        serverSocket.close();
    }

    @Override
    public void getPicConcurrency(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10001);
        while(true){
            Socket socket = serverSocket.accept();
            new Thread(new PicThread(socket, args)).start();
        }
    }
}
