package com.huawei.algorithm.mock;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author king
 * @date 2022/7/23-0:25
 * @Desc
 */
public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * get请求
     *
     * @return
     */
    public static String doGet(String url) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            //发送get请求
            HttpGet request = new HttpGet(url);
            org.apache.http.HttpResponse response = client.execute(request);
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //读取返回数据
                String strResult = EntityUtils.toString(response.getEntity());
                return strResult;
            }
        } catch (IOException e) {
            log.error("httpUtils doGet error", e);
            return null;
        }
        return null;
    }

    /**
     * post请求
     *
     * @param url
     * @param params key-value格式
     * @return
     */
    public static String doPost(String url, Map params) {
        BufferedReader in = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            log.info("This result is {}", sb.toString());
            return sb.toString();
        } catch (Exception e) {
            log.error("httpUtils doPost error", e);
            return null;
        }
    }

    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params) {
        long start = System.currentTimeMillis();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(2000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

//            if (statusCode == HttpStatus.SC_OK) {
            HttpEntity responseEntity = response.getEntity();
            String jsonString = EntityUtils.toString(responseEntity);
            log.info("This result is {} , cost : {} ms", jsonString, System.currentTimeMillis() - start);
            return new String(jsonString.getBytes("ISO-8859-1"), "utf-8");
//            }else{
//                log.info("httpUtils doGet error ,code ：" + response.getEntity().getContent());
//            }
        } catch (Exception e) {
            log.error("httpUtils doPost error", e);
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }


    /**
     * 发送https请求，无参数携带
     */
    public static String doHttps(String reqUrl, String requestMethod) {
        return doHttps(reqUrl, null, requestMethod, null, null, 10000, 10000);
    }

    /**
     * 发送https请求，参数携带
     */
    public static String doHttps(String reqUrl, String param, String requestMethod) {
        return doHttps(reqUrl, param, requestMethod, null, null, 10000, 10000);
    }

    /**
     * 发送https请求，参数携带
     */
    public static String doHttps(String reqUrl, String param, String requestMethod, String contentType) {
        return doHttps(reqUrl, param, requestMethod, null, contentType == null ? "application/json" : contentType, 10000, 10000);
    }

    /**
     * 发送https请求，携带头部信息。无参数携带,
     */
    public static String doHttps(String reqUrl, String param, String requestMethod, List<Map<String, String>> header) {
        return doHttps(reqUrl, param, requestMethod, header, null, 10000, 10000);
    }

    /**
     * 发送https请求，无参数携带,但携带头部信息
     *
     * @param reqUrl
     * @param param         参数信息
     * @param requestMethod get，post 默认是get
     * @param header        请求头信息
     * @param connTimeout   请求超时时间 默认10秒
     * @param readTimeout   读取超时时间 默认10秒
     * @return
     */
    public static String doHttps(String reqUrl, String param, String requestMethod, List<Map<String, String>> header,
                                 String contentType, Integer connTimeout, Integer readTimeout) {
        URL url;
        InputStream inputStream = null;
        String result = "";
        OutputStream outputStream = null;
        try {
            url = new URL(reqUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = {xtm};
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);
            con.setSSLSocketFactory(ctx.getSocketFactory());
            if (header != null && header.size() != 0) {
                for (Map<String, String> map : header) {
                    String key = map.keySet().iterator().next();
                    con.setRequestProperty(map.keySet().iterator().next(), map.get(key));
                }
            }
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            // 发送POST请求必须设置为true
            con.setDoInput(true); //允许输入流，即允许下载
            con.setDoOutput(false);//默认为false.允许输出流 GET下需设置为false
            con.setRequestMethod("GET"); //使用get请求
            con.setUseCaches(false); //不使用缓冲
            // 设置请求超时时间和读取超时时间
            con.setConnectTimeout(connTimeout == null ? 10000 : connTimeout);
            con.setReadTimeout(readTimeout == null ? 10000 : readTimeout);
            // 设置通用的请求属性
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            if (contentType != null) {
                con.setRequestProperty("Content-type", contentType);
            }
            if ("POST".equals(requestMethod.toUpperCase())) {
                con.setDoOutput(true); //允许输出流，即允许上传
                con.setRequestMethod("POST");
            }
            if (param != null) {
                outputStream = con.getOutputStream();
                outputStream.write(param.getBytes());
                outputStream.flush();
            }
            inputStream = con.getInputStream();//获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = bufferReader.readLine()) != null) {
                result += inputLine + '\n';
            }
        } catch (Exception e) {
            log.info("httpUtils doHttps error ", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("", e);
            }
        }
        if (result.endsWith("\n")) {
            result = result.replace("\n", "");
        }
        return result;
    }

    static X509TrustManager xtm = new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }
    };

}
