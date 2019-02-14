package comw.example.msi.wdshop.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import comw.example.msi.wdshop.R;
import comw.example.msi.wdshop.adapter.ShopMessageAdapter;
import comw.example.msi.wdshop.bean.CommodityListByLabel;
import comw.example.msi.wdshop.mvp.MyInterface;
import comw.example.msi.wdshop.presenter.IPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopMessageFragment<T> extends Fragment implements MyInterface.MyView {

    @BindView(R.id.edit_inputname)
    EditText editInputname;
    @BindView(R.id.text_search)
    TextView textSearch;
    @BindView(R.id.shop_class)
    TextView shopClass;
    @BindView(R.id.xrecycler_view)
    XRecyclerView xrecyclerView;
    Unbinder unbinder;
    private ArrayList<CommodityListByLabel.ResultBean>mData=new ArrayList<>();
    private IPresenterImpl iPresenter;
    private ShopMessageAdapter shopMessageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        iPresenter = new IPresenterImpl(this);
        shopMessageAdapter = new ShopMessageAdapter(getActivity(), mData);
        xrecyclerView.setAdapter(shopMessageAdapter);
        shopMessageAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void success(Object data) {
        if (data instanceof CommodityListByLabel){
            CommodityListByLabel commodityListByLabel= (CommodityListByLabel) data;
            if (commodityListByLabel.getStatus().equals("0000")){
                mData.addAll(commodityListByLabel.getResult());
                shopMessageAdapter.notifyDataSetChanged();
            }else{
                return;
            }
        }
    }

    @Override
    public void error(Object error) {
        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();
    }
}