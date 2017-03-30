package masxdeveloper.simplegalley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Masx Developer on 3/30/17.
 * https://masx-dev.blogspot.com
 */
public class AdapterGallery extends RecyclerView.Adapter<AdapterGallery.ViewHolder> {

    private static final String TAG = AdapterGallery.class.getSimpleName();
    private Context ctx;
    private List<File> mList;
    private OnItemClickListener mListener;

    public AdapterGallery(Context ctx, List<File> list, OnItemClickListener onItemClickListener) {
        this.ctx = ctx;
        this.mList = list;
        this.mListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final File item = mList.get(position);


        Picasso.with(ctx)
                .load(item.getAbsoluteFile())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .resize(300,300)
                .centerCrop()
                .into(holder.Iv);


        Log.d(TAG, "onBindViewHolder: " + item.getAbsoluteFile());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Iv;

        ViewHolder(View itemView) {
            super(itemView);

            Iv = (ImageView) itemView.findViewById(R.id.gallery);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(File item);
    }
}