
-- Insert statements for roles
INSERT INTO roles (role_name, allowed_resources, allowed_read, allowed_create, allowed_update, allowed_delete)
VALUES
    ('DeletedUser', '/', false, false, false, false),
    ('Admin', '/', true, true, true, true),
    ('Manager', '/', true, true, true, false),
    ('User', '/', true, true, true, false);

-- Insert statements for users
INSERT INTO users (user_email, user_name, user_password, user_avatar, user_beliked)
VALUES
    ('unknownuser@dev.com', 'Unknown User', 'pwdunknownuser', 'path/to/profile-pic1.jpg', 0),
    ('johnsmith@gmail.com', 'John Smith', 'pwdjohnsmith', 'path/to/profile-pic2.jpg', 0),
    ('emmajohnson@gmail.com', 'Emma Johnson', 'pwdemmajohnson', 'path/to/profile-pic3.jpg', 0),
    ('michaelwilliams@gmail.com', 'Michael Williams', 'pwdmichaelwilliams', 'path/to/profile-pic4.jpg', 0),
    ('oliviabrown@gmail.com', 'Olivia Brown', 'pwdoliviabrown', 'path/to/profile-pic5.jpg', 0),
    ('lexiyin@gmail.com', 'Lexi Yin', 'pwdlexiyin', 'path/to/profile-pic6.jpg', 0),
    ('alexstanton@gmail.com', 'Alex Stanton', 'pwdalexstanton', 'path/to/profile-pic7.jpg', 0),
    ('jeffintrone@gmail.com', 'Jeff Introne', 'pwdjeffintrone', 'path/to/profile-pic8.jpg', 0);



-- Insert statements for user_role
INSERT INTO user_role (user_id, role_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 4),
    (6, 4),
    (7, 4),
    (8, 4);

-- Insert statements for hashtags
INSERT INTO hashtags (hashtag_content)
VALUES
    ('#technology'),
    ('#programming'),
    ('#art'),
    ('#music'),
    ('#fashion');

-- Insert statements with fake timestamp data
INSERT INTO posts (post_content, post_media, post_date, post_beliked, user_id)
VALUES
    ('Hello world!', 'path/to/media1.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 4),
    ('Exciting news!', 'path/to/media2.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 5),
    ('Check out this artwork!', 'path/to/media3.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 6),
    ('New music release!', 'path/to/media4.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 7),
    ('Fashion trends for summer', 'path/to/media5.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 8),
    ('Amazing sunset!', 'path/to/media6.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 4),
    ('Delicious food recipe!', 'path/to/media7.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 5),
    ('Beautiful nature photography', 'path/to/media8.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 6),
    ('Book recommendation: Must-read!', 'path/to/media9.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 7),
    ('Travel destination: Paradise on Earth', 'path/to/media10.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 8),
    ('Admin post', 'path/to/media11.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 2),
    ('Manager post', 'path/to/media12.jpg', TIMESTAMP '2023-01-01' + (random() * INTERVAL '365 days'), 0, 3);

-- Insert statements for post_hashtag
INSERT INTO post_hashtag (post_id, hashtag_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
    (5, 3),
    (5, 5);

-- Insert statements for likes
INSERT INTO likes (user_id, post_id, like_date)
VALUES
    (2, 1, TIMESTAMP '2023-01-02'),
    (3, 1, TIMESTAMP '2023-01-03'),
    (4, 2, TIMESTAMP '2023-02-01'),
    (5, 2, TIMESTAMP '2023-02-02'),
    (6, 3, TIMESTAMP '2023-03-01'),
    (7, 3, TIMESTAMP '2023-03-02'),
    (8, 4, TIMESTAMP '2023-04-01'),
    (4, 5, TIMESTAMP '2023-05-01'),
    (5, 6, TIMESTAMP '2023-06-01'),
    (6, 7, TIMESTAMP '2023-07-01'),
    (7, 8, TIMESTAMP '2022-08-01'),
    (8, 9, TIMESTAMP '2022-09-01'),
    (4, 10, TIMESTAMP '2022-10-01'),
    (5, 11, TIMESTAMP '2022-11-01'),
    (6, 12, TIMESTAMP '2022-12-01');

-- upgrade data
UPDATE posts
SET post_beliked = COALESCE(
    (
        SELECT COUNT(*)
        FROM likes
        WHERE likes.post_id = posts.id
    ),
    0
);

UPDATE users
SET user_beliked = COALESCE(
    (
    SELECT SUM(post_beliked)
    FROM posts
    WHERE posts.user_id = users.id
    ),
    0
);

ALTER TABLE posts
ALTER COLUMN post_beliked SET DEFAULT 0;

ALTER TABLE users
ALTER COLUMN user_beliked SET DEFAULT 0;
