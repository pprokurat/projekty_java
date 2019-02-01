package pl.patryk.ztpj.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Validator extends Remote {
    String validate(String aUserName, String aPassword) throws RemoteException;

}
