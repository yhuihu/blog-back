package com.blog.blogBack.service;

import com.blog.blogBack.entity.Reader;
import com.blog.blogBack.framework.service.IService;

/**
 * Created by CodeGenerator on 2019/07/07.
 */
public interface ReaderService extends IService<Reader> {

    Reader Login(String username, String password);

    Reader hasReaderName(String username);

    Reader hasReaderEmail(String email);

    Integer registerReader(Reader reader);

}
