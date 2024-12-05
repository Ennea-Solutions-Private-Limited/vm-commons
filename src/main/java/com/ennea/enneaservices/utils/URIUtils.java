package com.ennea.enneaservices.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class URIUtils {

    public static URI appendQueryToUri(URI uri, String appendQuery) throws URISyntaxException {
        String newQuery = uri.getQuery();
        if(newQuery == null){
            newQuery = appendQuery;
        } else{
            newQuery += "&" + appendQuery;
        }

        return new URI(uri.getScheme(), uri.getAuthority(),
                       uri.getPath(), newQuery, uri.getFragment());
    }

}
