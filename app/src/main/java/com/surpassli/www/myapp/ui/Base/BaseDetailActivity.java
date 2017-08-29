package com.surpassli.www.myapp.ui.Base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.common.DisplayUtil;
import com.surpassli.www.myapp.support.utils.common.ImageUtil;
import com.surpassli.www.myapp.view.base.BaseView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/8/11.
 * BaseDetailActivity
 */

public abstract class BaseDetailActivity extends BaseActivity implements BaseView {

    protected ProgressBar progressBar;
    protected ProgressBar progressBarTopPic;
    protected WebView contentView;
    protected ImageView topImage;
    protected boolean isShowBigPic = false;
    protected NestedScrollView scrollView;
    protected FrameLayout mainContent;
    protected Toolbar toolbar;
    protected ImageButton networkBtn;

    public abstract void onDataRefresh(); //刷新数据

    protected abstract void onEventComing(EventModel eventModel);  //EventBus绑定

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());   //让子类去绑定布局
        EventBus.getDefault().register(this);
    }

    protected int getLayoutID() {
        return R.layout.activity_base_detail;
    }

    //这个方法会在子类中执行
    @Override
    public void initView() {
        //对toolbar进行下移
        int height = DisplayUtil.getScreenHeight(InitApp.AppContext);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_bar);
        LinearLayout.LayoutParams llp = (LinearLayout.LayoutParams) ll.getLayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            llp.height = (int) (height * 0.03);
            ll.setLayoutParams(llp);
        }

        mainContent = (FrameLayout) findViewById(R.id.main_content);
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBarTopPic = (ProgressBar) findViewById(R.id.progressBarTopPic);
        topImage = (ImageView) findViewById(R.id.detail_top_image);
        networkBtn = (ImageButton) findViewById(R.id.networkBtn);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.top_gradient));
        contentView = (WebView) findViewById(R.id.content_view);

        contentView.getSettings().setJavaScriptEnabled(true);

        // 开启缓存
        contentView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //开启Dom Storage Api,可用于H5缓存数据，类似cookie但比cookie可存储的数据量大
        contentView.getSettings().setDomStorageEnabled(true);
        //支持本地存储
        contentView.getSettings().setDatabaseEnabled(true);

        contentView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoading();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                displayNetworkError();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                contentView.loadUrl(url);
                return false;
            }
        });

        if (isShowBigPic) {
            setBigPicConfig();
        }

        //网络异常就显示
        networkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkBtn.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                onDataRefresh();
            }
        });
        onDataRefresh();
    }

    @Override
    public void displayLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
            progressBarTopPic.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void displayNetworkError() {
        if (networkBtn != null) {
            networkBtn.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onEventMainThread(EventModel eventModel) {
        onEventComing(eventModel);
    }

    protected void setBigPicConfig() {
//        contentView.addJavascriptInterface(new JavascriptInterface(this), "imagelistener");
//        contentView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onLoadResource(WebView view, String url) {
//                super.onLoadResource(view, url);
//                // 监听器加载这是为了防止动态加载图片时新加载的图片无法预览
//                addImageClickListener();
//            }
//        });
    }

    /**
     * 设置布局背景，其实就是边缘空隙的颜色，颜色取自顶部图片的主色调
     *
     * @param url url
     */
    protected void setMainContentBg(String url) {
        HttpUtil.sendGetOkhttp(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        topImage.setBackground(new BitmapDrawable(getResources(), bitmap));
                        mainContent.setBackgroundColor(ImageUtil.getImageColor(bitmap));
                        progressBarTopPic.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }
}
