drop table if exists awl CASCADE;
CREATE TABLE awl (
    id BIGINT AUTO_INCREMENT,
    episode INTEGER NOT NULL,
    name VARCHAR(255),
    rating INTEGER NOT NULL,
    PRIMARY KEY (id)
);