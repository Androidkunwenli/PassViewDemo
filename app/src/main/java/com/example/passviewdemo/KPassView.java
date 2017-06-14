package com.example.passviewdemo;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KPassView extends LinearLayout implements OnClickListener {

    private TextView[] tvList;
    private TextView[] tv;
    private ImageView iv_del;
    private View view;
    private String strPassword;     //输入的密码
    private int currentIndex = -1;    //用于记录当前输入密码格位置
    private TextView mTvForgotPassword, mTvDismiss;
    private LinearLayout mLlKeyboard;
    private OnPasswordInputFinish mListener;

    public KPassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KPassView(Context context) {
        super(context);
        view = View.inflate(context, R.layout.item_paypassword, null);
        mTvForgotPassword = (TextView) view.findViewById(R.id.tv_forgot_password);
        mTvDismiss = (TextView) view.findViewById(R.id.tv_dismiss);
        mLlKeyboard = (LinearLayout) view.findViewById(R.id.ll_keyboard);
        tvList = new TextView[6];
        tvList[0] = (TextView) view.findViewById(R.id.pay_box1);
        tvList[1] = (TextView) view.findViewById(R.id.pay_box2);
        tvList[2] = (TextView) view.findViewById(R.id.pay_box3);
        tvList[3] = (TextView) view.findViewById(R.id.pay_box4);
        tvList[4] = (TextView) view.findViewById(R.id.pay_box5);
        tvList[5] = (TextView) view.findViewById(R.id.pay_box6);
        tv = new TextView[10];
        tv[0] = (TextView) view.findViewById(R.id.pay_keyboard_zero);
        tv[1] = (TextView) view.findViewById(R.id.pay_keyboard_one);
        tv[2] = (TextView) view.findViewById(R.id.pay_keyboard_two);
        tv[3] = (TextView) view.findViewById(R.id.pay_keyboard_three);
        tv[4] = (TextView) view.findViewById(R.id.pay_keyboard_four);
        tv[5] = (TextView) view.findViewById(R.id.pay_keyboard_five);
        tv[6] = (TextView) view.findViewById(R.id.pay_keyboard_sex);
        tv[7] = (TextView) view.findViewById(R.id.pay_keyboard_seven);
        tv[8] = (TextView) view.findViewById(R.id.pay_keyboard_eight);
        tv[9] = (TextView) view.findViewById(R.id.pay_keyboard_nine);
        iv_del = (ImageView) view.findViewById(R.id.pay_keyboard_del);
        for (TextView aTv : tv) {
            aTv.setOnClickListener(this);
        }
        iv_del.setOnClickListener(this);
        mTvForgotPassword.setOnClickListener(this);
        mTvDismiss.setOnClickListener(this);
        addView(view);
    }

    //设置监听方法，在第6位输入完成后触发
    public void setOnFinishInput(final OnPasswordInputFinish pass) {
        tvList[5].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    strPassword = "";     //每次触发都要先将strPassword置空，再重新获取，避免由于输入删除再输入造成混乱
                    for (int i = 0; i < 6; i++) {
                        strPassword += tvList[i].getText().toString().trim();
                    }
                    //界面不可见
                    mLlKeyboard.setVisibility(INVISIBLE);
                    //清空密码框
                    deleteLey();
                    pass.inputFinish();    //接口中要实现的方法，完成密码输入完成后的响应逻辑
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_keyboard_one:
                getPass("1");
                break;
            case R.id.pay_keyboard_two:
                getPass("2");
                break;
            case R.id.pay_keyboard_three:
                getPass("3");
                break;
            case R.id.pay_keyboard_four:
                getPass("4");
                break;
            case R.id.pay_keyboard_five:
                getPass("5");
                break;
            case R.id.pay_keyboard_sex:
                getPass("6");
                break;
            case R.id.pay_keyboard_seven:
                getPass("7");
                break;
            case R.id.pay_keyboard_eight:
                getPass("8");
                break;
            case R.id.pay_keyboard_nine:
                getPass("9");
                break;
            case R.id.pay_keyboard_zero:
                getPass("0");
                break;
            case R.id.pay_keyboard_del:
                if (currentIndex - 1 >= -1) {      //判断是否删除完毕————要小心数组越界
                    tvList[currentIndex--].setText("");
                }
                break;
            default:
                // TODO Auto-generated method stub
                if (mListener != null) {
                    mListener.setOnItemClickListener(v);
                }
                break;
        }

    }

    public void getPass(String str) {
        if (currentIndex >= -1 && currentIndex < 5) {
            tvList[++currentIndex].setText(str);
        }
    }

    /**
     * 删除所有密码
     */
    public void deleteLey() {
        for (int i = currentIndex; i >= 0; i--) {
            if (currentIndex - 1 >= -1) {      //判断是否删除完毕————要小心数组越界
                tvList[currentIndex--].setText("");
            }
        }
    }

    //是否显示键盘
    public void showKey(boolean showKey) {
        if (showKey) {
            mLlKeyboard.setVisibility(VISIBLE);
        } else {
            mLlKeyboard.setVisibility(INVISIBLE);
        }
    }

    public String getStrPassword() {
        return strPassword;
    }

    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface OnPasswordInputFinish {
        //密码完成监听
        void inputFinish();

        //点击事件
        void setOnItemClickListener(View v);
    }

    public void setOnItemClickListener(OnPasswordInputFinish listener) {
        this.mListener = listener;
    }
}
