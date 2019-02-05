package pl.patryk.ztpj.rmi;

import pl.patryk.ztpj.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ValidatorImpl extends UnicastRemoteObject implements Validator {
    //UnicastRemoteObject implements Remote interface


    Map memberMap;
    String token;

    public ValidatorImpl() throws RemoteException {
        super();
        memberMap = new HashMap();
        memberMap.put("John", "Appleseed");
        memberMap.put("Patryk", "1234abcd");
    }

    @Override
    public String validate(String aUserName, String aPassword) throws RemoteException {

        //String token;

        if(getMemberMap().containsKey(aUserName) && getMemberMap().get(aUserName).equals(aPassword)){
            //String genToken = "token";
            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            String genToken = bytes.toString();
            byte[] encodedBytes = Base64.getEncoder().encode(genToken.getBytes());
            token = new String(encodedBytes);
            Server.tokens.put(token, System.currentTimeMillis());
            return token;
//            String token = Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()).toString();
//            Server.tokens.put(token, System.currentTimeMillis());
//            return token;
        }

        token = "";

        return token;
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
