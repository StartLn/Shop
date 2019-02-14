package comw.example.msi.wdshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import comw.example.msi.wdshop.R;
import comw.example.msi.wdshop.bean.FindCommodityDetailsByIdBean;

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/17  13:59<p>
 * <p>更改时间：2019/1/17  13:59<p>
 * <p>版本号：1<p>
 */
public class ListViewAdapter extends BaseAdapter {
    private final int ITEM_ONE = 0;
    private final int ITEM_TWO = 1;
    private final int ITEM_THREE = 2;
    private Context mContext;
    private ArrayList<FindCommodityDetailsByIdBean.ResultBean> mList;

    public ListViewAdapter(Context mContext, ArrayList<FindCommodityDetailsByIdBean.ResultBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderOne one = null;
        HolderTwo two = null;
        HolderThree three = null;
        int type = getItemViewType(position);
        switch (type) {
            case ITEM_ONE:
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.item_shop, null);
                    one.shopXbanner=convertView.findViewById(R.id.shop_xbanner);
                    one.shopPrice=convertView.findViewById(R.id.shop_price);
                    one.shopSale=convertView.findViewById(R.id.shop_sale);
                    one.shopName=convertView.findViewById(R.id.shop_name);
                    one.shopWeight=convertView.findViewById(R.id.shop_weight);
                    //通过key值区分到底是那个条目（HolderOne或者holderTwo）
                    convertView.setTag(one);
                } else {
                    one = (HolderOne) convertView.getTag();
                }
                final String[] split = mList.get(position).getPicture().split("\\,");
                final List<String> list_img = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    list_img.add(split[0]);
                }
                one.shopXbanner.setData(list_img, null);
                one.shopXbanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(mContext).load(list_img.get(position)).into((ImageView) view);
                    }
                });
                one.shopXbanner.setPageTransformer(Transformer.Default);
                one.shopPrice.setText(""+mList.get(position).getPrice()+"");
                one.shopName.setText(mList.get(position).getCategoryName());
                one.shopSale.setText(mList.get(position).getSaleNum());
                one.shopWeight.setText(mList.get(position).getWeight());
                break;
            case ITEM_TWO:

                break;
            case ITEM_THREE:

                break;
        }
        return convertView;
    }

    //返回条目的类型
    @Override
    public int getItemViewType(int position) {
        //比如正常的接口一般都是有字段判断的  data.getType =1
        if (position % 3 == 0) {
            return ITEM_ONE;
        } else if (position % 3 == 1) {
            return ITEM_TWO;
        } else {
            return ITEM_THREE;
        }
    }

    //到底有几张样式
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    static class HolderOne {
        @BindView(R.id.shop_xbanner)
        XBanner shopXbanner;
        @BindView(R.id.shop_price)
        TextView shopPrice;
        @BindView(R.id.shop_sale)
        TextView shopSale;
        @BindView(R.id.shop_name)
        TextView shopName;
        @BindView(R.id.shop_weight)
        TextView shopWeight;

        HolderOne(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class HolderTwo {
        @BindView(R.id.design_image)
        SimpleDraweeView designImage;
        @BindView(R.id.design_web)
        WebView designWeb;

        HolderTwo(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class HolderThree {
        @BindView(R.id.comment_num)
        TextView commentNum;
        @BindView(R.id.comment_headimage)
        SimpleDraweeView commentHeadimage;
        @BindView(R.id.comment_username)
        TextView commentUsername;
        @BindView(R.id.comment_date)
        TextView commentDate;
        @BindView(R.id.comment_context)
        TextView commentContext;
        @BindView(R.id.comment_images)
        SimpleDraweeView commentImages;

        HolderThree(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
