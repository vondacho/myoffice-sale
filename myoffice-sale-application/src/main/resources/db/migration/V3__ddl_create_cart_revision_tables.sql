CREATE TABLE t_cart_aud (
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

CREATE TABLE t_cart_items_aud (
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
  rev                    INTEGER        NOT NULL,
  revtype                TINYINT        NOT NULL
);

