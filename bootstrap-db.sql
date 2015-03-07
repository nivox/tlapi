CREATE TABLE tlapi.users (
  user VARCHAR(32) PRIMARY KEY,
  name VARCHAR(64),
  description VARCHAR(256),
  location VARCHAR(128)
);

CREATE TABLE tlapi.user_follow_edges (
  user    VARCHAR(32),
  follows VARCHAR(32),
  PRIMARY KEY (user, follows),

  FOREIGN KEY (user) REFERENCES users(user),
  FOREIGN KEY (follows) REFERENCES users(user)
);

CREATE TABLE tlapi.tweets (
  tweet_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  user VARCHAR(32),
  timestamp BIGINT,
  tweet VARCHAR(140),

  FOREIGN KEY (user) REFERENCES users(user)
);
CREATE INDEX idx_tweet_user ON tlapi.tweets (user);
CREATE INDEX idx_tweet_timestamp ON tlapi.tweets (timestamp);

INSERT INTO tlapi.users (user, name, description, location) values ("nivox", "Andrea Zito", "Software engineer, geek, (mountain)biker, science fiction lover...", "Trento, Italy");
INSERT INTO tlapi.users (user, name, description, location) values ("thousandeyes", "ThousandEyes", "ThousandEyes extends performance analytics beyond the edge of the enterprise network into the public Internet. Follow @ThousandEyesOps for status updates.", "San Francisco - thousandeyes.com");
INSERT INTO tlapi.users (user, name, description, location) values ("user1", "Dummy user 1", "What can be said about dummy user 1?", "Dumbyland");
INSERT INTO tlapi.users (user, name, description, location) values ("user2", "Dummy user 2", "What can be said about dummy user 2?", "Dumbyland");
INSERT INTO tlapi.users (user, name, description, location) values ("user3", "Dummy user 3", "What can be said about dummy user 3?", "Dumbyland");
INSERT INTO tlapi.users (user, name, description, location) values ("user4", "Dummy user 4", "What can be said about dummy user 4?", "Dumbyland");
INSERT INTO tlapi.users (user, name, description, location) values ("user5", "Dummy user 5", "What can be said about dummy user 5?", "Dumbyland");

INSERT INTO tlapi.tweets (user, timestamp, tweet) values ("nivox", 1425603040, "Sample tweet containing a keyword I could filter on later: black");
INSERT INTO tlapi.tweets (user, timestamp, tweet) values ("nivox", 1388245380, "Started a new C project and got to the point where I needed to setup a Makefile... and tried scons instead. What a refreshing experience!");
INSERT INTO tlapi.tweets (user, timestamp, tweet) values ("thousandeyes", 1425593040, "In the @thousandeyes #DemoFriday, the lid is coming off the black box of #Internet performance. Sign up now! http://sdx.io/1DKGBcb");
INSERT INTO tlapi.tweets (user, timestamp, tweet) values ("thousandeyes", 1425684960, "Just another day @ThousandEyes. Fighting through the dark corners of the Internet #AFWD2015 http://thousandeyes.com/careers");

INSERT INTO tlapi.tweets (user, timestamp, tweet) values ("user1", 1325684960, "User1 tweet! Wow that's amazing");
INSERT INTO tlapi.tweets (user, timestamp, tweet) values ("user2", 1325684960, "User2 tweet! Wow that's amazing");
INSERT INTO tlapi.tweets (user, timestamp, tweet) values ("user3", 1325684960, "User3 tweet! Wow that's amazing");
INSERT INTO tlapi.tweets (user, timestamp, tweet) values ("user4", 1325684960, "User4 tweet! Wow that's amazing");
INSERT INTO tlapi.tweets (user, timestamp, tweet) values ("user5", 1325684960, "User5 tweet! Wow that's amazing");


INSERT INTO tlapi.user_follow_edges (user, follows) values("user1", "thousandeyes");
INSERT INTO tlapi.user_follow_edges (user, follows) values("user2", "thousandeyes");
INSERT INTO tlapi.user_follow_edges (user, follows) values("user3", "thousandeyes");
INSERT INTO tlapi.user_follow_edges (user, follows) values("user4", "thousandeyes");
INSERT INTO tlapi.user_follow_edges (user, follows) values("user5", "thousandeyes");