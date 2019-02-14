package comw.example.msi.wdshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xw.repo.XEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comw.example.msi.wdshop.bean.RegisterBean;
import comw.example.msi.wdshop.mvp.MyInterface;
import comw.example.msi.wdshop.presenter.IPresenterImpl;

import static comw.example.msi.wdshop.mvp.Contacts.BASE_REGISTER;

public class RegisterActivity<T> extends AppCompatActivity implements MyInterface.MyView<T> {

    @BindView(R.id.xedit_phone)
    XEditText xeditPhone;
    @BindView(R.id.xedit_verify)
    XEditText xeditVerify;
    @BindView(R.id.get_verify)
    TextView getVerify;
    @BindView(R.id.xedit_password)
    XEditText xeditPassword;
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private IPresenterImpl iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //获取Presener的对象
        iPresenter = new IPresenterImpl(this);
    }

    @OnClick({R.id.get_verify, R.id.text_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_verify:

                break;
            case R.id.text_login:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
            case R.id.btn_register:
                //获取输入的手机号和密码，并判断
                String mphone = xeditPhone.getText().toString().trim();
                String mpassword = xeditPassword.getText().toString().trim();
                if (mphone.equals("")||mpassword.equals("")){
                    Toast.makeText(this,"手机号和密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    Map<String,String> headmap=new HashMap<>();
                    Map<String,String> bodymap=new HashMap<>();
                    bodymap.put("phone",mphone);
                    bodymap.put("pwd",mpassword);
                    //开始请求数据
                    iPresenter.startRequestDatapost(BASE_REGISTER,headmap,bodymap,RegisterBean.class);
                }
                break;
        }
    }

    //请求数据成功
    @Override
    public void success(T data) {
        RegisterBean registerBean= (RegisterBean) data;
        if (registerBean.getStatus().equals("0000")){
            Toast.makeText(this,registerBean.getMessage(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }else{
            Toast.makeText(this,registerBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    //请求数据失败
    @Override
    public void error(T error) {
        String e= (String) error;
        Toast.makeText(this,e,Toast.LENGTH_SHORT).show();
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
}
