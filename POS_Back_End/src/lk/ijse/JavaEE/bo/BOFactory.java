package lk.ijse.JavaEE.bo;

import lk.ijse.JavaEE.bo.custom.impl.CustomerBOImpl;
import lk.ijse.JavaEE.bo.custom.impl.ItemBOImpl;
import lk.ijse.JavaEE.bo.custom.impl.PurchaseOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        if (boFactory == null){
            return boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,PURCHASEORDER
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PURCHASEORDER:
                return new PurchaseOrderBOImpl();
            default:
                return null;
        }
    }
}
