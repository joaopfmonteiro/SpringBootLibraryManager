CREATE TABLE library_user_dto
(
   ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   USER_NAME VARCHAR NOT NULL,
   USER_ROLE VARCHAR NOT NULL
);
CREATE TABLE book_dto
(
   ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   TITLE VARCHAR NOT NULL,
   GENDER VARCHAR NOT NULL,
   DESCRIPTION VARCHAR NOT NULL,
   YEAR_OF_PUBLICATION INTEGER NOT NULL,
   AUTHOR VARCHAR NOT NULL
);