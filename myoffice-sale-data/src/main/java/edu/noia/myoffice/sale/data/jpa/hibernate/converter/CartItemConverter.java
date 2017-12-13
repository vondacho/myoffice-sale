package edu.noia.myoffice.sale.data.jpa.hibernate.converter;

import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.domain.vo.Tariff;
import edu.noia.myoffice.common.domain.vo.Unit;
import edu.noia.myoffice.sale.domain.vo.Article;
import edu.noia.myoffice.sale.domain.vo.ArticleId;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CartItemConverter implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[] {
                StringType.INSTANCE.sqlType(), // id
                LongType.INSTANCE.sqlType(), // quantity in tariff unit
                StringType.INSTANCE.sqlType(), // article id
                StringType.INSTANCE.sqlType(), // article name
                LongType.INSTANCE.sqlType(), // tariff amount in centimes
                StringType.INSTANCE.sqlType(), // tariff unit
        };
    }

    @Override
    public Class returnedClass() {
        return CartItem.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x != null ? x.equals(y) : false;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        try {
            return CartItem.of(
                    UUID.fromString(rs.getString(names[0])),
                    rs.getLong(names[1]),
                    Article.of(
                            ArticleId.of(UUID.fromString(rs.getString(names[2]))),
                            rs.getString(names[3]),
                            Tariff.of(
                                    Amount.ofCentimes(rs.getLong(names[4])),
                                    Unit.valueOf(rs.getString(names[5])))));
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        CartItem cartItem = (CartItem)value;
        // item id
        st.setString(index++, cartItem.getId().toString());
        // item quantity
        st.setLong(index++, cartItem.getQuantity());
        // article id
        st.setString(index++, cartItem.getArticle().getArticleId().toString());
        // article name
        st.setString(index++, cartItem.getArticle().getName());
        // tariff price
        st.setLong(index++, cartItem.getArticle().getTariff().getValue().toCentimes());
        // tariff unit
        st.setString(index++, cartItem.getArticle().getTariff().getUnit().toString());
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable)value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
