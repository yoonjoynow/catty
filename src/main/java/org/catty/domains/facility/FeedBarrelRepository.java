package org.catty.domains.facility;

import java.util.List;

public interface FeedBarrelRepository {
    public List<FeedBarrel> selectFeedBarrelList(FeedBarrel feedBarrel) throws Exception;
    public FeedBarrel selectFeedBarrel(FeedBarrel feedBarrel) throws Exception;
    public void insertFeedBarrel(FeedBarrel feedBarrel) throws Exception;
    public void updateFeedBarrel(FeedBarrel feedBarrel) throws Exception;
    public void deleteFeedBarrel(FeedBarrel feedBarrel) throws Exception;
}