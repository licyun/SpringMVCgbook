package com.licyun.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/9.
 */
@Component
public class GetDate {

    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String dataString = sdf.format(now);
        return dataString;
    }
}
