package com.carcinema.cc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

public class LoginActivity extends AppCompatActivity {
    LinearLayout login_layout;
    Button naver_btn_logout;
    ImageView naver_login;

    OAuthLogin mOAuthLoginModule;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mContext = getApplicationContext();

        naver_login = (ImageView)findViewById(R.id.naver_login);
        naver_btn_logout = (Button) findViewById(R.id.naver_btn_logout);

        naver_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOAuthLoginModule = OAuthLogin.getInstance();
                mOAuthLoginModule.init(mContext,
                        getString(R.string.naver_client_id),
                        getString(R.string.naver_client_secret),
                        getString(R.string.naver_client_name));

                @SuppressLint("HandlerLeak")
                OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
                    @Override
                    public void run(boolean success) {
                        if (success) {
                            String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                            String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
                            long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
                            String tokenType = mOAuthLoginModule.getTokenType(mContext);

                            Log.i("LoginData", "accessToken : " + accessToken);
                            Log.i("LoginData", "refreshToken : " + refreshToken);
                            Log.i("LoginData", "expiresAt : " + expiresAt);
                            Log.i("LoginData", "tokenType : " + tokenType);

                            redirectSignupActivity();
                            Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();

                        } else {
                            String errorCode = mOAuthLoginModule
                                    .getLastErrorCode(mContext).getCode();
                            String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                            Toast.makeText(mContext, "errorCode:" + errorCode
                                    + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                        }
                    }

                    ;
                };

                mOAuthLoginModule.startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);
            }
        });

        naver_btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOAuthLoginModule.logout(mContext);
            }
        });
    }

    protected void redirectSignupActivity() {
        final Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();
    }
}