package com.afc.event;

import com.baidu.location.BDLocation;

/**
 * Created by Administrator on 2016/5/5.
 */
public class LocationEvent {
    public LocationEvent(boolean success, BDLocation bdLocation) {
        this.success = success;
        this.bdLocation = bdLocation;
    }

    public boolean success;
    public BDLocation bdLocation;
}
