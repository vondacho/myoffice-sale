CREATE TABLE cart_state_aud (
  pk_id      BIGINT(10)   NOT NULL,
  id         VARCHAR(40)  NOT NULL,
  folder_id  VARCHAR(40)  NOT NULL,
  type       VARCHAR(10)  NOT NULL,
  title      VARCHAR(255) NULL,
  invoice_id VARCHAR(40)  NULL,
  order_id   VARCHAR(40)  NULL,
  notes      LONGTEXT     NULL,
  rev        INTEGER      NOT NULL,
  revtype    TINYINT      NOT NULL
);

CREATE TABLE cart_items_aud (
  cart_pk    BIGINT(10)     NOT NULL,
  id         VARCHAR(40)    NOT NULL,
  article_id VARCHAR(40)    NOT NULL,
  title      VARCHAR(255)   NOT NULL,
  tariff     DECIMAL(10, 2) NOT NULL,
  unit       VARCHAR(10)    NOT NULL,
  quantity   DECIMAL(10, 2) NOT NULL,
  timestamp  TIMESTAMP      NULL,
  rev        INTEGER        NOT NULL,
  revtype    TINYINT        NOT NULL
);

