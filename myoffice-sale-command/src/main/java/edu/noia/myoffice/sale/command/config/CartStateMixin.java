package edu.noia.myoffice.sale.command.config;

import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartType;
import edu.noia.myoffice.sale.domain.vo.FolderId;

import java.util.List;

public class CartStateMixin {

    FolderId folderId;
    CartType type;
    String title;
    String notes;
    List<CartItem> items;
}
