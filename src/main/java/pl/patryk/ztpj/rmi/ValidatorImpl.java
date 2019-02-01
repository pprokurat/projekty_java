package pl.patryk.ztpj.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ValidatorImpl extends UnicastRemoteObject implements Validator {
    //UnicastRemoteObject implements Remote interface


    Map memberMap;

    public ValidatorImpl() throws RemoteException {
        super();
        memberMap = new HashMap();
        memberMap.put("John", "Appleseed");
    }

    @Override
    public String validate(String aUserName, String aPassword) throws RemoteException {
        if(getMemberMap().containsKey(aUserName) && getMemberMap().get(aUserName).equals(aPassword)){
            return "Welcome " + aUserName;
        }

        return "";
    }

    public Map getMemberMap() {
        return memberMap;
    }

//    public static void main(String[] args) {
//        try {
//            Naming.rebind("localhost",new ValidatorImpl());
//            //System.out.println("Login server open for business");
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException me) {
//            System.out.println("MalformedURLException " + me);
//        }
//    }
}
