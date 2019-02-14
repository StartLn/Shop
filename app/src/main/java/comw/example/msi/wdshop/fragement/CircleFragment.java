package comw.example.msi.wdshop.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import comw.example.msi.wdshop.R;
import comw.example.msi.wdshop.adapter.CircleXRecyclerAdapter;
import comw.example.msi.wdshop.bean.CircleListBean;
import comw.example.msi.wdshop.mvp.Contacts;
import comw.example.msi.wdshop.mvp.MyInterface;
import comw.example.msi.wdshop.presenter.IPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment<T> extends Fragment implements MyInterface.MyView<T>  {


    @BindView(R.id.xrecycler_view)
    XRecyclerView xrecyclerView;
    Unbinder unbinder;
    private ArrayList<CircleListBean.ResultBean> mCircle=new ArrayList<>();
    private IPresenterImpl iPresenter;
    private CircleXRecyclerAdapter circleXRecyclerAdapter;
    private int page=1;
    private int count=5;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            mCircle.clear();
            iPresenter = new IPresenterImpl(this);
            Map<String,String> headmap=new HashMap<>();
            Map<String,String> bodymap=new HashMap<>();
            bodymap.put("page",page+"");
            bodymap.put("count",count+"");
            iPresenter.startRequestDataget(Contacts.BASE_CircleList,headmap,bodymap,CircleListBean.class);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);

        circleXRecyclerAdapter = new CircleXRecyclerAdapter(getActivity(), mCircle);
        xrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        xrecyclerView.setAdapter(circleXRecyclerAdapter);
        xrecyclerView.setPullRefreshEnabled(true);//开启下拉刷新
        xrecyclerView.setLoadingMoreEnabled(true);//开启上拉加载更多
        //刷新，加载
        setListener();
        circleXRecyclerAdapter.notifyDataSetChanged();
        return view;
    }

    private void setListener() {
        xrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mCircle.clear();
                page=1;
                count=2;
                Map<String,String> headmap=new HashMap<>();
                Map<String,String> bodymap=new HashMap<>();
                bodymap.put("page",page+"");
                bodymap.put("count",count+"");
                iPresenter.startRequestDataget(Contacts.BASE_CircleList,headmap,bodymap,CircleListBean.class);
                circleXRecyclerAdapter.notifyDataSetChanged();
                xrecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                Map<String,String> headmap=new HashMap<>();
                Map<String,String> bodymap=new HashMap<>();
                bodymap.put("page",page+"");
                bodymap.put("count",count+"");
                iPresenter.startRequestDataget(Contacts.BASE_CircleList,headmap,bodymap,CircleListBean.class);
                circleXRecyclerAdapter.notifyDataSetChanged();
                xrecyclerView.refreshComplete();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(T data) {

        if (data instanceof CircleListBean){
            CircleListBean circleListBean = (CircleListBean) data;
            if (circleListBean.getStatus().equals("0000")){
                mCircle.addAll(circleListBean.getResult());
                circleXRecyclerAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void error(T error) {
        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();
    }
}
