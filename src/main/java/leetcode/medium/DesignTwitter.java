package leetcode.medium;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DesignTwitter {
    class Twitter {

        record Tweet(int tweetId, long publishedNumber) {
        }

        final Map<Integer, List<Tweet>> userIdToTweetId;
        final Map<Integer, Set<Integer>> followerToFollowees;
        final AtomicLong tweetCounter;
        final int newsFeedLimit;

        public Twitter() {
            userIdToTweetId = new ConcurrentHashMap<>();
            followerToFollowees = new ConcurrentHashMap<>();
            tweetCounter = new AtomicLong();
            newsFeedLimit = 10;
        }

        public void postTweet(int userId, int tweetId) {
            userIdToTweetId.compute(userId, (k, v) -> {
                v = (v == null) ? new ArrayList<>() : v;
                Tweet t = new Tweet(tweetId, tweetCounter.getAndIncrement());
                v.add(t);
                return v;
            });
        }

        public List<Integer> getNewsFeed(int userId) {
            Stream<Integer> usersStream = Stream.concat(
                    followerToFollowees.getOrDefault(userId, Collections.emptySet()).stream(),
                    Stream.of(userId));
            List<Integer> out = usersStream.flatMap(followeeId ->
                            userIdToTweetId.getOrDefault(followeeId, Collections.emptyList()).stream())
                    .sorted(Comparator.comparingLong(Tweet::publishedNumber).reversed())
                    .limit(newsFeedLimit)
                    .map(Tweet::tweetId)
                    .collect(Collectors.toList());
            return out;
        }

        public void follow(int followerId, int followeeId) {
            followerToFollowees.compute(followerId, (k, v) -> {
                v = (v == null) ? Collections.newSetFromMap(new ConcurrentHashMap<>()) : v;
                v.add(followeeId);
                return v;
            });
        }

        public void unfollow(int followerId, int followeeId) {
            followerToFollowees.computeIfPresent(followerId, (k, v) -> {
                v.remove(followeeId);
                return v;
            });
        }
    }
}
