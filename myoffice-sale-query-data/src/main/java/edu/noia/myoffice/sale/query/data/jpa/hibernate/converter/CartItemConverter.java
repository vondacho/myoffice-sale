package edu.noia.myoffice.sale.query.data.jpa.hibernate.converter;

import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Tariff;
import edu.noia.myoffice.common.domain.vo.Unit;
import edu.noia.myoffice.sale.domain.vo.Article;
import edu.noia.myoffice.sale.domain.vo.ArticleId;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartItemId;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class CartItemConverter implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{
                StringType.INSTANCE.sqlType(), // id
                StringType.INSTANCE.sqlType(), // article id
                StringType.INSTANCE.sqlType(), // article name
                LongType.INSTANCE.sqlType(), // tariff amount in centimes
                StringType.INSTANCE.sqlType(), // tariff target quantity
                StringType.INSTANCE.sqlType(), // tariff target unit
                StringType.INSTANCE.sqlType(), // quantity
                StringType.INSTANCE.sqlType(), // quantity unit (compliant with target unit)
                DateType.INSTANCE.sqlType(), // timestamp
        };
    }

    @Override
    public Class returnedClass() {
        return CartItem.class;
    }

    @Override
    public boolean equals(Object x, Object y) {
        return x != null && x.equals(y);
    }

    @Override
    public int hashCode(Object x) {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        try {
            final String id = rs.getString(names[0]);
            final String articleId = rs.getString(names[1]);
            final String articleName = rs.getString(names[2]);
            final Long tariffPrice = rs.getLong(names[3]);
            final String tariffTargetQuantity = rs.getString(names[4]);
            final String tariffTargetUnit = rs.getString(names[5]);
            final String quantity = rs.getString(names[6]);
            final String quantityUnit = rs.getString(names[7]);
            final Timestamp timestamp = rs.getTimestamp(names[8]);

            return CartItem.of(
                    CartItemId.of(UUID.fromString(id)),
                    Article.of(
                            ArticleId.of(UUID.fromString(articleId)),
                            articleName,
                            Tariff.of(
                                    Amount.ofCentimes(tariffPrice),
                                    Quantity.of(tariffTargetQuantity, Unit.valueOf(tariffTargetUnit)))),
                    Quantity.of(quantity, Unit.valueOf(quantityUnit)),
                    timestamp != null ? timestamp.toLocalDateTime() : null);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
        CartItem cartItem = (CartItem) value;
        // item id
        st.setString(index++, cartItem.getId().getId().toString());
        // article id
        st.setString(index++, cartItem.getArticle().getArticleId().getId().toString());
        // article name
        st.setString(index++, cartItem.getArticle().getName());
        // tariff price
        st.setLong(index++, cartItem.getArticle().getTariff().getQuantity().toCentimes());
        // tariff target quantity
        st.setString(index++, cartItem.getArticle().getTariff().getBy().asString());
        // tariff target unit
        st.setString(index++, cartItem.getArticle().getTariff().getTargetUnit().toString());
        // item quantity
        st.setString(index++, cartItem.getQuantity().asString());
        // item quantity unit
        st.setString(index++, cartItem.getQuantity().getUnit().toString());
        // item timestamp
        st.setTimestamp(index, cartItem.getTimestamp() != null ? Timestamp.valueOf(cartItem.getTimestamp()) : Timestamp.from(Instant.now()));
    }

    @Override
    public Object deepCopy(Object value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) {
        return original;
    }
}
