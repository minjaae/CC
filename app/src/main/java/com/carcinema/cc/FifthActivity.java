package com.carcinema.cc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FifthActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private MapFragment mapFrag;
    private Marker marker;
    ListView foodListView;
    String name;
    Intent intent;
    ImageView login_btn;


    //장흥자동차극장
    private final String[] JHName = {"분식클라쓰", "cafeAU", "꼬꼬시티 장흥유원지점", "네네치킨 양주시장흥점", "네네치킨 송추점",
            "해피타임", "보스코커피", "박물관옆 카페"};
    private final String[] JHAddress = {"경기도 양주시 장흥면 일영리 41-4 그린숲타운 상가 105동 1층 102호", "경기도 양주시 장흥면 일영리 53-3 1", "경기도 양주시 일영리 장흥면 53-3", "경기 양주시 장흥면 일영리 145", "경기도 양주시 장흥면 부곡리 537-1", "경기도 양주시 장흥면 일영리 44-9", "경기 양주시 장흥면 일영리 41-4 그리숲타운 105동106호", "경기도 양주시 일영리 장흥면 34-1"};
    private final int[] JHKinds = {8, 4, 6, 6, 6, 4, 4, 4};
    private final double[] JHX = {37.72284319209867, 37.7236276350002, 37.7236276350002, 37.717014110222095, 37.7191294938009, 37.72415730267715, 37.72278378793907, 37.72253954574381};
    private final double[] JHY = {126.94761972602032, 126.94796447033892, 126.94796447033892, 126.94056839528335, 126.97752385666297, 126.94930052782696, 126.94763045480964, 126.94694552026974};

    //자유로자동차극장
    private final String[] JYName = {"춘천닭갈비", "회랑구이랑", "호식이두마리치킨", "파스타오빠", "능이족발", "카페 무즐라"};
    private final String[] JYAddress = {"파주점 경기도 파주시 탄현면 성동리 665-1 중앙프라자 103호,104호", "파주점 경기도 파주시 탄현면 성동리 667 나동 107호 108호", "호식이두마리치킨-통일동산점 경기도 파주시 탄현면 성동리 663-2 로드빌 106호", "경기도 파주시 탄현면 성동리 674 106호", "경기도 파주시 탄현면 성동리 667 다동 115, 116호", "경기 파주시 탄현면 성동로 7-22"};
    private final int[] JYKinds = {0, 3, 6, 2, 5, 4};
    private final double[] JYX = {37.78221427203783, 37.81217991131563, 37.78516860443726, 37.77932905599965, 37.77968111577005, 37.7801584742614};
    private final double[] JYY = {126.68815769528564, 126.68401197363212, 126.6899467836479, 126.68665775295797, 126.68595851063036, 126.68687479713905};

    //광릉수목원자동차극장
    private final String[] GRName = {"카페 목화포트", "숨", "카페 온다", "카페 두다트 광릉수목원점", "유앤미치킨"};
    private final String[] GRAddress = {"경기도 포천시 소흘읍 고모리 183", "경기도 포천시 소흘읍 죽엽산로 502-61", "경기도 포천시 소흘읍 고모리 714-1", "경기 포천시 소흘읍 고모리 190", "경기도 포천시 소흘읍 직동리 355-1"};
    private final int[] GRKinds = {4, 4, 4, 4, 6};
    private final double[] GRX = {37.79063297168716, 37.78790086225037, 37.793343113382804, 37.79353687817769, 37.772659891465736};
    private final double[] GRY = {127.16562859899317, 127.16376017015678, 127.16478626830337, 127.16516948179475, 127.1619436529577};

    //양평자동차극장
    private final String[] YPName = {"투썸플레이스 오빈점", "카페 오빈", "느낌표", "스타벅스 더양평DTR점", "짬뽕의달인",
            "고봉민김밥인 양평중앙점"};
    private final String[] YPAddress = {"경기 양평군 양평읍 경강로 1663", "경기 양평군 양평읍 오빈역길 21", "경기도 양평군 양평읍 오빈리 150-47", "경기 양평군 양평읍 양근로 76", "경기 양평군 양평읍 양근로 67", "경기 양평군 양평읍 양근리 422-25"};
    private final int[] YPKinds = {4, 4, 4, 4, 1, 8};
    private final double[] YPX = {37.504333384191355, 37.505109716544425, 37.499785497587624, 37.497467465700424, 37.49833611462555, 37.49136625819948};
    private final double[] YPY = {127.46668531062086, 127.47399494898322, 27.47622983945696, 127.48197484131049, 127.48191151247437, 127.48977136829296};

    //평택호자동차극장
    private final String[] PTHName = {"행복한터", "에즈파파", "토프레소 평택호점", "우경호프치킨", "무진장횟집"};
    private final String[] PTHAddress = {"경기 평택시 현덕면 평택호길 111-2", "경기 평택시 현덕면 평택호길 133-1", "경기 평택시 현덕면 평택호길 131", "경기 평택시 현덕면 평택호1길 10", "경기 평택시 현덕면 평택호길 9-4"};
    private final int[] PTHKinds = {4, 0, 4, 6, 3};
    private final double[] PTHX = {36.91907315134503, 36.920294918404615, 36.92006360905033, 36.91828491145153, 36.914533926213636
    };
    private final double[] PTHY = {126.9177402664198, 126.91981069710961, 126.9193448087473, 126.90749768176462, 126.90845095478188
    };

    //포천자동차극장
    private final String[] PCName = {"카페 살다", "카페바움", "도미노피자 의정부가능점", "스타벅스 포천DT점", "스타벅스 의정부금오점",
            "투앤디", "착한치킨"};
    private final String[] PCAddress = {"경기 포천시 소흘읍 광릉수목원로779번길 35-18", "경기 포천시 소흘읍 광릉수목원로 696", "경기 의정부시 평화로 640 한진그랑빌", "경기 포천시 호국로 925", "경기 의정부시 금오동 474-2 대송프라자", "경기 의정부시 호국로 1982", "경기 포천시 소흘읍 광릉수목원로 870 이곡리 468-6번지"};
    private final int[] PCKinds = {4, 4, 7, 4, 4, 4, 6};
    private final double[] PCX = {37.76917588951383, 37.773165287609736, 37.749274294949906, 37.84838718616646, 37.753187880189195, 37.77608634404267, 37.77784497955697};
    private final double[] PCY = {127.15180961062998, 127.16023222597508, 127.04532085481054, 127.16173428179658, 127.07046783179325, 127.11301330677998, 127.14754533761297};

    //용인자동차극장
    private final String[] YIName = {"공백 un vide","처갓집양념치킨 보라점","미소야 민속촌점","삼국지","청년피자 보라점","감탄떡볶이 용인보라점","신주닭발","교촌치킨 보라점","베스킨라빈스 용인보라점","BBQ 용인보라점","카페다"};
    private final String[] YIAddress = {"경기도 용인시 기흥구 보라동 660 상가b동 102호","경기도 용인시 기흥구 보라동 660 301동 104호","경기도 용인시 기흥구 보라동 140-7 신원프라자 101호","경기도 용인시 기흥구 보라동 596-4","경기도 용인시 기흥구 보라동 587-6 1층 102호","경기도 용인시 기흥구 보라동 578-1","경기도 용인시 기흥구 보라동 576 111호","경기도 용인시 기흥구 보라동 581-2","경기도 용인시 기흥구 보라동 573-6 스카이프라자 103호","경기도 용인시 기흥구 보라동 583-5 1층 101호","경기도 용인시 기흥구 보라동 142-5 1층 101,102호"};
    private final int[] YIKinds = {4,6,3,1,7,8,5,7,4,6,4};
    private final double[] YIX = {37.25509379461272, 37.25509379461272, 37.255283133922646, 37.25176379944158, 37.25355533999316, 37.25439088306424, 37.25351747916324, 37.25359438803226, 37.25395706919686, 37.253570979507835, 37.25558607062946};
    private final double[] YIY = {127.11456759526749, 127.11456759526749, 127.11821116828483, 127.113527241302, 127.11230838362982, 127.1098450660301, 127.10893332495652, 127.11029436781364, 127.1093783337986, 127.11085279921957, 127.11794682263132};

    //퍼스트가든자동차극장
    private final String[] FirstGardenName = {"오쇼짬뽕", "설문 커피", "소소함 카페", "카페떼오", "네네치킨 봉일천점", "파주돌짜장",
            "회싸나이"};
    private final String[] FirstGardenAddress = {"경기도 고양시 일산동구 설문동 705-6 가동 1층 전부호", "경기도 고양시 일산동구 설문동 고봉로 832", "경기도 고양시 일산동구 설문동 고봉로 778-7", "경기도 고양시 일산동구 고봉로 725", "경기 파주시 조리읍 봉일천리 156-1 성원상가 106호", "경기 파주시 운정로 129", "경기도 파주시 야당동 운정1길 84"};
    private final int[] FirstGardenKinds = {1, 4, 4, 4, 6, 1, 3};
    private final double[] FirstGardenX = {37.72508978489873, 37.726663999149935, 37.724647699461705, 37.72136701817153, 37.742461825068126, 37.721593576024105, 37.722605144647446};
    private final double[] FirstGardenY = {126.7932711511332, 126.79971061618666, 126.7944699952406, 126.78904246544451, 126.80652671960756, 126.78020556122917, 126.76616661370431};

    //자동차 극장
    final double[] ccX = {37.72365050737381,37.78044938456662,37.77847200350035,37.50247544538755,36.92111067461713,37.77603655899525,37.2576461405492,37.73166923477598};
    final double[] ccY = { 126.94989179769364, 127.16460511118768,126.68496225853572,127.47059929768693, 126.92173015587491, 127.13887039769533, 127.11440164000776, 126.79471459984927};
    final String[] ccName = {"장흥 자동차극장", "광릉수목원 자동차극장", "자유로 자동차극장", "양평자동차극장",
            "평택호 자동차극장", "포천 자동차극장", "용인 자동차극장", "퍼스트가든 자동차극장"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fifth);

        login_btn = (ImageView)findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FifthActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mapFrag = (MapFragment)getFragmentManager().findFragmentById(R.id.foodMap);
        mapFrag.getMapAsync(this);
        //주변 매장들
        foodListView = (ListView)findViewById(R.id.foodList);
        intent = getIntent();//받는다
        dataSetting();

//        //구글맵 스크롤으로부터 종속되지 않게
//        ivMapTransparent = (ImageView) findViewById(R.id.ivMapTransparent);
//        ivMapTransparent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                switch (action) {
//                    case MotionEvent.ACTION_DOWN:
//                        // Disallow ScrollView to intercept touch events.
//                        scrollView.requestDisallowInterceptTouchEvent(true);
//                        // Disable touch on transparent view
//                        return false;
//
//                    case MotionEvent.ACTION_UP:
//                        // Allow ScrollView to intercept touch events.
//                        scrollView.requestDisallowInterceptTouchEvent(false);
//                        return true;
//
//                    case MotionEvent.ACTION_MOVE:
//                        scrollView.requestDisallowInterceptTouchEvent(true);
//                        return false;
//
//                    default:
//                        return true;
//                }
//            }
//        });

        //scrollView = (ScrollView)findViewById(R.id.scroll);

        //스크롤뷰에 영향 안받도록
//        foodListView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //scrollView.requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });

        name = intent.getStringExtra("name");//String 데이타 받기


        switch (name){

            case "장흥 자동차극장":
                foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LatLng cclocation = new LatLng(JHX[i], JHY[i]); //마커 추가
                        marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                                .title(JHName[i]));
                        marker.showInfoWindow();

                    }
                });

                break;
            case "광릉수목원 자동차극장":
                foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LatLng cclocation = new LatLng(GRX[i], GRY[i]); //마커 추가
                        marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                                .title(GRName[i]));
                        marker.showInfoWindow();

                    }
                });
                break;
            case "자유로 자동차극장":
                foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LatLng cclocation = new LatLng(JYX[i], JYY[i]); //마커 추가
                        marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                                .title(JYName[i]));
                        marker.showInfoWindow();

                    }
                });
                break;
            case "양평자동차극장":
                foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LatLng cclocation = new LatLng(YPX[i], YPY[i]); //마커 추가
                        marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                                .title(YPName[i]));
                        marker.showInfoWindow();

                    }
                });
                break;
            case "평택호 자동차극장":
                foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LatLng cclocation = new LatLng(PTHX[i], PTHY[i]); //마커 추가
                        marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                                .title(PTHName[i]));
                        marker.showInfoWindow();

                    }
                });
                break;
            case "포천 자동차극장":
                foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LatLng cclocation = new LatLng(PCX[i], PCY[i]); //마커 추가
                        marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                                .title(PCName[i]));
                        marker.showInfoWindow();

                    }
                });
                break;
            case "용인 자동차극장":
                foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LatLng cclocation = new LatLng(YIX[i], YIY[i]); //마커 추가
                        marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                                .title(YIName[i]));
                        marker.showInfoWindow();

                    }
                });
                break;
            case "퍼스트가든 자동차극장":
                foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LatLng cclocation = new LatLng(FirstGardenX[i], FirstGardenY[i]); //마커 추가
                        marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                                .title(FirstGardenName[i]));
                        marker.showInfoWindow();

                    }
                });
                break;
        }

    }

    private void dataSetting() {
        int pos = intent.getIntExtra("pos", 0);
        FoodAdapter foodAdapter = new FoodAdapter();
        foodAdapter.setCCposition(pos);
        int[] FoodImageList = new int[]{R.drawable.korean, R.drawable.chinese, R.drawable.noodle, R.drawable.japanese, R.drawable.desert, R.drawable.night, R.drawable.chicken, R.drawable.pizza, R.drawable.bunsik};

        switch (pos) {
            case 0: //장흥자동차극장
                for (int i = 0; i < JHName.length; i++) {
                    foodAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.call), ContextCompat.getDrawable(getApplicationContext(), FoodImageList[JHKinds[i]]), JHName[i], JHAddress[i]);
                }
                break;
            case 1: //광릉수목원자동차극장
                for (int i = 0; i < GRName.length; i++) {
                    foodAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.call), ContextCompat.getDrawable(getApplicationContext(), FoodImageList[GRKinds[i]]), GRName[i], GRAddress[i]);
                }
                break;
            case 2://자유로 자동차극장
                for (int i = 0; i < JYName.length; i++) {
                    foodAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.call), ContextCompat.getDrawable(getApplicationContext(), FoodImageList[JYKinds[i]]), JYName[i], JYAddress[i]);
                }
                break;
            case 3: //양평자동차극장
                for (int i = 0; i < YPName.length; i++) {
                    foodAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.call), ContextCompat.getDrawable(getApplicationContext(), FoodImageList[YPKinds[i]]), YPName[i], YPAddress[i]);
                }
                break;
            case 4: //평택호자동차극장
                for (int i = 0; i < PTHName.length; i++) {
                    foodAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.call), ContextCompat.getDrawable(getApplicationContext(), FoodImageList[PTHKinds[i]]), PTHName[i], PTHAddress[i]);
                }
                break;
            case 5: //포천자동차극장
                for (int i = 0; i < PCName.length; i++) {
                    foodAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.call), ContextCompat.getDrawable(getApplicationContext(), FoodImageList[PCKinds[i]]), PCName[i], PCAddress[i]);
                }
                break;
            case 6://용인 자동차극장
                for (int i = 0; i < YIName.length; i++) {
                    foodAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.call), ContextCompat.getDrawable(getApplicationContext(), FoodImageList[YIKinds[i]]), YIName[i], YIAddress[i]);
                }
                break;
            case 7: //퍼스트가든
                for (int i = 0; i < FirstGardenName.length; i++) {
                    foodAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.call), ContextCompat.getDrawable(getApplicationContext(), FoodImageList[FirstGardenKinds[i]]), FirstGardenName[i], FirstGardenAddress[i]);
                }
                break;
        }
        foodListView.setAdapter(foodAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        oneMarker();
    }

    public void oneMarker(){

        LatLng cclocation;

        int pos = intent.getIntExtra("pos",0);
        name = intent.getStringExtra("name");//String 데이타 받기

        switch (name){
            case "장흥 자동차극장":
                for (int i = 0; i < JHName.length; i++) {
                    cclocation = new LatLng(JHX[i], JHY[i]); //마커 추가
                    marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                            .title(JHName[i]));
                    marker.showInfoWindow();
                }
                break;
            case "광릉수목원 자동차극장":
                for (int i = 0; i < GRName.length; i++) {
                    cclocation = new LatLng(GRX[i], GRY[i]); //마커 추가
                    marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                            .title(GRName[i]));
                    marker.showInfoWindow();
                }

                break;
            case "자유로 자동차극장":
                for (int i = 0; i < JYName.length; i++) {
                    cclocation = new LatLng(JYX[i], JYY[i]); //마커 추가
                    marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                            .title(JYName[i]));
                    marker.showInfoWindow();
                }

                break;
            case "양평자동차극장":
                for (int i = 0; i < YPName.length; i++) {
                    cclocation = new LatLng(YPX[i], YPY[i]); //마커 추가
                    marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                            .title(YPName[i]));
                    marker.showInfoWindow();
                }
                break;
            case "평택호 자동차극장":
                for (int i = 0; i < PTHName.length; i++) {
                    cclocation = new LatLng(PTHX[i], PTHY[i]); //마커 추가
                    marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                            .title(PTHName[i]));
                    marker.showInfoWindow();
                }

                break;
            case "포천 자동차극장":
                for (int i = 0; i < PCName.length; i++) {
                    cclocation = new LatLng(PCX[i], PCY[i]); //마커 추가
                    marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                            .title(PCName[i]));
                    marker.showInfoWindow();
                }
                break;
            case "용인 자동차극장":
                for (int i = 0; i < YIName.length; i++) {
                    cclocation = new LatLng(YIX[i], YIY[i]); //마커 추가
                    marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                            .title(YIName[i]));
                    marker.showInfoWindow();
                }
                break;
            case "퍼스트가든 자동차극장":
                for (int i = 0; i < FirstGardenName.length; i++) {
                    cclocation = new LatLng(FirstGardenX[i], FirstGardenY[i]); //마커 추가
                    marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                            .title(FirstGardenName[i]));
                    marker.showInfoWindow();
                }
                break;
        }
        cclocation = new LatLng(ccX[pos], ccY[pos]);
        marker = gMap.addMarker(new MarkerOptions().position(cclocation)
                .title(ccName[pos]));
        marker.showInfoWindow();



        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cclocation,14));
        gMap.getUiSettings().setZoomControlsEnabled(true);
    }
}