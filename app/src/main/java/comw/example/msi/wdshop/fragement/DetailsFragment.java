package comw.example.msi.wdshop.fragement;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import comw.example.msi.wdshop.R;
import comw.example.msi.wdshop.bean.CommodityListByLabel;
import comw.example.msi.wdshop.bean.MyEventBusBean;
import comw.example.msi.wdshop.mvp.Contacts;
import comw.example.msi.wdshop.presenter.IPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {


    @BindView(R.id.detalis_image)
    XBanner detalisImage;
    @BindView(R.id.detalis_price)
    TextView detalisPrice;
    @BindView(R.id.detalis_sale)
    TextView detalisSale;
    @BindView(R.id.price_lin)
    LinearLayout priceLin;
    @BindView(R.id.detalis_name)
    TextView detalisName;
    @BindView(R.id.detalis_weighnt)
    TextView detalisWeighnt;
    @BindView(R.id.detalis_web)
    WebView detalisWeb;
    @BindView(R.id.btn_cart)
    ImageView btnCart;
    @BindView(R.id.details_relative_addshoppingcar)
    RelativeLayout detailsRelativeAddshoppingcar;
    @BindView(R.id.btn_buy)
    ImageView btnBuy;
    @BindView(R.id.details_relative_pay)
    RelativeLayout detailsRelativePay;
    Unbinder unbinder;

    private ArrayList<String> datas = new ArrayList<>();
    private String sessionId;
    private int userId;
    private IPresenterImpl iPresenter;
    //private DetailsBean detailsBean;
    //private ShopCarBean shopCarBean;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        initData();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> headmap=new HashMap<>();
                Map<String,String> bodymap=new HashMap<>();
                iPresenter.startRequestDataget(Contacts.BASE_FindShoppingCart,headmap,bodymap,CommodityListByLabel.class);
                //presenter.getHeaderData(Contacts.sel_shopcar,userId,sessionId,null,ShopCarBean.class);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getInfo(Object object){
        if (object instanceof MyEventBusBean){
            userId= ((MyEventBusBean) object).getUserId();
            sessionId=((MyEventBusBean) object).getSessionId();
        }
    }

    private void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        //注销EventBus
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @OnClick({R.id.btn_cart, R.id.btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cart:
                break;
            case R.id.btn_buy:
                break;
        }
    }

}
