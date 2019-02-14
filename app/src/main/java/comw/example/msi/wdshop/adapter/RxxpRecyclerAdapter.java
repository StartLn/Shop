package comw.example.msi.wdshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import comw.example.msi.wdshop.R;
import comw.example.msi.wdshop.bean.CommodityList;

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/7  14:39<p>
 * <p>更改时间：2019/1/7  14:39<p>
 * <p>版本号：1<p>
 */
public class RxxpRecyclerAdapter extends RecyclerView.Adapter<RxxpRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CommodityList.ResultBean.RxxpBean.CommodityListBean> mRxxp;

    public RxxpRecyclerAdapter(Context mContext, ArrayList<CommodityList.ResultBean.RxxpBean.CommodityListBean> mRxxp) {
        this.mContext = mContext;
        this.mRxxp = mRxxp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rxxp, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.rxxpImage.setImageURI(mRxxp.get(i).getMasterPic());
        viewHolder.rxxpTitle.setText(mRxxp.get(i).getCommodityName());
        viewHolder.rxxpPrice.setText("￥" + (int) mRxxp.get(i).getPrice()+".00");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoodsClick.onGoodsClick(i);
                //Toast.makeText(mContext, "点击了: " + mRxxp.get(i).getCommodityName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRxxp.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rxxp_image)
        SimpleDraweeView rxxpImage;
        @BindView(R.id.rxxp_title)
        TextView rxxpTitle;
        @BindView(R.id.rxxp_price)
        TextView rxxpPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private goodsClick mGoodsClick;
    public void setGoodsClick(goodsClick goodsClick) {
        mGoodsClick = goodsClick;
    }
    //自定义接口
    public interface goodsClick {
        void onGoodsClick(int position);
    }
}