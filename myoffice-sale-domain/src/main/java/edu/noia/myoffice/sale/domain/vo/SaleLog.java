package edu.noia.myoffice.sale.domain.vo;

public class SaleLog extends CartMutableSample<SaleLogItem> {

    private SaleLog(FolderId folderId) {
        super(folderId, CartType.LOG);
    }

    public static SaleLog of(FolderId folderId) {
        return new SaleLog(folderId);
    }
}
