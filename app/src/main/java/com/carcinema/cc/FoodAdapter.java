package com.carcinema.cc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter{
    int CCposition;
    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<FoodItem> mItems = new ArrayList<>();
    public void setCCposition(int position){
        CCposition = position;
    }
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public FoodItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //error
            convertView = inflater.inflate(R.layout.food_listview_custom, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        ImageView iv_img_phone = (ImageView) convertView.findViewById(R.id.iv_img_food) ;
        ImageView iv_img_food = (ImageView) convertView.findViewById(R.id.iv_img_phone) ;
//        ImageView iv_img_marker = (ImageView) convertView.findViewById(R.id.iv_img_marker) ;
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name) ;
        TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_contents) ;

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        FoodItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        iv_img_food.setImageDrawable(myItem.getFoodIcon());
        iv_img_phone.setImageDrawable(myItem.getIcon());
//        iv_img_marker.setImageDrawable(myItem.getIcon2());
        tv_name.setText(myItem.getName());
        tv_contents.setText(myItem.getContents());

//장흥자동차극장
        final String[] JHPhone = {"031-829-9894", "031-836-1120", "031-855-9222", "031-855-5474", "031-826-1934", "031-829-3888”", "0507-1326-6936", "031-855-5100"};
        //자유로자동차극장
        final String[] JYPhone = {"031-944-1020", "031- 957-0346", "031-944-9698", "", "050-7782-9202", "031-941-1363","0507-1490-0584"};
        //광릉수목원자동차극장
        final String[] GRPhone = {"031-542-0325", "031-542-1449", "070-4407-2300", "031-541-0190", "031-543-9285"};
//양평자동차극장
        final String[] YPPhone = {"031-772-7202", "070-4204-1914", "0507-1319-4193", "1522-3232", "0507-1320-6101", "031-772-3456"};

        //용인자동차극장
        final String[] YIPhone = {"031-261-8000","031-287-0804","031-274-4033","0504-308-2435","031-285-2326","031-8005-5494","031-282-8788","031-281-1992","031-693-8200","050-7744-9907","031- 283-8295"};
        //퍼스트가든
        final String[] FirstGardenPhone = {"031-976-8088", "031-975-5078", "070-8834-1220", "070-8802-1122", "031-947-4192", "031-944-4845", "031-941-6292"};
//평택호자동차극장
        final String[] PTHPhone = {"031-683-0833", "0507-1389-6616", "031-681-6974", "031-682-7726", "031-681-9532"};
        //포천자동차극장
        final String[] PCPhone = {"0507-1310-2508", "031-542-8969", "031-848-3082", "1522-3232", "1522-3232", "031-841-4138", "031-543-9288"};

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        //폰넘버
        iv_img_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent;
                switch(CCposition){
                    case 0:
                        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/" + JHPhone[pos]));
                        context.startActivity(mIntent);
                        break;
                    case 1:
                        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/" + GRPhone[pos]));
                        context.startActivity(mIntent);
                        break;
                    case 2:
                        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/" + JYPhone[pos]));
                        context.startActivity(mIntent);
                        break;
                    case 3: //양평자동차극장
                        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/" + YPPhone[pos]));
                        context.startActivity(mIntent);
                        break;
                    case 4: //평택호자동차극장
                        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/" + PTHPhone[pos]));
                        context.startActivity(mIntent);
                        break;
                    case 5: //포천자동차극장
                        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/" + PCPhone[pos]));
                        context.startActivity(mIntent);
                        break;
                    case 6:
                        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/" + YIPhone[pos]));
                        context.startActivity(mIntent);
                        break;
                    case 7:
                        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/" + FirstGardenPhone[pos]));
                        context.startActivity(mIntent);
                        break;
                }

            }
        });
        //구글맵 마커
//        iv_img_marker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "선택: "+tv_name.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(Drawable foodimg, Drawable img, String name, String contents) {

        FoodItem mItem = new FoodItem();

        /* MyItem에 아이템을 setting한다. */
        mItem.setFoodIcon(foodimg);
        mItem.setIcon(img);
//        mItem.setIcon2(img2);
        mItem.setName(name);
        mItem.setContents(contents);

        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }

}