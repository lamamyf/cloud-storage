CREATE TABLE IF NOT EXISTS USERS (
  id INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  first_name VARCHAR(20),
  last_name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
    id INT PRIMARY KEY auto_increment,
    title VARCHAR(20),
    description VARCHAR (1000),
    user_id INT,
    foreign key (user_id) references USERS(id)
);

CREATE TABLE IF NOT EXISTS FILES (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR,
    content_type VARCHAR,
    size VARCHAR,
    user_id INT,
    data BLOB,
    foreign key (user_id) references USERS(id)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    id INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    user_id INT,
    foreign key (user_id) references USERS(id)
);

-- Test Data --

INSERT INTO USERS (username, salt, password, first_name, last_name) VALUES ('lama', 'aecuRk9joFw7KN09pDJ0gw==', 'kddLs+ImHy/k+3zACyJ3wg==', 'lama', 'm');

INSERT INTO NOTES (title, description, user_id) VALUES
                  ('note title', 'note description', 1),
                  ('some note', 'some description', 1);

INSERT INTO CREDENTIALS (url, username, key, password, user_id) VALUES
                        ('url', 'lamamyf', 'VSs7L+7jw0qXHM9naLS8uA==', 'UQktjU9N+mIBHiJUBlSRYA==', 1),
                        ('url', 'user', 'VSs7L+7jw0qXHM9naLS8uA==', 'UQktjU9N+mIBHiJUBlSRYA==',  1);
