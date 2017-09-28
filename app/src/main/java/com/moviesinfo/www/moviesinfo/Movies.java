package com.moviesinfo.www.moviesinfo;

/**
 * Created by HP on 03-08-2017.
 */

public class Movies {
   // private String movieTitle;
    //private String Adult;
    //private String releasedate;
    //private String language;
    private String url;
    private int vote_count;

   /* public Movies(String movieTitle, String adult, String releasedate, String language,String url) {
        this.movieTitle = movieTitle;
        Adult = adult;
        this.releasedate = releasedate;
        this.language = language;
        this.url=url;
    }*/
    public Movies(String url,int vote_count) {

        this.url=url;
        this.vote_count=vote_count;
    }

   /* public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getAdult() {
        return Adult;
    }

    public void setAdult(String adult) {
        Adult = adult;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }*/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
