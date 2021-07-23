package com.carcinema.cc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {

    WebView mWebView;
    WebSettings mWebSettings;
    TextView storName;
    View dialogView;
    TextView num_dialog, address_dialog, nextTakeout;
    Intent intent;
    String name;
    ImageView login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth);

        login_btn = (ImageView)findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FourthActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mWebView = (WebView)findViewById(R.id.timeTable);

        //웹뷰 설정들
        mWebView.setWebViewClient(new WebViewClient());
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setSupportZoom(false);
        mWebSettings.setBuiltInZoomControls(false);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setDomStorageEnabled(true);

        storName = (TextView)findViewById(R.id.storeName);
        intent = getIntent();//받는다
        name = intent.getStringExtra("name");//String 데이타 받기
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phone");
        int pos = intent.getIntExtra("pos", 0);
        storName.setText(name);//받아 온 데이터 tv_second 에 넣기

        nextTakeout = (TextView)findViewById(R.id.nextTakeout);

        nextTakeout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FourthActivity.this, FifthActivity.class);
                intent.putExtra("name", name);//str에있는 값을 가져 가겠다
                intent.putExtra("pos",pos);
                startActivity(intent);
                mWebView.goBack();
            }
        });

        switch (name){

            case "장흥 자동차극장":
                mWebView.loadUrl("http://driveinmovie.co.kr/");
                break;
            case "광릉수목원 자동차극장":
                mWebView.loadUrl("http://sumokmovie.com/");
                break;
            case "자유로 자동차극장":
                mWebView.loadUrl("http://www.carmovie.co.kr/");
                break;
            case "양평자동차극장":
                mWebView.loadUrl("http://ypdit.co.kr/");
                break;
            case "평택호 자동차극장":
                mWebView.loadUrl("http://www.ptcarmovie.com/");
                break;
            case "포천 자동차극장":
                mWebView.loadUrl("http://www.pocheoncarmovie.co.kr/");
                break;
            case "용인 자동차극장":
                mWebView.loadUrl("http://www.drivemovie.co.kr/");
                break;
            case "퍼스트가든 자동차극장":
                mWebView.loadUrl("http://www.firstgarden.co.kr/fg/cartheater");
                break;
        }

        //i버튼 누르면 매장 전화번호 뜨는 다이얼로그
        final ImageButton storeInfo = (ImageButton)findViewById(R.id.storeInfo);
        storeInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialogView = (View)View.inflate(FourthActivity.this, R.layout.info_dial, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(FourthActivity.this);
                address_dialog = (TextView)dialogView.findViewById(R.id.address_dialog);
                num_dialog = (TextView)dialogView.findViewById(R.id.num_dialog);
                dlg.setView(dialogView);
                address_dialog.setText(address);
                num_dialog.setText(phone);
                //전화번호 클릭하면 전화다이얼 화면으로 전환
                num_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/" + phone));
                        startActivity(mIntent);
                    }
                });
                dlg.show();
            }
        });

    }

    //뒤로 가기 눌렀을 때 웹뷰에서 작동하도록
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }else{
            super.onBackPressed();
        }
    }




}