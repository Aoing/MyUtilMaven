package com.aoing.main;

import com.aoing.upLoadPicImpl.UpLoadPicImpl;
import com.aoing.upLoadPicService.UpLoadPicService;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        UpLoadPicService upLoadPicClientS = new UpLoadPicImpl();
//        upLoadPicClientS.upLoadPic();
        upLoadPicClientS.upLoadPicConcurrency(args);
    }
}
