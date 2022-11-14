package ru.maybeqeafie.jpalessons;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CardRemote extends Remote {
    Boolean donate(String name,int number,int term,int code) throws RemoteException;

}
