package comw.example.msi.wdshop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comw.example.msi.wdshop.adapter.ListViewAdapter;
import comw.example.msi.wdshop.bean.FindCommodityDetailsByIdBean;
import comw.example.msi.wdshop.mvp.Contacts;
import comw.example.msi.wdshop.mvp.MyInterface;
import comw.example.msi.wdshop.presenter.IPresenterImpl;

public class Commodity_DetailsActivity<T> extends AppCompatActivity implements MyInterface.MyView<T> {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.btn_cart)
    ImageView btnCart;
    @BindView(R.id.btn_buy)
    ImageView btnBuy;
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    private boolean isInv=true;
    private IPresenterImpl iPresenter;
    private ArrayList<FindCommodityDetailsByIdBean.ResultBean>mList= new ArrayList<>();
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity__details);
        ButterKnife.bind(this);

        iPresenter = new IPresenterImpl(this);
        startRequestData();
        initView();
        initData();
        setListener();
    }

    private void initData() {
        listViewAdapter = new ListViewAdapter(this,mList);
        listView.setAdapter(listViewAdapter);
    }

    private void startRequestData() {
        Map<String,String> headmap=new HashMap<>();
        Map<String,String> bodymap=new HashMap<>();
        iPresenter.startRequestDataget(Contacts.BASE_CommodityDetailsById,headmap,bodymap,FindCommodityDetailsByIdBean.class);
    }

    private void initView() {
        for (int i = 0; i < 3; i++) {
            String text = null;
            switch (i) {
                case 0:
                    text = "商品";
                    break;
                case 1:
                    text = "详情";
                    break;
                case 2:
                    text = "评论";
                    break;
            }
            SpannableStringBuilder textC = new SpannableStringBuilder(text);
            textC.setSpan(new ForegroundColorSpan(Color.BLACK), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textC.setSpan(new AbsoluteSizeSpan(14, true), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tabLayout.addTab(tabLayout.newTab().setText(textC), i, i == 0);
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_cart, R.id.btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                break;
            case R.id.btn_cart:
                break;
            case R.id.btn_buy:
                break;
        }
    }

    private void setListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {

                if (isInv) {
                    return;
                }
                listView.post(new Runnable() {
                    @Override
                    public void run() {

                        int position = 0;
                        switch (tab.getPosition()) {
                            case 0:
                                position = 0;
                                break;
                            case 1:
                                position = 1;
                                break;
                            case 2:
                                // position = adapter.imgCount;
                                break;
                        }

                        listView.requestFocusFromTouch();//获取焦点
                        // 保存当前第一个可见的item的索引和偏移量
                       //int height = CommonUtils.dip2px(Commodity_DetailsActivity.this,
                        //PreferencesUtils.getInt(Commodity_DetailsActivity.this, AppConst.STATUS_BAR, 48));
                        //listView.setSelectionFromTop(position, height);
                    }
                });
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void success(T data) {
        if (data instanceof FindCommodityDetailsByIdBean){
            FindCommodityDetailsByIdBean findCommod= (FindCommodityDetailsByIdBean) data;
            Log.e("eee",findCommod.getMessage()+"");
            if (findCommod.getStatus().equals(0000)){
                mList.add(findCommod.getResult());
                listViewAdapter.notifyDataSetChanged();
            }else {
                Log.e("eee",findCommod.getMessage()+"");
            }
        }

    }

    @Override
    public void error(T error) {
        Log.e("eee",error+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /*
     * ListView滚动距离
     *//*
     *//*@Override
    public void scrollYDistance(AbsListView view, int distance, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        super.scrollYDistance(view, distance, firstVisibleItem, visibleItemCount, totalItemCount);

        //做通栏适配，title是覆盖在listview上面firstVisibleItem可能被title遮挡住不能显示，其实为第一个可见的item

        if (firstVisibleItem == 0 || firstVisibleItem == adapter.imgCount - 1) {
            if (listView.getChildAt(0).getBottom() < titleHeight) {
                return;
            }
        }        RecommendProductBean item = (RecommendProductBean) adapter.getItem(firstVisibleItem);
        //0:商品详情图片;  1：推荐item;  2：商品详情title;  3：推荐位title
        if (distance < Enums.SCREEN_HEIGHT_TO_PX - 100) {
            if (currentIndex == 0) {
                return;
            }
            currentIndex = 0;
        } else {
            Logger.e("item.getItemType()....  " + item.getItemType());

            switch (item.getItemType()) {
                case 0:
                case 2:
                    if (currentIndex == 1) {
                        return;
                    }
                    currentIndex = 1;
                    break;
                case 1:
                case 3:
                    if (currentIndex == 2) {
                        return;
                    }
                    currentIndex = 2;
                    break;
                default:
                    return;
            }
        }

        try {
            isInv = true;
            Class clz = tabLayout.getClass();
            Method animateToTab = clz.getDeclaredMethod("selectTab", new Class[]{TabLayout.Tab.class});
            animateToTab.setAccessible(true);
            animateToTab.invoke(tabLayout, new Object[]{tabLayout.getTabAt(currentIndex)});
            isInv = false;
        } catch (Exception e) {
            e.printStackTrace();
            isInv = false;
        }

    }*/
}
