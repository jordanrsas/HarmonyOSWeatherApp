package com.dtse.cjra.data.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UnsafeOkHttpClient {

    private static int CONNECTION_TIME_OUT = 60;

    public static OkHttpClient getUnsafeOkHttpClient() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(chain -> {
                Request request = chain.request();
                Request.Builder myBuilder = request.newBuilder();
                if (headers.size() > 0) {
                    for (Map.Entry<String, String> element : headers.entrySet()) {
                        myBuilder.addHeader(element.getKey(), element.getValue());
                    }
                }
                return chain.proceed(myBuilder.build());
            })
                    .readTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                    .addInterceptor(getInterceptor())
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true);

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
