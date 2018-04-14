CREATE TABLE t_cart (
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

CREATE TABLE t_cart_items (
  fk_cart                BIGINT(10)     NOT NULL,
  id                     VARCHAR(40)    NOT NULL,
  article_id             VARCHAR(40)    NOT NULL,
  title                  VARCHAR(255)   NOT NULL,
  tariff_price           DECIMAL(10, 2) NOT NULL,
  tariff_target_quantity DECIMAL(10, 2) NOT NULL,
  tariff_target_unit     VARCHAR(10)    NOT NULL,
  quantity               DECIMAL(10, 2) NOT NULL,
  quantity_unit          VARCHAR(10)    NOT NULL,
  timestamp              TIMESTAMP      NULL,
  UNIQUE INDEX id_u (id),
  INDEX fk_cart_i (fk_cart),
  FOREIGN KEY (fk_cart)
  REFERENCES t_cart (pk_id)
);