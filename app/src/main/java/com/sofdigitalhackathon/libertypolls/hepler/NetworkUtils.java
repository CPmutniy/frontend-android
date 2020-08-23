package com.sofdigitalhackathon.libertypolls.hepler;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class NetworkUtils {
    public static final String MEDIA_TYPE_PLAIN = "text/plain";
    public static final String MEDIA_TYPE_BINARY = "application/octet-stream";

    public static RequestBody WrapStringToRequestBody(String MIMEtype, String value) {
        return RequestBody.create(MediaType.parse(MIMEtype), value);
    }

    public static RequestBody WrapByteArrayToRequestBody(String MIMEtype, byte[] value) {
        return RequestBody.create(MediaType.parse(MIMEtype), value);
    }

    public static RequestBody WrapIntToRequestBody(String MIMEtype, int value) {
        return RequestBody.create(MediaType.parse(MIMEtype), String.valueOf(value));
    }

    public static RequestBody WrapDoubleToRequestBody(String MIMEtype, double value) {
        return RequestBody.create(MediaType.parse(MIMEtype), String.valueOf(value));
    }
}
