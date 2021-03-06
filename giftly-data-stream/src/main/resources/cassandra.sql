CREATE KEYSPACE OpsCenter
    WITH REPLICATION = {
        'class' : 'SimpleStrategy',
        'replication_factor' : 1
    };

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
    PRIMARY KEY ((gift_id, user_id, liked), like_time)
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

CREATE TABLE giftly.gift_likes_total (
    gift_id text,
    total counter,
    PRIMARY KEY (gift_id)
);
