package ganargatul.com.moviegdk;

public class MovieItems {

    private String title;
    private int vote;
    private boolean adult;
    private String image;
    private String overview;
    private String release_date;

    public MovieItems(String title, int vote, boolean adult, String image, String overview, String release_date) {
        this.title = title;
        this.vote = vote;
        this.adult = adult;
        this.image = image;
        this.overview = overview;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public int getVote() {
        return vote;
    }

    public boolean getAdult() {
        return adult;
    }

    public String getImage() {
        return image;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }
}
