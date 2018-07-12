package com.kyka.data.dao;

import com.kyka.data.entity.Notice;

import java.util.List;

public interface NoticeDao {
    List<Notice> getNotices();
    int insertNotice(Notice notice);
}
