package pl.patryk.ztpj.rmi;

import pl.patryk.ztpj.Server;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.*;

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

        if(existsInInternalDB(aUserName,aPassword) || existsInLDAP(aUserName, aPassword)){
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

    //public Map getMemberMap() { return memberMap; }

    private boolean existsInLDAP(String username, String password) {

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://82.145.72.13:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "wipsad\\" + username);
        env.put(Context.SECURITY_CREDENTIALS, password);

        try
        {
            new InitialLdapContext(env, null);
            return true;
        }
        catch (NamingException e)
        {
            System.out.println(e.getMessage());
        }

        return false;
    }

    private boolean existsInInternalDB(String username, String password) {
        return memberMap.containsKey(username) && memberMap.get(username).equals(password);
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
