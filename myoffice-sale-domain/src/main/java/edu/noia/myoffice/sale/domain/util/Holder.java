package edu.noia.myoffice.sale.domain.util;

import java.util.function.Consumer;

public interface Holder<T> {
    void execute(Consumer<T> action);
}
