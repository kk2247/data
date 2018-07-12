package com.kyka.data.service;

import com.kyka.data.entity.Notice;

public interface NoticeService {
    Notice getNotice();
    boolean insertNotice(Notice notice);

}
