package com.aoing.upLoadPicImpl;

import com.aoing.upLoadPicService.UpLoadPicService;

import java.io.*;
import java.net.Socket;

public class UpLoadPicImpl implements UpLoadPicService{

    @Override
    public void upLoadPic() throws Exception {
        //获取socket对象
        Socket socket = new Socket("218.197.197.3", 10001);
        //创建输入流，获取图片流
        FileInputStream fileInputStream = new FileInputStream("F:/a.jpg");
        //获取socket的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //创建字节数组缓冲区
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(buf)) != -1){
            outputStream.write(buf, 0, len);
        }

        //告诉服务器已经写完数据了
        socket.shutdownOutput();

        InputStream socketInputStream = socket.getInputStream();

        byte[] bufIn = new byte[1024];

        int num = socketInputStream.read(bufIn);

        System.out.println(new String(bufIn, 0 , num));

        fileInputStream.close();
        socket.close();

    }

    @Override
    public void upLoadPicConcurrency(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("请选择一个jpd格式的图片");
            return;
        }

        File file = new File(args[0]);

        if( !(file.exists() && (file.isFile()))){
            System.out.println("该文件不存在，或者不是文件类型");
            return;
        }

        if (! file.getName().endsWith(".jpg")){
            System.out.println("改文件不是jpg文件");
            return;
        }

        if (file.length() > 1024*1024*5){
            System.out.println("文件过大，大于5M");
            return;
        }

        //创建socket
        Socket socket = new Socket("218.197.197.3", 10001);

        FileInputStream fileInputStream = new FileInputStream(file);
        //获取socket输出流
        OutputStream socketOutputStream = socket.getOutputStream();


        //创建缓冲区
        byte[] buf = new byte[1024];
        int len = 0;
        while( (len = fileInputStream.read(buf)) != -1){
            socketOutputStream.write(buf, 0, len);
        }

        //告诉服务器数据已经写完
        socket.shutdownOutput();

        InputStream socketInputStream = socket.getInputStream();
        //服务器传过来的字节流，此处要转为字符流
        InputStreamReader inputStreamReader = new InputStreamReader(socketInputStream, "UTF-8");

        char[] bufin = new char[1024];

        int l = 0;

        String backResult = null;
        while ((l = inputStreamReader.read(bufin)) != -1){
            backResult = new String(bufin, 0, l);
        }
        System.out.println("上传结果："+backResult);
//        System.out.println("上传结果："+new String(bufin, 0, l));

        fileInputStream.close();
        socket.close();
    }


    public void upFile() throws IOException {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 10006);
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader("upLoad.txt"));

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String line = null;

        while((line = bufferedReader.readLine()) != null){
            out.println(line);
        }

        socket.shutdownInput();

        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String s = bufferedReader1.readLine();
        System.out.println("结果："+s);
        bufferedReader1.close();
        socket.close();
    }
}
