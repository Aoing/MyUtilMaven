package com.aoing.upLoadPicService;

import java.io.IOException;

public interface UpLoadPicService {
    /**
     * 2019��11��30��14:38:04   �ϴ�ָ����Ƭ��������ָ��λ��
     * @throws IOException
     * @throws Exception
     */
    public void upLoadPic() throws IOException, Exception;

    public void upLoadPicConcurrency(String[] args) throws IOException;
}
