package com.zzn.wanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.zzn.wanandroid.R;
import com.zzn.wanandroid.ui.TestActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.pas_word)
    EditText pasWord;
    @Bind(R.id.checkbox)
    CheckBox checkbox;
    @Bind(R.id.login_but)
    Button loginBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        pasWord.setSelection(pasWord.getText().length());

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
                Intent intent = new Intent(new Intent(this, Main1Activity.class));
//                Intent intent = new Intent(new Intent(this, TestActivity.class));
                startActivity(intent);

                break;
        }
    }
}
