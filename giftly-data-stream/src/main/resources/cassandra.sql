CREATE KEYSPACE giftly
    WITH REPLICATION = {
        'class' : 'SimpleStrategy',
        'replication_factor' : 1
    };

CREATE TABLE giftly.gift_likes_raw (
    gift_id text,
    user_id text,
    liked int,
    comment text,
    like_time timestamp,
    PRIMARY KEY ((gift_id, user_id), like_time, liked)
);

CREATE TABLE giftly.gift_likes_current (
    gift_id text,
    user_id text,
    liked int,
    like_time timestamp,
    PRIMARY KEY (gift_id, user_id)
);

CREATE TABLE giftly.gift_likes_daily (
    gift_id text,
    date text,
    total bigint,
    PRIMARY KEY (gift_id, date)
) WITH CLUSTERING ORDER BY (date DESC);

CREATE TABLE giftly.gift_likes_top (
    gift_id text,
    total counter,
    PRIMARY KEY ((gift_id), total)
) WITH CLUSTERING ORDER BY (total DESC);