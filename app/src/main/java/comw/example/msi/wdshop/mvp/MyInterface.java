package comw.example.msi.wdshop.mvp;

import java.util.Map;

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/3  14:53<p>
 * <p>更改时间：2019/1/3  14:53<p>
 * <p>版本号：1<p>
 */
public interface MyInterface{
    interface MyCallBack<T>{
        void setData(T data);
        void setError(T error);
    }

    interface MyView<T>{
        void success(T data);
        void error(T error);
    }

    interface IModel{
        void getData(String url, Map<String,String>headmap, Map<String,String>bodymap,Class clazz,MyCallBack myCallBack);
        void postData(String url, Map<String,String>headmap, Map<String,String>bodymap,Class clazz,MyCallBack myCallBack);
        void putData(String url, Map<String,String>headmap, Map<String,String>bodymap,Class clazz,MyCallBack myCallBack);
        void deleteData(String url, Map<String,String>headmap, Map<String,String>bodymap,Class clazz,MyCallBack myCallBack);
    }

    interface IPresenter{
        void startRequestDataget(String url,Map<String,String>headmap, Map<String,String>bodymap,Class clazz);
        void startRequestDatapost(String url,Map<String,String>headmap, Map<String,String>bodymap,Class clazz);
        void startRequestDataput(String url,Map<String,String>headmap, Map<String,String>bodymap,Class clazz);
        void startRequestDatadelete(String url,Map<String,String>headmap, Map<String,String>bodymap,Class clazz);
    }
}
