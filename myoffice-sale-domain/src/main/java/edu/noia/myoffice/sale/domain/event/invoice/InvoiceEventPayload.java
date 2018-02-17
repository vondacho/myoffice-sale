package edu.noia.myoffice.sale.domain.event.invoice;

import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;

public interface InvoiceEventPayload extends EventPayload {

    InvoiceId getInvoiceId();
}
