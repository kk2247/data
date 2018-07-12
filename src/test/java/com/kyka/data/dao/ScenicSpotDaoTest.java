package com.kyka.data.dao;

import com.kyka.data.entity.ScenicSpot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScenicSpotDaoTest {

    @Autowired
    private ScenicSpotDao scenicSpotDao;
    @Test
    public void deleteScenicSpot() {
        scenicSpotDao.deleteScenicSpot(17);
    }
}