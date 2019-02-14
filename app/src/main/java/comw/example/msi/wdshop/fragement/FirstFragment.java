package comw.example.msi.wdshop.fragement;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import comw.example.msi.wdshop.Commodity_DetailsActivity;
import comw.example.msi.wdshop.R;
import comw.example.msi.wdshop.ShopMessageActivity;
import comw.example.msi.wdshop.adapter.MlssRecyclerAdapter;
import comw.example.msi.wdshop.adapter.PzshRecyclerAdapter;
import comw.example.msi.wdshop.adapter.RxxpRecyclerAdapter;
import comw.example.msi.wdshop.bean.CommodityList;
import comw.example.msi.wdshop.bean.MyEventBusBean;
import comw.example.msi.wdshop.bean.XBannerBean;
import comw.example.msi.wdshop.mvp.Contacts;
import comw.example.msi.wdshop.mvp.MyInterface;
import comw.example.msi.wdshop.presenter.IPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
/*首页*/
public class FirstFragment<T> extends Fragment implements MyInterface.MyView<T> {

    //控件id
    Unbinder unbinder;
    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.first_menu)
    ImageButton firstMenu;
    @BindView(R.id.btn_yellow)
    ImageView btnYellow;
    @BindView(R.id.recycler_new_product)
    RecyclerView recyclerNewProduct;
    @BindView(R.id.btn_purple)
    ImageView btnPurple;
    @BindView(R.id.recycler_fashion)
    RecyclerView recyclerFashion;
    @BindView(R.id.btn_pink)
    ImageView btnPink;
    @BindView(R.id.recycler_life)
    RecyclerView recyclerLife;
    @BindView(R.id.btn_search)
    ImageButton btnSearch;
    @BindView(R.id.rel_header)
    RelativeLayout relHeader;
    private IPresenterImpl iPresenter;
    //定义集合
    private ArrayList<XBannerBean.ResultBean>mXbanner=new ArrayList<>();
    private ArrayList<CommodityList.ResultBean.RxxpBean.CommodityListBean> mRxxp = new ArrayList<>();
    private ArrayList<CommodityList.ResultBean.MlssBean.CommodityListBeanXX> mMlss = new ArrayList<>();
    private ArrayList<CommodityList.ResultBean.PzshBean.CommodityListBeanX> mPzsh = new ArrayList<>();
    //适配器
    private RxxpRecyclerAdapter rxxpRecyclerAdapter;
    private MlssRecyclerAdapter mlssRecyclerAdapter;
    private PzshRecyclerAdapter pzshRecyclerAdapter;
    private CommodityList commodityList;

    /*public void setUserVisibleHint(boolean isVisibleToUser)*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            mXbanner.clear();
            mRxxp.clear();
            mMlss.clear();
            mPzsh.clear();
                iPresenter = new IPresenterImpl(this);
                //开始请求数据
                startRequest();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        unbinder = ButterKnife.bind(this, view);
        //注册Eventbus
        EventBus.getDefault().register(this);
        //获取P层对象
        iPresenter = new IPresenterImpl(this);
        //设置适配器
        setAdapters();
        //设置点击事件
        setListener();
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getInfo(Object object){

    }

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_first,new ShopMessageFragment());
            fragmentTransaction.addToBackStack("search").commit();
    }*/

   /*https://blog.csdn.net/weixin_41787393/article/details/81193827*/
    private void setListener() {
        rxxpRecyclerAdapter.setGoodsClick(new RxxpRecyclerAdapter.goodsClick() {
            @Override
            public void onGoodsClick(int position) {
                int mRxxpcommodityId = mRxxp.get(position).getCommodityId();
                //Toast.makeText(getActivity(),mRxxpcommodityId+"商品id",Toast.LENGTH_SHORT).show();
                //点击商品跳转到详情页
                startActivity(new Intent(getActivity(),Commodity_DetailsActivity.class));
            }
        });
        mlssRecyclerAdapter.setGoodsClick(new MlssRecyclerAdapter.goodsClick() {
            @Override
            public void onGoodsClick(int position) {
                int mMlsscommodityId = mMlss.get(position).getCommodityId();
                //Toast.makeText(getActivity(),mMlsscommodityId+"商品id",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),Commodity_DetailsActivity.class));
            }
        });
        pzshRecyclerAdapter.setGoodsClick(new PzshRecyclerAdapter.goodsClick() {
            @Override
            public void onGoodsClick(int position) {
                int mPzshcommodityId = mPzsh.get(position).getCommodityId();
                //Toast.makeText(getActivity(),mPzshcommodityId+"商品id",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),Commodity_DetailsActivity.class));
            }
        });
    }

    private void setAdapters() {
        //热销商品
        rxxpRecyclerAdapter = new RxxpRecyclerAdapter(getActivity().getApplicationContext(), mRxxp);
        recyclerNewProduct.setAdapter(rxxpRecyclerAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        recyclerNewProduct.setLayoutManager(layoutManager);
        rxxpRecyclerAdapter.notifyDataSetChanged();
        //魔力时尚
        mlssRecyclerAdapter = new MlssRecyclerAdapter(getActivity().getApplicationContext(), mMlss);
        recyclerFashion.setAdapter(mlssRecyclerAdapter);
        recyclerFashion.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mlssRecyclerAdapter.notifyDataSetChanged();
        //品质生活
        pzshRecyclerAdapter = new PzshRecyclerAdapter(getActivity().getApplicationContext(), mPzsh);
        recyclerLife.setAdapter(pzshRecyclerAdapter);
        GridLayoutManager layoutManagers = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerLife.setLayoutManager(layoutManagers);
        pzshRecyclerAdapter.notifyDataSetChanged();
    }

    private void startRequest() {
        Map<String,String> headmap=new HashMap<>();
        Map<String,String> bodymap=new HashMap<>();
        //开始请求数据
        iPresenter.startRequestDataget(Contacts.BASE_XBANNER,headmap, bodymap, XBannerBean.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        //eventbus注销
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    //获取数据成功
    @Override
    public void success(T data) {
        //Xbanner的实现
        if (data instanceof XBannerBean) {
            final XBannerBean xBannerBean = (XBannerBean) data;
            if (xBannerBean.getStatus().equals("0000")) {
                mXbanner.addAll(xBannerBean.getResult());
                xbanner.setData(mXbanner, null);
                xbanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(getActivity()).load(mXbanner.get(position).getImageUrl()).into((ImageView) view);
                    }
                });
                //开始请求商品列表放任数据
                Map<String,String> headmap=new HashMap<>();
                Map<String,String> bodymap=new HashMap<>();
                iPresenter.startRequestDataget(Contacts.BASE_CommodityList, headmap,bodymap, CommodityList.class);
            } else {
                Toast.makeText(getActivity(), ((XBannerBean) data).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        //recyclerview的实现
        if (data instanceof CommodityList) {
            commodityList = (CommodityList) data;
            //储存到不同的集合中
            List<CommodityList.ResultBean.RxxpBean.CommodityListBean> commodityList1 = commodityList.getResult().getRxxp().get(0).getCommodityList();
            List<CommodityList.ResultBean.MlssBean.CommodityListBeanXX> commodityList2 = commodityList.getResult().getMlss().get(0).getCommodityList();
            List<CommodityList.ResultBean.PzshBean.CommodityListBeanX> commodityList3 = commodityList.getResult().getPzsh().get(0).getCommodityList();
            if (commodityList.getStatus().equals("0000")) {
                mRxxp.addAll(commodityList1);
                mMlss.addAll(commodityList2);
                mPzsh.addAll(commodityList3);
                //刷新适配器
                rxxpRecyclerAdapter.notifyDataSetChanged();
                pzshRecyclerAdapter.notifyDataSetChanged();
                mlssRecyclerAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), ((CommodityList) data).getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    //获取数据失败
    @Override
    public void error(T error) {
        String e = (String) error;
        Toast.makeText(getActivity(), e, Toast.LENGTH_SHORT).show();
    }

    //优化MVP 处理内存泄漏
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (iPresenter != null) {
            iPresenter.OnDetch();
            iPresenter = null;
        }
    }

    //点击事件
    @OnClick({R.id.first_menu, R.id.btn_yellow, R.id.btn_purple, R.id.btn_pink, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.first_menu:
                //二级链表展示数据,m\n
                break;
            case R.id.btn_yellow:
                /*int mRxxpid = commodityList.getResult().getRxxp().get(0).getId();
                String mRxxpName=commodityList.getResult().getRxxp().get(0).getName();*/
                MyEventBusBean myEventBusBean = new MyEventBusBean();
                myEventBusBean.setShopid(commodityList.getResult().getRxxp().get(0).getId());
                myEventBusBean.setName(commodityList.getResult().getRxxp().get(0).getName());
                EventBus.getDefault().postSticky(myEventBusBean);
                startActivity(new Intent(getActivity(),ShopMessageActivity.class));
                break;
            case R.id.btn_purple:
                MyEventBusBean myEventBusBeann = new MyEventBusBean();
                myEventBusBeann.setShopid(commodityList.getResult().getMlss().get(0).getId());
                myEventBusBeann.setName(commodityList.getResult().getMlss().get(0).getName());
                EventBus.getDefault().postSticky(myEventBusBeann);
                startActivity(new Intent(getActivity(),ShopMessageActivity.class));
                break;
            case R.id.btn_pink:
                MyEventBusBean myEventBusBeans = new MyEventBusBean();
                myEventBusBeans.setShopid(commodityList.getResult().getPzsh().get(0).getId());
                myEventBusBeans.setName(commodityList.getResult().getPzsh().get(0).getName());
                EventBus.getDefault().postSticky(myEventBusBeans);
                startActivity(new Intent(getActivity(),ShopMessageActivity.class));
                break;
            case R.id.btn_search:
                startActivity(new Intent(getActivity(),ShopMessageActivity.class));
                break;
        }
    }
}
