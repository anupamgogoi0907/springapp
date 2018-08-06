DROP TABLE TB_TWEET IF EXISTS;
CREATE TABLE "PUBLIC"."TB_TWEET"
(
   ID bigint PRIMARY KEY NOT NULL,
   CREATION_DATE timestamp,
   FOLLOWERS_COUNT integer,
   HASH_TAG varchar(255),
   LANGUAGE varchar(255),
   TWEET_ID bigint,
   USER_ID bigint,
   USER_NAME varchar(255)
);
