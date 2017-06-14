package com.example.passviewdemo;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Created by 赵亚坤 on 2017/6/2.
 */

public class PassFragemnt extends PopupWindow implements KPassView.OnPasswordInputFinish, View.OnClickListener {

    private KPassView mView;
    private Activity mContext;
    private OnItemClickListener mListener;

    public PassFragemnt(final Activity context) {
        init(context);
        setPopupWindow();
    }

    private void init(Activity context) {
        mContext = context;
        mView = new KPassView(mContext);
        mView.setOnFinishInput(this);
        mView.setOnItemClickListener(this);
    }

    private void setPopupWindow() {
        this.setContentView(mView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        } else {
            this.dismiss();
        }
    }

    //输入密码完成监听
    @Override
    public void inputFinish() {
        Toast.makeText(mContext, mView.getStrPassword(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOnItemClickListener(View v) {
        switch (v.getId()) {
            case R.id.tv_forgot_password:
                Toast.makeText(mContext, "忘记密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_dismiss:
                this.dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.setOnItemClick(v);
        }
    }
}

