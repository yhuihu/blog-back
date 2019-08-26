package com.blog.blogBack.service.impl;


import com.blog.blogBack.dao.ReaderMapper;
import com.blog.blogBack.entity.Reader;
import com.blog.blogBack.framework.service.AbstractService;
import com.blog.blogBack.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.blog.blogBack.util.Encryption.verify;

/**
 * Created by CodeGenerator on 2019/07/07.
 */
@Service
@Transactional
public class ReaderServiceImpl extends AbstractService<Reader> implements ReaderService {
    @Autowired
    private ReaderMapper readerMapper;

    @Override
    public Reader Login(String username, String password) {
        Reader reader = new Reader();
        reader = readerMapper.GetDataBasePassword(username);
        if (reader != null) {
            if (!verify(password, reader.getPassword())) {
                reader = null;
            }
        }
        return reader;
    }

    @Override
    public Reader hasReaderName(String username) {
        return readerMapper.hasUserName(username);
    }

    @Override
    public Reader hasReaderEmail(String email) {
        return readerMapper.hasUserEmail(email);
    }

    @Override
    public Integer registerReader(Reader reader) {
        return readerMapper.insert(reader);
    }
}
