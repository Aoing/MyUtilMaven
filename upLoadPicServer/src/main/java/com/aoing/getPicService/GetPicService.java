package com.aoing.getPicService;

import java.io.IOException;

public interface GetPicService {

    /**
     * 2019年12月1日14:54:40   单线程
     */

    public void getPic() throws IOException;

    /**
     * 2019年12月1日14:54:57  多线程并发
     */
    public void getPicConcurrency(String[] args) throws IOException;

}
