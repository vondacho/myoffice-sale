CREATE TABLE cart (
  pk_id      BIGINT(10)   NOT NULL AUTO_INCREMENT,
  id         VARCHAR(40)  NOT NULL,
  folder_id  VARCHAR(40)  NOT NULL,
  type       VARCHAR(10)  NOT NULL,
  title      VARCHAR(255) NULL,
  invoice_id VARCHAR(40)  NULL,
  order_id   VARCHAR(40)  NULL,
  notes      LONGTEXT     NULL,
  PRIMARY KEY (pk_id),
  UNIQUE INDEX id_u (id),
  INDEX folder_id_i (folder_id)
);

CREATE TABLE cart_items (
  cart_pk    BIGINT(10)     NOT NULL,
  id         VARCHAR(40)    NOT NULL,
  article_id VARCHAR(40)    NOT NULL,
  title      VARCHAR(255)   NOT NULL,
  tariff     DECIMAL(10, 2) NOT NULL,
  unit       VARCHAR(10)    NOT NULL,
  quantity   DECIMAL(10, 2) NOT NULL,
  timestamp  TIMESTAMP      NULL,
  UNIQUE INDEX id_u (id),
  INDEX cart_pk_fk_i (cart_pk),
  FOREIGN KEY (cart_pk)
  REFERENCES cart_state (pk_id)
);