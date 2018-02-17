package edu.noia.myoffice.sale.domain.event.folder;

import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.sale.domain.vo.FolderId;

public interface FolderEventPayload extends EventPayload {

    FolderId getFolderId();
}
