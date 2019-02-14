package comw.example.msi.wdshop.presenter;

import java.util.Map;

import comw.example.msi.wdshop.molder.IMolderImpl;
import comw.example.msi.wdshop.mvp.MyInterface;

/**
 * <p>文件描述：Presenter层<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/3  16:48<p>
 * <p>更改时间：2019/1/3  16:48<p>
 * <p>版本号：1<p>
 */
public class IPresenterImpl implements MyInterface.IPresenter {
    private MyInterface.MyView myView;
    private IMolderImpl iMolder;

    public IPresenterImpl(MyInterface.MyView myView) {
        this.myView = myView;
        iMolder = new IMolderImpl();
    }

    @Override
    public void startRequestDataget(String url, Map<String, String> headmap, Map<String, String> bodymap, Class clazz) {
        iMolder.getData(url, headmap, bodymap, clazz, new MyInterface.MyCallBack() {
            @Override
            public void setData(Object data) {
                myView.success(data);
            }

            @Override
            public void setError(Object error) {
                myView.error(error);
            }
        });

    }

    @Override
    public void startRequestDatapost(String url, Map<String, String> headmap, Map<String, String> bodymap, Class clazz) {
        iMolder.postData(url, headmap, bodymap, clazz, new MyInterface.MyCallBack() {
            @Override
            public void setData(Object data) {
                myView.success(data);
            }

            @Override
            public void setError(Object error) {
                myView.error(error);
            }
        });
    }

    @Override
    public void startRequestDataput(String url, Map<String, String> headmap, Map<String, String> bodymap, Class clazz) {
        iMolder.putData(url, headmap, bodymap, clazz, new MyInterface.MyCallBack() {
            @Override
            public void setData(Object data) {
                myView.success(data);
            }

            @Override
            public void setError(Object error) {
                myView.error(error);
            }
        });
    }

    @Override
    public void startRequestDatadelete(String url, Map<String, String> headmap, Map<String, String> bodymap, Class clazz) {
        iMolder.deleteData(url, headmap, bodymap, clazz, new MyInterface.MyCallBack() {
            @Override
            public void setData(Object data) {
                myView.success(data);
            }

            @Override
            public void setError(Object error) {
                myView.error(error);
            }
        });
    }

    //MVP的优化  处理内存泄漏
    public void OnDetch(){
        if (myView!=null){
            myView=null;
        }
        if (iMolder!=null){
            iMolder=null;
        }
    }
}
