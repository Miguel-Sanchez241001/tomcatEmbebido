package com.example;

import java.io.File;

import javax.xml.ws.Endpoint;

//import javax.xml.ws.Endpoint;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
 import org.apache.catalina.connector.Connector;
 import org.apache.catalina.startup.Tomcat;
  
import com.example.serivicios.Enpointsoap;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setSilent(true);

        String webappDirLocation = "src/main/webapp/";
        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        // Configurar el puerto
        Connector connector = tomcat.getConnector();
        connector.setPort(9090);
        Context context = tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        // Publicar el servicio SOAP
        Endpoint.publish("http://localhost:9090/serviciosoap", new Enpointsoap());
        tomcat.getHost().setAutoDeploy(false);
        tomcat.start();
        tomcat.getServer().await();
    
        // String webappDirLocation = "src/main/java";
        // Tomcat tomcat = new Tomcat();

        // // Configurar el contexto de la aplicación web
        // String webappPath = "/";
        // File additionWebInfClasses = new File("target/classes");
        // StandardContext ctx = (StandardContext) tomcat.addWebapp(webappPath, new File(webappDirLocation).getAbsolutePath());
        // WebResourceRoot resources = new StandardRoot(ctx);
        // resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        // ctx.setResources(resources);

        // // Publicar el servicio SOAP en un endpoint
        // String address = "http://localhost:8080/CalculadoraEndpoint";
        // Endpoint.publish(address, new Enpointsoap());

        // // Iniciar el servidor Tomcat
        // tomcat.start();
        // tomcat.getServer().await();
       }
}
