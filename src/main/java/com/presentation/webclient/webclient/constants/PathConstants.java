package com.presentation.webclient.webclient.constants;

public class PathConstants extends ApiServerConstants{
    public final static String WEB_CLIENT_GETOK=API_SERVER_ADDR+"/getdispacthok/{id}";
    public final static String WEB_CLIENT_GETFAIL=API_SERVER_ADDR+"/getdispacthfail";

    public final static String WEB_CLIENT_POSTOK=API_SERVER_ADDR+"/postdispatchok";
    public final static String WEB_CLIENT_POSTFAIL=API_SERVER_ADDR+"/postdispacthfail";

    public final static String WEB_CLIENT_PATCHOK=API_SERVER_ADDR+"/patchdispatchok";
    public final static String WEB_CLIENT_PATCHFAIL=API_SERVER_ADDR+"/patchdispatchfail";

    public final static String WEB_CLIENT_PUTOK=API_SERVER_ADDR+"/putdispatchok";
    public final static String WEB_CLIENT_PUTFAIL=API_SERVER_ADDR+"/putdispatchfail";

    public final static String WEB_CLIENT_DELETEOK=API_SERVER_ADDR+"/deletedispatchok/{id}";
    public final static String WEB_CLIENT_DELETEFAIL=API_SERVER_ADDR+"/deletedispatchfail";

}
