package edu.noia.myoffice.sale.common.processor;

import java.util.function.Function;

public interface EventProcessor<T, U> extends Function<T, U> {
}
