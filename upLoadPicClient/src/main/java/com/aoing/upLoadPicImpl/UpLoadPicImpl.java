package com.aoing.upLoadPicImpl;

import com.aoing.upLoadPicService.UpLoadPicService;

import java.io.*;
import java.net.Socket;

public class UpLoadPicImpl implements UpLoadPicService{

    @Override
    public void upLoadPic() throws Exception {
        //��ȡsocket����
        Socket socket = new Socket("218.197.197.3", 10001);
        //��������������ȡͼƬ��
        FileInputStream fileInputStream = new FileInputStream("F:/a.jpg");
        //��ȡsocket�����������
        OutputStream outputStream = socket.getOutputStream();
        //�����ֽ����黺����
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(buf)) != -1){
            outputStream.write(buf, 0, len);
        }

        //���߷������Ѿ�д��������
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
            System.out.println("��ѡ��һ��jpd��ʽ��ͼƬ");
            return;
        }

        File file = new File(args[0]);

        if( !(file.exists() && (file.isFile()))){
            System.out.println("���ļ������ڣ����߲����ļ�����");
            return;
        }

        if (! file.getName().endsWith(".jpg")){
            System.out.println("���ļ�����jpg�ļ�");
            return;
        }

        if (file.length() > 1024*1024*5){
            System.out.println("�ļ����󣬴���5M");
            return;
        }

        //����socket
        Socket socket = new Socket("218.197.197.3", 10001);

        FileInputStream fileInputStream = new FileInputStream(file);
        //��ȡsocket�����
        OutputStream socketOutputStream = socket.getOutputStream();


        //����������
        byte[] buf = new byte[1024];
        int len = 0;
        while( (len = fileInputStream.read(buf)) != -1){
            socketOutputStream.write(buf, 0, len);
        }

        //���߷����������Ѿ�д��
        socket.shutdownOutput();

        InputStream socketInputStream = socket.getInputStream();
        //���������������ֽ������˴�ҪתΪ�ַ���
        InputStreamReader inputStreamReader = new InputStreamReader(socketInputStream, "UTF-8");

        char[] bufin = new char[1024];

        int l = 0;

        String backResult = null;
        while ((l = inputStreamReader.read(bufin)) != -1){
            backResult = new String(bufin, 0, l);
        }
        System.out.println("�ϴ������"+backResult);
//        System.out.println("�ϴ������"+new String(bufin, 0, l));

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
        System.out.println("�����"+s);
        bufferedReader1.close();
        socket.close();
    }
}
