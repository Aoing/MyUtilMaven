package com.aoing.main;

import com.aoing.getPicImpl.GetPicImpl;
import com.aoing.getPicService.GetPicService;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        GetPicService pic = new GetPicImpl();
//        pic.getPic();

        //���������ϴ�ͼƬ�ķ���
        pic.getPicConcurrency(args);
    }
}
