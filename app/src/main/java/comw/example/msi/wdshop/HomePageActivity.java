package comw.example.msi.wdshop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import comw.example.msi.wdshop.adapter.MyFragementAdapter;
import comw.example.msi.wdshop.custom.CustomViewPage;
import comw.example.msi.wdshop.fragement.CircleFragment;
import comw.example.msi.wdshop.fragement.FirstFragment;
import comw.example.msi.wdshop.fragement.OrderFormFragment;
import comw.example.msi.wdshop.fragement.PersonageFragment;
import comw.example.msi.wdshop.fragement.ShoppingFragment;

public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.view_pager)
    CustomViewPage viewPager;
    private ArrayList<Fragment> mFragment;
    private MyFragementAdapter myFragementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        //得到fragement页面
        initData();
        //设置适配器
        myFragementAdapter = new MyFragementAdapter(getSupportFragmentManager(), mFragment);
        viewPager.setAdapter(myFragementAdapter);
        //联动，点击按钮跳转对应的Dragment页面
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.page_first) {
                    viewPager.setCurrentItem(0);
                } else if (checkedId == R.id.page_circle) {
                    viewPager.setCurrentItem(1);
                } else if (checkedId == R.id.page_shopping_cart) {
                    viewPager.setCurrentItem(2);
                } else if (checkedId == R.id.page_order_form) {
                    viewPager.setCurrentItem(3);
                } else if (checkedId == R.id.page_personage) {
                    viewPager.setCurrentItem(4);
                }
            }
        });
    }
    private void initData() {
        //添加fragement
        mFragment = new ArrayList<>();
        mFragment.add(new FirstFragment());
        mFragment.add(new CircleFragment());
        mFragment.add(new ShoppingFragment());
        mFragment.add(new OrderFormFragment());
        mFragment.add(new PersonageFragment());
    }
}
