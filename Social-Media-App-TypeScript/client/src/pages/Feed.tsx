import React, { useState, useEffect, useRef, useCallback } from "react";
import PostForm from "../components/PostForm";
import Post from "../components/Post";
import { API_BASE_URL } from "../lib/apiConfig";
import FeedSkeleton from "../components/skeletons/FeedSkeleton";
import { getAuthToken } from '../lib/auth';

export default function Feed({ currentUserId } : {currentUserId: string;}) {

    const [posts, setPosts] = React.useState<Array<{postId: string; text: string; imageUrl?: string | null; videoUrl?: string | null; userId: string; username: string; likeCount: number; commentCount: number; liked: boolean}>>([]);
    const [loading, setLoading] = React.useState(true);
    const [offset, setOffset] = useState(0);
    const [hasMore, setHasMore] = useState(true);
    const [isFetchingMore, setIsFetchingMore] = useState(false);

    const LIMIT = 10;
    const token = getAuthToken();
    const observer = useRef<IntersectionObserver | null>(null);

    const fetchPosts = async (currentOffset: number) => {
        if (currentOffset === 0) {
            setLoading(true);
        } else {
            setIsFetchingMore(true);
        }

        try {
            const res = await fetch(`${API_BASE_URL}/posts?offset=${currentOffset}&limit=${LIMIT}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            const data = await res.json();

            if (currentOffset === 0) {
                setPosts(data);
            } else {
                setPosts(prev => [...prev, ...data]);
            }

            if (data.length < LIMIT) {
                setHasMore(false);
            }
        } catch (err) {
            console.error("Error fetching posts:", err);
        } finally {
            setLoading(false);
            setIsFetchingMore(false);
        }
    };

    useEffect(() => {
        fetchPosts(0);
    }, []);

    const lastPostRef = useCallback((node: HTMLDivElement | null) => {
        if (loading || isFetchingMore) return;
        if (observer.current) observer.current.disconnect();
        observer.current = new IntersectionObserver(entries => {
            if (entries[0].isIntersecting && hasMore) {
                const newOffset = offset + LIMIT;
                setOffset(newOffset);
                fetchPosts(newOffset);
            }
        });
        if (node) observer.current.observe(node);
    }, [loading, isFetchingMore, hasMore, offset]);

    if (loading) {
        return <FeedSkeleton />;
    }

    return (
        <div className="flex justify-center w-full">
            <div className="w-full max-w-xl">
                <PostForm />

                {posts.map((post, index) => {
                    if (posts.length === index + 1) {
                        return (
                            <div ref={lastPostRef} key={post.postId}>
                                <Post
                                    postId={post.postId}
                                    text={post.text}
                                    imageUrl={post.imageUrl}
                                    videoUrl={post.videoUrl}
                                    userId={post.userId}
                                    username={post.username}
                                    likeCount={post.likeCount}
                                    commentCount={post.commentCount}
                                    liked={post.liked}
                                    currentUserId={currentUserId}
                                />
                            </div>
                        );
                    } else {
                        return (
                            <Post
                                key={post.postId}
                                postId={post.postId}
                                text={post.text}
                                imageUrl={post.imageUrl}
                                videoUrl={post.videoUrl}
                                userId={post.userId}
                                username={post.username}
                                likeCount={post.likeCount}
                                commentCount={post.commentCount}
                                liked={post.liked}
                                currentUserId={currentUserId}
                            />
                        );
                    }
                })}

                {isFetchingMore && <p className="text-center text-gray-400 py-4">Se încarcă...</p>}
                {!hasMore && <p className="text-center text-gray-400 py-4">Nu mai sunt postări</p>}
            </div>
        </div>
    );
}