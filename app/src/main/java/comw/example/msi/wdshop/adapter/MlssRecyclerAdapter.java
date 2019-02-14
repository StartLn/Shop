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
public class MlssRecyclerAdapter extends RecyclerView.Adapter<MlssRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CommodityList.ResultBean.MlssBean.CommodityListBeanXX> mMlss;

    public MlssRecyclerAdapter(Context mContext, ArrayList<CommodityList.ResultBean.MlssBean.CommodityListBeanXX> mMlss) {
        this.mContext = mContext;
        this.mMlss = mMlss;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mlss, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mlssImage.setImageURI(mMlss.get(i).getMasterPic());
        viewHolder.mlssTitle.setText(mMlss.get(i).getCommodityName());
        viewHolder.mlssPrice.setText("￥" + (int) mMlss.get(i).getPrice()+".00");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoodsClick.onGoodsClick(i);
                //Toast.makeText(mContext, "点击了: " + mMlss.get(i).getCommodityName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMlss.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mlss_image)
        SimpleDraweeView mlssImage;
        @BindView(R.id.mlss_title)
        TextView mlssTitle;
        @BindView(R.id.mlss_price)
        TextView mlssPrice;

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
