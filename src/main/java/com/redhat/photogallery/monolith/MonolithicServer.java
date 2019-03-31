package com.redhat.photogallery.monolith;

import com.redhat.photogallery.common.Server;
import com.redhat.photogallery.common.VertxInit;
import com.redhat.photogallery.like.LikeComponent;
import com.redhat.photogallery.photo.PhotoComponent;
import com.redhat.photogallery.query.QueryComponent;

public class MonolithicServer {

    private static final int LISTEN_PORT = 8083;

    public static void main(String[] args) {
        VertxInit.createVertx().deployVerticle(
                new Server(LISTEN_PORT, new PhotoComponent(), new LikeComponent(), new QueryComponent()));
    }
}