package ganargatul.com.moviegdk.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ganargatul.com.moviegdk.MovieItems;
import ganargatul.com.moviegdk.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context mContext;
    private ArrayList<MovieItems> mMovieList;
    private OnItemClickListener mListener;
    private View v;


    public MovieAdapter(Context Context,ArrayList<MovieItems> MovieList){
        mContext = Context;
        mMovieList = MovieList;

    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(mContext).inflate(R.layout.movie_items,viewGroup,false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        MovieItems movieItems = mMovieList.get(i);

        String image = movieItems.getImage();

        String title = movieItems.getTitle();

        String release = movieItems.getRelease_date();
        movieViewHolder.mTitle.setText(title);
        movieViewHolder.mRelease.setText(release);
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500"+image).fit().centerInside().into(movieViewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitle,mRelease;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.img_movie_items);
            mTitle = itemView.findViewById(R.id.txt_title);
            mRelease = itemView.findViewById(R.id.txt_release_date);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
