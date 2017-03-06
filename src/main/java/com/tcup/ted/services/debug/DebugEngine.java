package com.tcup.ted.services.debug;


import com.sun.jdi.Bootstrap;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class DebugEngine {


    //refer the github url for more reference
    public void putDebugPoints() {
        //https://github.com/Jody7/JDWP-Client/blob/master/MainMon.java
    }

    /**
     * gets the JVM VirtualMachine object to communicate with the JVM
     * for the remote debugging
     * @param host localhost
     * @param port 8000
     * @return VirtualMachine
     */
    public VirtualMachine getVM(String host, int port) {
        AttachingConnector connector = getConnector();
        try {
            VirtualMachine vm = connect(connector, host, String.valueOf(port));
            return vm;
        } catch (IllegalConnectorArgumentsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns the Attaching Connector for the Java Virtual Machine
     * @return AttachingConnector object
     */
    private AttachingConnector getConnector() {
        VirtualMachineManager vmManager = Bootstrap.virtualMachineManager();
        Optional optional = vmManager.attachingConnectors().stream()
                .filter(connector->connector.name().equals("com.sun.jdi.SocketAttach"))
                .findFirst();
        if(optional.isPresent()){
            return  (AttachingConnector) optional.get();
        } else {
            throw new IllegalArgumentException("Connector not available");
        }
    }

    /**
     * connects to the JVM through the host and debug port and attaching connector
     * @param connector
     * @param host
     * @param port
     * @return
     * @throws IllegalConnectorArgumentsException
     * @throws IOException
     */
    private VirtualMachine connect(AttachingConnector connector, String host, String port)
            throws IllegalConnectorArgumentsException, IOException {

        Map<String, Connector.Argument> args = connector.defaultArguments();
        Connector.Argument portArg = args.get("port");
        portArg.setValue(port);
        Connector.Argument addressArg = args.get("hostname");
        addressArg.setValue(host);

        return connector.attach(args);
    }
}
