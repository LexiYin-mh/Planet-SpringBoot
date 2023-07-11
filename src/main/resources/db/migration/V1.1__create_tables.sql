DROP TABLE IF EXISTS hashtags CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS posts CASCADE;
DROP TABLE IF EXISTS post_hashtag CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS user_role CASCADE;

-------------------------------------------------------

-- tables
-- Table: hashtags
CREATE TABLE hashtags (
    id BIGSERIAL  NOT NULL,
    hashtag_content varchar(100)  NOT NULL,
    CONSTRAINT hashtags_pk PRIMARY KEY (id)
);

-- Table: post_hashtag
CREATE TABLE post_hashtag (
    id BIGSERIAL  NOT NULL,
    post_id bigint  NOT NULL,
    hashtag_id bigint  NOT NULL,
    CONSTRAINT post_hashtag_pk PRIMARY KEY (id)
);

-- Table: posts
CREATE TABLE posts (
    id BIGSERIAL  NOT NULL,
    post_content text,
    post_media varchar(255)  NOT NULL, -- path to media file
    post_date timestamp  NOT NULL,
    post_beliked bigint DEFAULT 0,
    user_id bigint  NOT NULL,
    CONSTRAINT posts_pk PRIMARY KEY (id)
);

-- Table: users
CREATE TABLE users (
    id BIGSERIAL  NOT NULL,
    user_email varchar(255)  NOT NULL,
    user_name varchar(255)  NOT NULL,
    user_password varchar(255)  NOT NULL,
    user_avatar varchar(255), -- path to media file
    user_beliked bigint DEFAULT 0,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

-- Table: roles
CREATE TABLE roles (
    id BIGSERIAL  NOT NULL,
    role_name varchar(255)  NOT NULL,
    allowed_resources varchar(255)  NOT NULL,
    allowed_read boolean  NOT NULL default FALSE,
    allowed_create boolean  NOT NULL default FALSE,
    allowed_update boolean  NOT NULL default FALSE,
    allowed_delete boolean  NOT NULL default FALSE,
    CONSTRAINT roles_pk PRIMARY KEY (id)
);

-- Table: user_role
CREATE TABLE user_role (
    id BIGSERIAL  NOT NULL,
    user_id bigint  NOT NULL,
    role_id bigint  NOT NULL,
    CONSTRAINT user_role_pk PRIMARY KEY (id)
);

-- Table: Likes
CREATE TABLE likes (
    id BIGSERIAL NOT NULL,
    user_id bigint NOT NULL,
    post_id bigint NOT NULL,
    like_date timestamp NOT NULL,
    CONSTRAINT likes_pk PRIMARY KEY (id)
);

-------------------------------------------------------

-- foreign keys
-- Reference: post_hashtag_hashtags (table: post_hashtag)
ALTER TABLE post_hashtag ADD CONSTRAINT post_hashtag_hashtags
    FOREIGN KEY (hashtag_id)
    REFERENCES hashtags (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: post_hashtag_posts (table: post_hashtag)
ALTER TABLE post_hashtag ADD CONSTRAINT post_hashtag_posts
    FOREIGN KEY (post_id)
    REFERENCES posts (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: posts_users (table: posts)
ALTER TABLE posts ADD CONSTRAINT posts_users
    FOREIGN KEY (user_id)
    REFERENCES users (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: likes_users (table: likes)
ALTER TABLE likes ADD CONSTRAINT likes_users
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: likes_posts (table: likes)
ALTER TABLE likes ADD CONSTRAINT likes_posts
    FOREIGN KEY (post_id)
    REFERENCES posts (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- End of file.

