package comw.example.msi.wdshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comw.example.msi.wdshop.adapter.ShopMessageAdapter;
import comw.example.msi.wdshop.bean.CommodityListByLabel;
import comw.example.msi.wdshop.bean.MyEventBusBean;
import comw.example.msi.wdshop.mvp.Contacts;
import comw.example.msi.wdshop.mvp.MyInterface;
import comw.example.msi.wdshop.presenter.IPresenterImpl;

public class ShopMessageActivity<T> extends AppCompatActivity implements MyInterface.MyView<T> {

    @BindView(R.id.edit_inputname)
    EditText editInputname;
    @BindView(R.id.text_search)
    TextView textSearch;
    @BindView(R.id.shop_class)
    TextView shopClass;
    @BindView(R.id.xrecycler_view)
    XRecyclerView xrecyclerView;

    private ArrayList<CommodityListByLabel.ResultBean>mData=new ArrayList<>();
    private IPresenterImpl iPresenter;
    private ShopMessageAdapter shopMessageAdapter;

    private int labelId;
    private int page=1;
    private int count=5;
    private String classname=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_message);
        ButterKnife.bind(this);
        //EventBus注册
        EventBus.getDefault().register(this);
        iPresenter = new IPresenterImpl(this);
        startRequestDatas();
        shopClass.setText(classname);
        shopMessageAdapter = new ShopMessageAdapter(this, mData);
        xrecyclerView.setAdapter(shopMessageAdapter);
        xrecyclerView.setLayoutManager(new GridLayoutManager(this.getApplicationContext(), 2));
        xrecyclerView.setLoadingMoreEnabled(true);//开启上拉加载更多
        xrecyclerView.setPullRefreshEnabled(true);//开启下拉刷新
        //刷新，加载
        setListener();
        shopMessageAdapter.notifyDataSetChanged();
    }

    private void setListener() {
        xrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mData.clear();
                page=1;
                Map<String,String> headmap=new HashMap<>();
                Map<String,String> bodymap=new HashMap<>();
                bodymap.put("labelId",labelId+"");
                bodymap.put("page",page+"");
                bodymap.put("count",count+"");
                iPresenter.startRequestDataget(Contacts.BASE_CommodityListByLabel,headmap,bodymap,CommodityListByLabel.class);
                xrecyclerView.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                page++;
                Map<String,String> headmap=new HashMap<>();
                Map<String,String> bodymap=new HashMap<>();
                bodymap.put("labelId",labelId+"");
                bodymap.put("page",page+"");
                bodymap.put("count",count+"");
                iPresenter.startRequestDataget(Contacts.BASE_CommodityListByLabel,headmap,bodymap,CommodityListByLabel.class);
                xrecyclerView.refreshComplete();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getInfo(Object object){
        if (object instanceof MyEventBusBean){
            labelId= ((MyEventBusBean) object).getShopid();
            classname=((MyEventBusBean) object).getName();
        }
    }

    private void startRequestDatas() {
        Map<String,String> headmap=new HashMap<>();
        Map<String,String> bodymap=new HashMap<>();
        bodymap.put("labelId",labelId+"");
        bodymap.put("page",page+"");
        bodymap.put("count",count+"");
        iPresenter.startRequestDataget(Contacts.BASE_CommodityListByLabel,headmap,bodymap,CommodityListByLabel.class);
    }
    @OnClick({R.id.text_search, R.id.shop_class})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_search:
                break;
            case R.id.shop_class:
                break;
        }
    }

    @Override
    public void success(T data) {
        if (data instanceof CommodityListByLabel){
            CommodityListByLabel commodityListByLabel= (CommodityListByLabel) data;
            if (commodityListByLabel.getStatus().equals("0000")){
                mData.addAll(commodityListByLabel.getResult());
                shopMessageAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void error(T error) {
        Toast.makeText(this,error.toString(),Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销EventBus
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
