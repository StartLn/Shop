package comw.example.msi.wdshop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.xw.repo.XEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comw.example.msi.wdshop.bean.LoginBean;
import comw.example.msi.wdshop.mvp.Contacts;
import comw.example.msi.wdshop.mvp.MyInterface;
import comw.example.msi.wdshop.presenter.IPresenterImpl;

public class LoginActivity<T> extends AppCompatActivity implements MyInterface.MyView<T> {

    @BindView(R.id.xedit_username)
    XEditText xeditUsername;
    @BindView(R.id.xedit_password)
    XEditText xeditPassword;
    @BindView(R.id.check_remember)
    CheckBox checkRemember;
    @BindView(R.id.text_register)
    TextView textRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEdit;
    private IPresenterImpl iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //获取P层的对象
        iPresenter = new IPresenterImpl(this);
        mShared = getSharedPreferences("ln", MODE_PRIVATE);
        mEdit = mShared.edit();
        if (mShared.getBoolean("remember",false)){
            xeditUsername.setText(mShared.getString("mphone",""));
            xeditPassword.setText(mShared.getString("mpassword",""));
            checkRemember.setChecked(true);
        }
    }

    @OnClick({R.id.check_remember, R.id.text_register, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_remember:
                if(!checkRemember.isChecked()){
                    checkRemember.setChecked(false);
                }
                break;
            case R.id.text_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
                break;
            case R.id.btn_login:
                //获取输入的手机号和密码，并判断
                String mphone = xeditUsername.getText().toString().trim();
                String mpassword = xeditPassword.getText().toString().trim();
                if (mphone.equals("")||mpassword.equals("")){
                    Toast.makeText(this,"手机号和密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    Map<String,String> headmap=new HashMap<>();
                    Map<String,String> bodymap=new HashMap<>();
                    bodymap.put("phone",mphone);
                    bodymap.put("pwd",mpassword);
                    //开始请求数据
                    iPresenter.startRequestDatapost(Contacts.BASE_LOGIN,headmap,bodymap,LoginBean.class);
                }
                break;
        }
    }

    @Override
    public void success(T data) {
        LoginBean loginBean = (LoginBean) data;
        if (loginBean.getStatus().equals("0000")){
            int userId = loginBean.getResult().getUserId();
            String sessionId = loginBean.getResult().getSessionId();
            String mphone = xeditUsername.getText().toString().trim();
            String mpassword = xeditPassword.getText().toString().trim();
            //记住密码
            if (checkRemember.isChecked()){
                mEdit.putBoolean("remember",true);
                mEdit.putString("mphone",mphone);
                mEdit.putString("mpassword",mpassword);
            }else {
                mEdit.putBoolean("remember",false);
            }
            mEdit.commit();
            startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
            finish();
        }else{
            Toast.makeText(this,loginBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(T error) {
        String e= (String) error;
        Toast.makeText(this,"异常"+e,Toast.LENGTH_SHORT).show();
        Log.e("eee","异常"+e);
    }

    //优化MVP 处理内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iPresenter!=null){
            iPresenter.OnDetch();
            iPresenter=null;
        }
    }


};
