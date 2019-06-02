package ganargatul.com.moviegdk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static ganargatul.com.moviegdk.Fragment.Upcoming.MOVIE_ADULT;
import static ganargatul.com.moviegdk.Fragment.Upcoming.MOVIE_IMAGE;
import static ganargatul.com.moviegdk.Fragment.Upcoming.MOVIE_OVERVIEW;
import static ganargatul.com.moviegdk.Fragment.Upcoming.MOVIE_RELEASE;
import static ganargatul.com.moviegdk.Fragment.Upcoming.MOVIE_TITLE;
import static ganargatul.com.moviegdk.Fragment.Upcoming.MOVIE_VOTE;

public class DetailMovies extends AppCompatActivity {
    TextView mTitle,mOverview,mRelease,mVote,mAdult;
    String adultt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);
        ImageView mImageView = findViewById(R.id.image_detail);
        mTitle = findViewById(R.id.title_detail);
        mOverview = findViewById(R.id.overview_detail);
        mVote = findViewById(R.id.vote_detail);
        mRelease = findViewById(R.id.Release_detail);
        mAdult = findViewById(R.id.adult_detail);
        Intent i =getIntent();
        String image = i.getStringExtra(MOVIE_IMAGE);
        String title = i.getStringExtra(MOVIE_TITLE);
        String overview = i.getStringExtra(MOVIE_OVERVIEW);
        int Vote = i.getIntExtra(MOVIE_VOTE,0);
        String Release = i.getStringExtra(MOVIE_RELEASE);
        Boolean adult = i.getBooleanExtra(MOVIE_ADULT,false);
        if(adult){
            adultt="Yes";
        }else if(!adult) {
            adultt="No";
        }
        mTitle.setText(title);
        mOverview.setText(overview);
        mVote.setText( String.valueOf(Vote) + "/10" );
        mRelease.setText(Release);
        mAdult.setText(adultt);
        Picasso.with(this).load("https://image.tmdb.org/t/p/w500"+image).fit().centerInside().into(mImageView);

    }
}
