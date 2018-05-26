DROP TABLE IF EXISTS user_inf;
CREATE TABLE user_inf (
  uid         SERIAL PRIMARY KEY,
  account     VARCHAR(20) NOT NULL UNIQUE,
  password    VARCHAR(16) NOT NULL,
  username    VARCHAR(20)          DEFAULT NULL,
  u_status    BIT                  DEFAULT b'0',
  create_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE log_inf (
  log_id    SERIAL PRIMARY KEY,
  accout    VARCHAR(20) NOT NULL,
  username  VARCHAR(20),
  ip        VARCHAR(20),
  type      INT         NOT NULL,
  operation VARCHAR(100),
  op_time   TIMESTAMP DEFAULT current_timestamp
);