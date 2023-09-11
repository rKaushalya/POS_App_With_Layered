package lk.ijse.JavaEE.dao;

import lk.ijse.JavaEE.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.JavaEE.dao.custom.impl.ItemDAOImpl;
import lk.ijse.JavaEE.dao.custom.impl.PurchaseOrderDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory == null){
            return daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,PURCHASEORDER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case PURCHASEORDER:
                return new PurchaseOrderDAOImpl();
            default:
                return null;
        }
    }
}
