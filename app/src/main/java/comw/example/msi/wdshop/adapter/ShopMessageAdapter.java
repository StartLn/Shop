package comw.example.msi.wdshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import comw.example.msi.wdshop.R;
import comw.example.msi.wdshop.bean.CommodityListByLabel;

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/10  19:54<p>
 * <p>更改时间：2019/1/10  19:54<p>
 * <p>版本号：1<p>
 */
public class ShopMessageAdapter extends RecyclerView.Adapter<ShopMessageAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CommodityListByLabel.ResultBean> mList;

    public ShopMessageAdapter(Context mContext, ArrayList<CommodityListByLabel.ResultBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shopmessage, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.shopImage.setImageURI(mList.get(i).getMasterPic());
        viewHolder.shopTitle.setText(mList.get(i).getCommodityName());
        viewHolder.shopPrice.setText("￥"+mList.get(i).getPrice());
        viewHolder.shopNumber.setText("已售"+mList.get(i).getSaleNum()+"件");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_image)
        SimpleDraweeView shopImage;
        @BindView(R.id.shop_title)
        TextView shopTitle;
        @BindView(R.id.shop_price)
        TextView shopPrice;
        @BindView(R.id.shop_number)
        TextView shopNumber;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
