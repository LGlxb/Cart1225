package interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoginIntercepteor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        long t1 = System.nanoTime();//请求发起的时间
        Log.i("Yk", String.format("发送请求 %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        Log.i("aaa", String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                (t2 - t1) / 1e6d,
                response.headers()));

        return response;
    }
}
