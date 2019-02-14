package comw.example.msi.wdshop.fragement;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import comw.example.msi.wdshop.R;
import comw.example.msi.wdshop.SubmitOrderActivity;
import comw.example.msi.wdshop.mvp.MyInterface;
import comw.example.msi.wdshop.presenter.IPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment implements MyInterface.MyView {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.check_all)
    CheckBox checkAll;
    @BindView(R.id.pricesum)
    TextView pricesum;
    @BindView(R.id.btn_close)
    Button btnClose;
    Unbinder unbinder;
    private IPresenterImpl iPresenter;
    private SharedPreferences sharedPreferences;

    /*"sessionId": "154276714558512",
"sex": 1,
"userId": 12
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        unbinder = ButterKnife.bind(this, view);
        iPresenter = new IPresenterImpl(this);
        /*sharedPreferences = getActivity().getSharedPreferences("ln", Context.MODE_PRIVATE);
        String userId = savedInstanceState.getString("userId", "");
        String sessionId = savedInstanceState.getString("sessionId", "");
        Toast.makeText(getActivity(),userId+"***"+sessionId+"",Toast.LENGTH_SHORT).show();*/
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.check_all, R.id.btn_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_all:
                break;
            case R.id.btn_close:
                startActivity(new Intent(getActivity().getApplicationContext(),SubmitOrderActivity.class));
                break;
        }
    }

    @Override
    public void success(Object data) {

    }

    @Override
    public void error(Object error) {

    }
}
