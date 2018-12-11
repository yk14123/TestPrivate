package com.chinafocus.mytestanimation;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

import com.chinafocus.mytestanimation.bean.BookContentRawBean;
import com.chinafocus.mytestanimation.network.ApiManager;
import com.chinafocus.mytestanimation.utils.JMDESUtil;
import com.chinafocus.mytestanimation.utils.LogUtil;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ObjectAnimator mObjectAnimator, mObjectAnimator_big;
    private ImageView mIv_home;
    private boolean isHiding = true;
    private AlertDialog mAlertDialog;
    private String mKey;
    private String mText;
    private String mEncryString;
    private String mDecryString;
    private WebView mWvShelfWeb;
    private char[] mEncryCharArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        testDES();

        mIv_home = findViewById(R.id.iv_home);
//        testAnimator();

//        View mContentView = LayoutInflater.from(this).inflate(R.layout.bookshelf_dialog_shelf_copyright, null);
//        mAlertDialog = new AlertDialog.Builder(this)
//                .setView(mContentView)
//                .create();
//        mAlertDialog.setCancelable(true);
//        mAlertDialog.setCanceledOnTouchOutside(true);

        // 指定密匙
        mKey = "*()&^%$#";
        // 指定需要加密的明文
//        mText = "4454069u =o 5h6u= bopregkljoj";
//        mText = "awefawerawfadsfasdfasdfadsfasf";
        mText = "";
//        String mimeType = "text/html";
//        String enCoding = "utf-8";
////        mWvShelfWeb.loadData(mText, mimeType, enCoding);
//        mWvShelfWeb.loadDataWithBaseURL(null,mText,mimeType,enCoding,null);
//        testWebView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int intrinsicHeight = mIv_home.getDrawable().getIntrinsicHeight();
        int intrinsicWidth = mIv_home.getDrawable().getIntrinsicWidth();

        Log.i("MyLog", "intrinsicWidth -- >" + intrinsicWidth);
        Log.i("MyLog", "intrinsicHeight -- >" + intrinsicHeight);

        int height = mIv_home.getHeight();
        int width = mIv_home.getWidth();

        Log.i("MyLog", "============================");
        Log.i("MyLog", "height -- >" + height);
        Log.i("MyLog", "width -- >" + width);
    }

    @SuppressLint("CheckResult")
    private void testDES() {
        //https://book.expressreader.cn/api/shelves/3/categories/26/books/285/page/aes/Chapter2.xhtml#ebookNote_3
        Observable<String> bookContentAESDetail = ApiManager.getInstance().getService().getBookContentAESDetail("2", "16", "185", "chapter5.xhtml");

        bookContentAESDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        BookContentRawBean bookContentRawBean = new Gson().fromJson(s, BookContentRawBean.class);
                        BookContentRawBean.BookContentResultBean data = bookContentRawBean.getData();
                        String currentRaw = data.getCurrent();
                        LogUtil.veryLongForI("MyLog", "原始数据 --> " + currentRaw);


                        String decode = JMDESUtil.decode(currentRaw);
                        LogUtil.veryLongForI("MyLog", "=========================================");
                        LogUtil.veryLongForI("MyLog", "解密数据 --> " + decode);

                        String mimeType = "text/html";
                        String enCoding = "utf-8";
                        mWvShelfWeb.loadDataWithBaseURL(null, decode, mimeType, enCoding, null);
                    }
                });
    }

//    public void testClick(View v) {
////        if (!mAlertDialog.isShowing()) {
////            mAlertDialog.show();
////            WindowManager windowManager = getWindowManager();
////            Display display = windowManager.getDefaultDisplay();
////            Window window = mAlertDialog.getWindow();
////            if (window != null) {
////                WindowManager.LayoutParams lp = window.getAttributes();
////                lp.width = (int) (display.getWidth() * 0.7); //设置宽度
////                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//////              lp.dimAmount = 0f;
////                mAlertDialog.getWindow().setAttributes(lp);
////                window.setBackgroundDrawableResource(android.R.color.transparent);
////            }
////        }
//
//        switch (v.getId()) {
//            case R.id.btn_encode:
//                // 调用DES加密方法
//                try {
//                    mEncryString = JMDESUtil.encode(mText);
////                    mEncryCharArray = JMDESUtil.encode(mText);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                LogUtil.veryLongForI("MyLog", "DES加密结果：" + mEncryString);
//
//                break;
//
//            case R.id.btn_decode:
//                // 调用DES解密方法
//                mDecryString = null;
//                try {
//                    mDecryString = JMDESUtil.decode(mEncryString);
////                    mDecryString = DES.decryptDES(mEncryCharArray, mKey);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                LogUtil.veryLongForI("MyLog", "DES解密结果： " + mDecryString);
//                String mimeType = "text/html";
//                String enCoding = "utf-8";
////                mWvShelfWeb.loadData(mDecryString, mimeType, enCoding);
//                mWvShelfWeb.loadDataWithBaseURL(null, mDecryString, mimeType, enCoding, null);
//                break;
//        }
//    }


//    private void testWebView() {
//        mWvShelfWeb = findViewById(R.id.wv_book_shelf_web);
//
//        mWvShelfWeb.setVerticalScrollBarEnabled(false);//去掉垂直滚动条
//        mWvShelfWeb.setHorizontalScrollBarEnabled(false);//去掉横向滚动条
//        mWvShelfWeb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //去掉百边
//        //設置WebView默認的白色背景為透明色
//        mWvShelfWeb.setBackgroundColor(0);
//        mWvShelfWeb.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                handler.proceed();
//                super.onReceivedSslError(view, handler, error);
//            }
//        });
//        WebSettings webSettings = mWvShelfWeb.getSettings(); //声明WebSettings子类
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setDomStorageEnabled(true);  //设置适应Html5的一些方法
//        //设置自适应屏幕，两者合用
//        webSettings.setUseWideViewPort(true); //将图片调整到适合WebView的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        //缩放操作
//        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
//        webSettings.setTextZoom(400);   //设置显示的字体大小
//        //加载本地文件
////        mWvShelfWeb.loadUrl("file:///android_asset/bookcaseIntro.html");
//        String mimeType = "text/html";
//        String enCoding = "utf-8";
////        mWvShelfWeb.loadData(mText, mimeType, enCoding);
//        mWvShelfWeb.loadDataWithBaseURL(null, mText, mimeType, enCoding, null);
//
////        mWvShelfWeb.loadUrl("https://tieba.baidu.com/index.html");
//    }
}

//    private void testAnimator() {
//        //这里的属性随便写什么都可以。但是需要我们手动更新绘制
//        mObjectAnimator = ObjectAnimator.ofFloat(mIv_home, "scaleX", 1.0f, 0.0f);
////1.设置动画更新监听
//        mObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
////2.从监听器里面获取动画值
//                float animatedValue = (float) animation.getAnimatedValue();
//                mIv_home.setScaleX(animatedValue);//3.手动更新绘制
//                mIv_home.setScaleY(animatedValue);
//            }
//        });
//        mObjectAnimator.setDuration(1000);
//
//        //这里的属性随便写什么都可以。但是需要我们手动更新绘制
//        mObjectAnimator_big = ObjectAnimator.ofFloat(mIv_home, "scaleX", 0.0f, 1.0f);
////1.设置动画更新监听
//        mObjectAnimator_big.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
////2.从监听器里面获取动画值
//                float animatedValue = (float) animation.getAnimatedValue();
//                mIv_home.setScaleX(animatedValue);//3.手动更新绘制
//                mIv_home.setScaleY(animatedValue);
//            }
//        });
//        mObjectAnimator_big.setDuration(1000);
//    }
//
