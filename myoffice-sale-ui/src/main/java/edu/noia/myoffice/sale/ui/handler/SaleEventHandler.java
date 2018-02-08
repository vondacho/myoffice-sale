package edu.noia.myoffice.sale.ui.handler;

import edu.noia.myoffice.common.domain.event.Event;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleEventHandler {

    SaleEventFluxSinkAdapter sinkAdapter;

    @Getter
    Flux<Event> eventStream = Flux.create(sink -> sinkAdapter = new SaleEventFluxSinkAdapter() {
        @Override
        public void accept(Event event) {
            sink.next(event);
        }

        @Override
        public void complete() {
            sink.complete();
        }
    });

    private interface SaleEventFluxSinkAdapter extends Consumer<Event> {
        void complete();
    }

    public void on(Event event) {
        LOG.debug("{} received event: {}", getClass().getName(), event);
        if (sinkAdapter != null) {
            sinkAdapter.accept(event);
        }
    }

    public void terminate() {
        LOG.debug("{} is being terminated", getClass().getName());
        sinkAdapter.complete();
        LOG.debug("{} has been terminated", getClass().getName());
    }

}
