package ganargatul.com.moviegdk.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ganargatul.com.moviegdk.Adapter.MovieAdapter;
import ganargatul.com.moviegdk.DetailMovies;
import ganargatul.com.moviegdk.MovieItems;
import ganargatul.com.moviegdk.R;

import static ganargatul.com.moviegdk.utils.Constants.GET_URL;
import static ganargatul.com.moviegdk.utils.Constants.TOKEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class Popular extends Fragment implements MovieAdapter.OnItemClickListener {

    View v;
    RecyclerView mRecyclerView;
    MovieAdapter mMovieAdapter;
    ArrayList<MovieItems> mMovieItems;
    RequestQueue mRequestQueue;
    ProgressDialog mProgressDialog;
    public static final String MOVIE_TITLE = "TITLE";
    public static final String MOVIE_OVERVIEW = "OVERVIEW";
    public static final String MOVIE_RELEASE = "RELEASE";
    public static final String MOVIE_IMAGE = "IMAGE";
    public static final String MOVIE_VOTE ="VOTE";
    public static final String MOVIE_ADULT = "ADULT";
    public Popular() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_popular, container, false);
        mRecyclerView = v.findViewById(R.id.container_popular);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mMovieItems = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getContext());
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Please Wait!");
        mProgressDialog.show();
        getMovie();
        return v;
    }

    private void getMovie() {
        String url = GET_URL + "movie/popular?api_key=" + TOKEN;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    int length = jsonArray.length();
                    for(int i = 0;i<length;i++){
                        JSONObject result = jsonArray.getJSONObject(i);
                        String title = result.getString("original_title");
                        int Vote = result.getInt("vote_average");
                        Boolean adult = result.getBoolean("adult");
                        String Image = result.getString("poster_path");
                        String release = result.getString("release_date");
                        String overview = result.getString("overview");
                        MovieItems movieItems = new MovieItems(title,Vote,adult,Image,overview,release);
                        mMovieItems.add(movieItems);
                    }
                    mProgressDialog.dismiss();
                    mMovieAdapter = new MovieAdapter(getContext(),mMovieItems);
                    mRecyclerView.setAdapter(mMovieAdapter);
                    mMovieAdapter.setOnItemClickListener(Popular.this);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent Detail = new Intent(getContext(),DetailMovies.class);
        MovieItems ClickedItems = mMovieItems.get(position);
        Detail.putExtra(MOVIE_TITLE,ClickedItems.getTitle());
        Detail.putExtra(MOVIE_IMAGE,ClickedItems.getImage());
        Detail.putExtra(MOVIE_OVERVIEW,ClickedItems.getOverview());
        Detail.putExtra(MOVIE_RELEASE,ClickedItems.getRelease_date());
        Detail.putExtra(MOVIE_VOTE,ClickedItems.getVote());
        Detail.putExtra(MOVIE_ADULT,ClickedItems.getAdult());
        startActivity(Detail);
    }
}
