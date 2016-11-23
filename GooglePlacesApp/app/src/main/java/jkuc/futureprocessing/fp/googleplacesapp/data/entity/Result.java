package jkuc.futureprocessing.fp.googleplacesapp.data.entity;

import java.util.List;

public class Result {
    public final List<PlaceDescription> results;
    public final String next_page_token;

    public Result(String next_page_token, List<PlaceDescription> results) {
        this.results = results;
        this.next_page_token = next_page_token;
    }
}
