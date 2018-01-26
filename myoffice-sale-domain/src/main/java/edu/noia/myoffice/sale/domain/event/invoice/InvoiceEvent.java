package edu.noia.myoffice.sale.domain.event.invoice;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;

public interface InvoiceEvent extends Event {

    InvoiceId getInvoiceId();
}
