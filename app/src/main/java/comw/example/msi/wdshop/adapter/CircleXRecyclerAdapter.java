package comw.example.msi.wdshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import comw.example.msi.wdshop.R;
import comw.example.msi.wdshop.bean.CircleListBean;

/**
 * <p>文件描述：<p>
 * <p>作者：${Ln}<p>
 * <p>创建时间：2019/1/7  14:39<p>
 * <p>更改时间：2019/1/7  14:39<p>
 * <p>版本号：1<p>
 */
public class CircleXRecyclerAdapter extends RecyclerView.Adapter<CircleXRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CircleListBean.ResultBean> mCircle;

    public CircleXRecyclerAdapter(Context mContext, ArrayList<CircleListBean.ResultBean> mCircle) {
        this.mContext = mContext;
        this.mCircle = mCircle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_circle, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.circleImage.setImageURI(mCircle.get(i).getHeadPic());
        viewHolder.circleUsername.setText(mCircle.get(i).getNickName());
        String sdate=(new SimpleDateFormat("yyyy-MM-dd")).format(mCircle.get(i).getCreateTime());
        viewHolder.circleDate.setText(sdate);
        viewHolder.circleContext.setText(mCircle.get(i).getContent());
        viewHolder.circleImages.setImageURI(mCircle.get(i).getImage());
        viewHolder.circleNumber.setText(mCircle.get(i).getGreatNum()+"");
        /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoodsClick.onGoodsClick(i);
                Toast.makeText(mContext, "点击了: " + mCircle.get(i).getCommodityId(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_image)
        SimpleDraweeView circleImage;
        @BindView(R.id.circle_username)
        TextView circleUsername;
        @BindView(R.id.circle_date)
        TextView circleDate;
        @BindView(R.id.circle_context)
        TextView circleContext;
        @BindView(R.id.circle_images)
        SimpleDraweeView circleImages;
        @BindView(R.id.circle_prise)
        ImageView circlePrise;
        @BindView(R.id.circle_number)
        TextView circleNumber;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {
        return mCircle.size();
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
