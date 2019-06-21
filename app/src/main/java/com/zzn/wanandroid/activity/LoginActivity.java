package com.zzn.wanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.zzn.wanandroid.R;
import com.zzn.wanandroid.RetrofitConfig.ServiceFactory;
import com.zzn.wanandroid.bean.loginPostbean;
import com.zzn.wanandroid.bean.loginbean;
import com.zzn.wanandroid.ui.TestActivity;
import com.zzn.wanandroid.utils.ACache;
import com.zzn.wanandroid.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.pas_word)
    EditText pasWord;
    @Bind(R.id.checkbox)
    CheckBox checkbox;
    @Bind(R.id.login_but)
    Button loginBut;

    ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        aCache = ACache.get(this);
        pasWord.setSelection(pasWord.getText().length());

        String usrename = aCache.getAsString("username");
        String pws = aCache.getAsString("pws");

        if (!TextUtils.isEmpty(usrename) && !TextUtils.isEmpty(pws)) {
            userName.setText(usrename);
            pasWord.setText(pws);
        }

    }

    @OnClick({R.id.checkbox, R.id.login_but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkbox:

                if (checkbox.isChecked()) {
                    //选择状态 显示明文--设置为可见的密码
                    pasWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //光标在最后面
                    Editable etable = pasWord.getText();
                    Selection.setSelection(etable, pasWord.length());
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    pasWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //光标在最后面
                    Editable etable = pasWord.getText();
                    Selection.setSelection(etable, pasWord.length());
                }


                break;
            case R.id.login_but:

                final String name = userName.getText().toString().trim();
                final String pas = pasWord.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pas)) {
                    loginPostbean postbean = new loginPostbean();
                    postbean.setUsername(name);
                    postbean.setPassword(pas);
                    String route = new Gson().toJson(postbean);
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);


                    ServiceFactory.INSTANCE.getBusinessApi().getLogin(name,pas).enqueue(new Callback<loginbean>() {
                        @Override
                        public void onResponse(Call<loginbean> call, Response<loginbean> response) {
                            int code = response.code();

                            if (code == 200) {

                                aCache.put("username", name);
                                aCache.put("pws", pas);

                                Intent intent = new Intent(new Intent(LoginActivity.this, Main1Activity.class));
//                Intent intent = new Intent(new Intent(this, TestActivity.class));
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFailure(Call<loginbean> call, Throwable t) {

                        }
                    });

                } else {
                    ToastUtil.showToast(this, "用户名密码不能为空");
                }

//                Intent intent = new Intent(new Intent(this, Main1Activity.class));
////                Intent intent = new Intent(new Intent(this, TestActivity.class));
//                startActivity(intent);

                break;
        }
    }
}
