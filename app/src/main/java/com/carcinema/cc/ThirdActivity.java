package com.carcinema.cc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ThirdActivity extends AppCompatActivity implements OnMapReadyCallback{
    ImageView login_btn;
    private GoogleMap gMap;
    private Marker marker;
    MapFragment mapFrag;
    GroundOverlayOptions mark;
    ListView ccListView;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        login_btn = (ImageView)findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mapFrag = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
        ccListView=(ListView)findViewById(R.id.ccListView);
        dataSetting();
        intent = getIntent();//받는다

    }

    private void dataSetting(){
        MyAdapter cMyAdapter = new MyAdapter();

        final String[] ccName = {"장흥 자동차극장", "광릉수목원 자동차극장", "자유로 자동차극장", "양평자동차극장",
                "평택호 자동차극장", "포천 자동차극장", "용인 자동차극장", "퍼스트가든 자동차극장"};
        final String[] ccAddress = {"경기도 양주시 장흥면 권율로 120 (일영리)","경기도 포천시 소흘읍 죽엽산로 613 (직동리)",
                "경기도 파주시 탄현면 필승로 432 (성동리)", "경기도 양평군 양평읍 경강로 1704 (오빈리)",
                "경기도 평택시 현덕면 평택호길 159 (권관리)", "경기도 포천시 소흘읍 무림리 41",
                "경기 용인시 기흥구 보라동 308-1","경기도 파주시 탑삭골길 222 (조리읍)"};
        final String[] ccPhone = {"031-855-6050","031-543-1145","031-945-0609","031-773-7893","031-682-0410",
                "02-3431-0450","031-541-5442", "031-284-1098", "031-957-6861"};

        for(int i = 0 ; i < 8 ; i++){
            cMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.marker_small),ccName[i], ccAddress[i]);
        }
        ccListView.setAdapter(cMyAdapter);

    }
    
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        oneMarker();
    }
    
    public void oneMarker(){
        final double[] X = {37.72365050737381,37.78044938456662,37.77847200350035,37.50247544538755,36.92111067461713,37.77603655899525,37.2576461405492,37.73166923477598};
        final double[] Y = { 126.94989179769364, 127.16460511118768,126.68496225853572,127.47059929768693, 126.92173015587491, 127.13887039769533, 127.11440164000776, 126.79471459984927};
        final String[] ccName = {"장흥 자동차극장", "광릉수목원 자동차극장", "자유로 자동차극장", "양평자동차극장",
                  "평택호 자동차극장", "포천 자동차극장", "용인 자동차극장", "퍼스트가든 자동차극장"};

        int pos = intent.getIntExtra("pos",0);
        final LatLng cclocation = new LatLng(X[pos], Y[pos]); //마커 추가

        marker = gMap.addMarker(new MarkerOptions().position(cclocation)
        .title(ccName[pos]));
        marker.showInfoWindow();

        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cclocation,15));
        gMap.getUiSettings().setZoomControlsEnabled(true);
    }
}