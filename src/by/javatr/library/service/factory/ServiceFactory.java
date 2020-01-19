package by.javatr.library.service.factory;

import by.javatr.library.dao.exception.DAOException;
import by.javatr.library.service.ClientService;

import java.io.FileNotFoundException;

public class ServiceFactory {

    private static volatile ServiceFactory instanceServiceFactory;
    private static volatile ClientService instanceClientService;

    //creating instance of ServiceFactory by pattern Singleton with lazy initial.
    public static ServiceFactory getInstance() {
        ServiceFactory localInstance = instanceServiceFactory;
        if (localInstance == null) {
            synchronized (ServiceFactory.class) {
                localInstance = instanceServiceFactory;
                if (localInstance == null) {
                    instanceServiceFactory = localInstance = new ServiceFactory();
                }
            }
        }
        return localInstance;
    }

    public ClientService getClientService() throws FileNotFoundException, DAOException {
        ClientService localClientService = instanceClientService;
        if (localClientService == null) {
            synchronized (ServiceFactory.class) {
                localClientService = instanceClientService;
                if (localClientService == null) {
                    instanceClientService = localClientService = new ClientService();
                }
            }
        }
        return localClientService;
    }
}