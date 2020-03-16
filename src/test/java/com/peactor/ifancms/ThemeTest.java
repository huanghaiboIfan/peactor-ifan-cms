package com.peactor.ifancms;

import com.peactor.ifancms.beans.entity.Theme;
import com.peactor.ifancms.mapper.ThemeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description: TODO
 * @Author: Ifan
 * date: 2020-03-12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThemeTest {

    @Autowired
    private ThemeMapper themeMapper;

    @Test
    public void foo() {
        System.out.println("selectAll");
        List<Theme> themes = themeMapper.selectTest();
        System.out.println(themes);
    }
}
