package com.yinshan.happycash.analytic.location;


import com.yinshan.happycash.dao.Location;

/**
 * Created by yyhuang on 2017/6/16  15:21.
 */

public interface LocationContract {

    int GPS_DISABLED = 0;
    int PERMISSION_GRANTED = 1;
    int PERMISSION_DENIED = - 1;

    void onReceived(Location bdLocation);
}
