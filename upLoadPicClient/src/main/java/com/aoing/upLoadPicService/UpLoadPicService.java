package com.aoing.upLoadPicService;

import java.io.IOException;

public interface UpLoadPicService {
    /**
     * 2019年11月30日14:38:04   上传指定照片到服务器指定位置
     * @throws IOException
     * @throws Exception
     */
    public void upLoadPic() throws IOException, Exception;

    public void upLoadPicConcurrency(String[] args) throws IOException;
}
