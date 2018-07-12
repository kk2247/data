package com.kyka.data.service.impl;

import com.kyka.data.dao.NoticeDao;
import com.kyka.data.entity.Notice;
import com.kyka.data.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public Notice getNotice() {
        List<Notice>notices=noticeDao.getNotices();
        return notices.get(0);
    }

    @Override
    public boolean insertNotice(Notice notice) {
        noticeDao.insertNotice(notice);
        return true;
    }
}
