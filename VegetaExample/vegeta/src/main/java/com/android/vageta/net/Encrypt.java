package com.android.vageta.net;


import com.android.vageta.util.EncryptUtil;

/**
 * Created by cocoa on 2015/10/20.16:16
 * email:385811416@qq.com
 * checkSum 的加密类，特殊的路径要改变packageKey，子类需要自行重写方法，以替换packageKey
 */
public class Encrypt {
    private String packageKey = "3bd679036267a732750921fe1904e003";

    public String getChecksum(String curTime, String pkgName, String clsName) {
        return EncryptUtil.md5(curTime + pkgName + clsName + packageKey);
    }

    public String getPackageKey() {
        return packageKey;
    }

    public void setPackageKey(String packageKey) {
        this.packageKey = packageKey;
    }
}
