package comw.example.msi.wdshop.units;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import comw.example.msi.wdshop.mvp.Contacts;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <p>文件描述：网络工具类<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/3  15:16<p>
 * <p>更改时间：2019/1/3  15:16<p>
 * <p>版本号：1<p>
 */
public class RetrofitUnits{
    private final MySeerviceApi mySeerviceApi;
    //单例模式
    private RetrofitUnits() {
        //okhttp+日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //setlevel用来设置日志打印的级别
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //初始化OkHttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //延时加载
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                //添加拦截器
                .addInterceptor(loggingInterceptor)
                //配置客户端
                .retryOnConnectionFailure(true)
                .build();
        //初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                //添加Gson解析器
                .addConverterFactory(GsonConverterFactory.create())
                //访问处理适配器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //添加网络前缀
                .baseUrl(Contacts.BASE_URLS)
                //添加网络访问工具类
                .client(okHttpClient)
                .build();
        //通过Retrofit创建  Api 调用请求方法方法
        mySeerviceApi = retrofit.create(MySeerviceApi.class);
    }

    public static RetrofitUnits getInstance(){
        return RetrofitHolder.units;
    }

    private static class RetrofitHolder{
        private static final RetrofitUnits units=new RetrofitUnits();
    }

    /*public RetrofitUnits get(String url,Map<String,String>bodymap){

        if (bodymap==null){
            bodymap=new HashMap<>();
        }
        //订阅者事件（处理网络请求）
        //Schedulers 线程调度器  指定Observable自身在哪个调度器上执行
        mySeerviceApi.get(url,bodymap)
                //subscribeOn(): 指定Observable(被观察者)所在的线程，或者叫做事件产生的线程。 子线程
                .subscribeOn(Schedulers.io())
                //observeOn(): 指定 Observer(观察者)所运行在的线程，或者叫做事件消费的线程。主线程
                .observeOn(AndroidSchedulers.mainThread())
                //订阅者
                .subscribe(observer);
        return RetrofitUnits.getInstance();
    }

    public RetrofitUnits post(String url,Map<String,String>bodymap){
        //订阅者事件（处理网络请求）
        if (bodymap==null){
            bodymap=new HashMap<>();
        }
        //Schedulers 线程调度器  指定Observable自身在哪个调度器上执行
        mySeerviceApi.posts(url,bodymap)
                //subscribeOn(): 指定Observable(被观察者)所在的线程，或者叫做事件产生的线程。 子线程
                .subscribeOn(Schedulers.io())
                //observeOn(): 指定 Observer(观察者)所运行在的线程，或者叫做事件消费的线程。主线程
                .observeOn(AndroidSchedulers.mainThread())
                //订阅者
                .subscribe(observer);
        return RetrofitUnits.getInstance();
    }*/
    //封装的Get和Post方式  构造者模式，调用方法，返回自己本身

    public RetrofitUnits get(String url, Map<String,String>headmap,Map<String,String>bodymap){
        if (headmap==null){
            headmap=new HashMap<>();
        }
        if (bodymap==null){
            bodymap=new HashMap<>();
        }
        //订阅者事件（处理网络请求）
        //Schedulers 线程调度器  指定Observable自身在哪个调度器上执行
        mySeerviceApi.gets(url,headmap,bodymap)
        //subscribeOn(): 指定Observable(被观察者)所在的线程，或者叫做事件产生的线程。 子线程
            .subscribeOn(Schedulers.io())
        //observeOn(): 指定 Observer(观察者)所运行在的线程，或者叫做事件消费的线程。主线程
            .observeOn(AndroidSchedulers.mainThread())
        //订阅者
            .subscribe(observer);
        return RetrofitUnits.getInstance();
    }

    public RetrofitUnits post(String url,  Map<String,String>headmap,Map<String,String>bodymap){
        //订阅者事件（处理网络请求）
        if (headmap==null){
            headmap=new HashMap<>();
        }
        if (bodymap==null){
            bodymap=new HashMap<>();
        }
        //Schedulers 线程调度器  指定Observable自身在哪个调度器上执行
        mySeerviceApi.posts(url,headmap,bodymap)
                //subscribeOn(): 指定Observable(被观察者)所在的线程，或者叫做事件产生的线程。 子线程
                .subscribeOn(Schedulers.io())
                //observeOn(): 指定 Observer(观察者)所运行在的线程，或者叫做事件消费的线程。主线程
                .observeOn(AndroidSchedulers.mainThread())
                //订阅者
                .subscribe(observer);
        return RetrofitUnits.getInstance();
    }

    public RetrofitUnits put(String url,Map<String,String>headmap,Map<String,String>bodymap){
        //订阅者事件（处理网络请求）
        if (headmap==null){
            headmap=new HashMap<>();
        }
        if (bodymap==null){
            bodymap=new HashMap<>();
        }
        //Schedulers 线程调度器  指定Observable自身在哪个调度器上执行
        mySeerviceApi.puts(url,headmap,bodymap)
                //subscribeOn(): 指定Observable(被观察者)所在的线程，或者叫做事件产生的线程。 子线程
                .subscribeOn(Schedulers.io())
                //observeOn(): 指定 Observer(观察者)所运行在的线程，或者叫做事件消费的线程。主线程
                .observeOn(AndroidSchedulers.mainThread())
                //订阅者
                .subscribe(observer);
        return RetrofitUnits.getInstance();
    }

    public RetrofitUnits delete(String url, Map<String,String>headmap,Map<String,String>bodymap){
        //订阅者事件（处理网络请求）
        if (headmap==null){
            headmap=new HashMap<>();
        }
        if (bodymap==null){
            bodymap=new HashMap<>();
        }
        //Schedulers 线程调度器  指定Observable自身在哪个调度器上执行
        mySeerviceApi.deletes(url,headmap,bodymap)
                //subscribeOn(): 指定Observable(被观察者)所在的线程，或者叫做事件产生的线程。 子线程
                .subscribeOn(Schedulers.io())
                //observeOn(): 指定 Observer(观察者)所运行在的线程，或者叫做事件消费的线程。主线程
                .observeOn(AndroidSchedulers.mainThread())
                //订阅者
                .subscribe(observer);
        return RetrofitUnits.getInstance();
    }

    //重写一个观察者  Rx的包
    private Observer observer=new Observer<ResponseBody>() {
        //暂停
        @Override
        public void onCompleted() {

        }
        //失败
        @Override
        public void onError(Throwable e) {
            if (httpListener!=null){
                httpListener.onError(e.getMessage());
            }
        }
        //成功
        @Override
        public void onNext(ResponseBody responseBody) {
            if (httpListener!=null){
                try {
                    httpListener.onSuccess(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    //接口回调，网络请求数据的成功与失败
    public interface HttpListener{
        //成功
        void onSuccess(String jsonStr);
        //失败
        void onError(String error);
    }

    public HttpListener httpListener;

    public void setHttpListener(HttpListener httpListener){
        this.httpListener=httpListener;
    }

}
