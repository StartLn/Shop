package comw.example.msi.wdshop.molder;

import com.google.gson.Gson;

import java.util.Map;

import comw.example.msi.wdshop.mvp.MyInterface;
import comw.example.msi.wdshop.units.RetrofitUnits;

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/3  16:37<p>
 * <p>更改时间：2019/1/3  16:37<p>
 * <p>版本号：1<p>
 */
public class IMolderImpl implements MyInterface.IModel {
    private MyInterface.MyCallBack myCallBacks;

    @Override
    public void getData(String url, Map<String, String> headmap, Map<String, String> bodymap, final Class clazz, final MyInterface.MyCallBack myCallBack) {
        this.myCallBacks=myCallBack;
        RetrofitUnits.getInstance().get(url,headmap,bodymap).setHttpListener(new RetrofitUnits.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                myCallBacks.setData(o);
            }

            @Override
            public void onError(String error) {
                myCallBacks.setError(error);
            }
        });
    }

    @Override
    public void postData(String url, Map<String, String> headmap, Map<String, String> bodymap, final Class clazz, final MyInterface.MyCallBack myCallBack) {
        this.myCallBacks=myCallBack;
        RetrofitUnits.getInstance().post(url,headmap,bodymap).setHttpListener(new RetrofitUnits.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                myCallBacks.setData(o);
            }

            @Override
            public void onError(String error) {
                myCallBacks.setError(error);
            }
        });
    }

    @Override
    public void putData(String url, Map<String, String> headmap, Map<String, String> bodymap, final Class clazz, final MyInterface.MyCallBack myCallBack) {
        this.myCallBacks=myCallBack;
        RetrofitUnits.getInstance().put(url,headmap,bodymap).setHttpListener(new RetrofitUnits.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                myCallBacks.setData(o);
            }

            @Override
            public void onError(String error) {
                myCallBacks.setError(error);
            }
        });
    }

    @Override
    public void deleteData(String url, Map<String, String> headmap, Map<String, String> bodymap, final Class clazz, final MyInterface.MyCallBack myCallBack) {
        this.myCallBacks=myCallBack;
       RetrofitUnits.getInstance().delete(url,headmap,bodymap).setHttpListener(new RetrofitUnits.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                myCallBacks.setData(o);
            }

            @Override
            public void onError(String error) {
                myCallBacks.setError(error);
            }
        });
    }
}
