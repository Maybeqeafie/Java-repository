CREATE TABLE Users
(
    id INT auto_increment PRIMARY KEY NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE OPERATION
(
    id INT auto_increment PRIMARY KEY NOT NULL,
    id_buyer INT NOT NULL,
    admin_check BOOLEAN,
    user_check BOOLEAN,
    name VARCHAR(255) NOT NULL,
    text VARCHAR(255) NOT NULL,
    id_user INT NOT NULL,
    price INT NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

ALTER TABLE OPERATION
    ADD CONSTRAINT operation_buyer_fk FOREIGN KEY (id_buyer) REFERENCES USERS (id);
ALTER TABLE OPERATION
    ADD CONSTRAINT operation_user_fk FOREIGN KEY (id_user) REFERENC
