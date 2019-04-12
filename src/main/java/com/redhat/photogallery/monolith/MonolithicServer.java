package com.redhat.photogallery.monolith;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.photogallery.common.data.LikesItem;
import com.redhat.photogallery.common.data.PhotoItem;
import com.redhat.photogallery.like.LikeComponent;
import com.redhat.photogallery.photo.PhotoComponent;
import com.redhat.photogallery.query.QueryComponent;

import io.vertx.reactivex.core.eventbus.EventBus;

@Path("/")
public class MonolithicServer {

    PhotoComponent photoDelegate = new PhotoComponent();
    LikeComponent likeDelegate = new LikeComponent();
    QueryComponent queryDelegate = new QueryComponent();

    @Inject
    public void injectEventBus(EventBus eventBus) {
        photoDelegate.injectEventBus(eventBus);
        likeDelegate.injectEventBus(eventBus);
        queryDelegate.injectEventBus(eventBus);
    }

    @Path("photos")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createPhoto(PhotoItem item) {
        return photoDelegate.createPhoto(item);
    }

    @Path("photos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAllPhotos() {
        return photoDelegate.readAllPhotos();
    }

    @Path("likes")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addLikes(LikesItem item) {
        likeDelegate.addLikes(item);
    }

    @Path("likes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAllLikes() {
        return likeDelegate.readAllLikes();
    }

    @Path("query")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCategoryOrderedByLikes(@QueryParam("category") String category) {
        return queryDelegate.readCategoryOrderedByLikes(category);
    }
}
