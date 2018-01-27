package edu.noia.myoffice.sale.domain.event.folder;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.FolderId;

public interface FolderEvent extends Event {

    FolderId getFolderId();
}
