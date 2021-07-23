package com.carcinema.cc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<MyItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public MyItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        String name, address, phone;

        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.iv_img) ;
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name) ;
        TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_contents) ;
        LinearLayout tv = (LinearLayout)convertView.findViewById(R.id.tv);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        MyItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        iv_img.setImageDrawable(myItem.getIcon());
        tv_name.setText(myItem.getName());
        tv_contents.setText(myItem.getContents());

        ////구글맵 pos
        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        iv_img.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View v) {
                intent = new Intent(parent.getContext(), ThirdActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("pos", pos);//str에있는 값을 가져 가겠다
                context.startActivity(intent);
            }
        });

        final String[] ccName = {"장흥 자동차극장", "광릉수목원 자동차극장", "자유로 자동차극장", "양평자동차극장",
                "평택호 자동차극장", "포천 자동차극장", "용인 자동차극장", "퍼스트가든 자동차극장"};
        final String[] ccAddress = {"경기도 양주시 장흥면 권율로 120 (일영리)","경기도 포천시 소흘읍 죽엽산로 613 (직동리)",
                "경기도 파주시 탄현면 필승로 432 (성동리)", "경기도 양평군 양평읍 경강로 1704 (오빈리)",
                "경기도 평택시 현덕면 평택호길 159 (권관리)", "경기도 포천시 소흘읍 무림리 41",
                "경기 용인시 기흥구 보라동 308-1","경기도 파주시 탑삭골길 222 (조리읍)"};
        final String[] ccPhone = {"031-855-6050","031-543-1145","031-945-0609","031-773-7893","031-682-0410",
                "02-3431-0450","031-541-5442", "031-284-1098", "031-957-6861"};

        tv.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            String name, address, phone;
            @Override
            public void onClick(View view) {
                name = ccName[pos];
                address = ccAddress[pos];
                phone = ccPhone[pos];
                intent = new Intent(parent.getContext(), FourthActivity.class);
                intent.putExtra("name", name);//str에있는 값을 가져 가겠다
                intent.putExtra("address", address);//str에있는 값을 가져 가겠다
                intent.putExtra("phone", phone);//str에있는 값을 가져 가겠다
                intent.putExtra("pos",pos);
                context.startActivity(intent);
            }});

        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(Drawable img, String name, String contents) {

        MyItem mItem = new MyItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setIcon(img);
        mItem.setName(name);
        mItem.setContents(contents);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }
}