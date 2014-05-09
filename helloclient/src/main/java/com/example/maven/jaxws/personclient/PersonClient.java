package com.example.maven.jaxws.personclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;


 
public class PersonClient {
       
    public static void main(String[] args) {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(Person.class);
        config.getClasses().add(JacksonJsonProvider.class);
        
        Client client = Client.create(config);
        printList(getAllPersons(client));
        //System.out.println(deletePersonById(client,23));
        System.out.println(returnPersonById(client,50));
        try{
        System.out.println(updatePerson(client,"Tested","Tested",21,0));}
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private static List<Person> getAllPersons(Client client) {    
        WebResource webResource = 
                client.resource("http://localhost:34215/helloservice/rest/persons/getallpersons");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Person>> type = new GenericType<List<Person>>() {};
        return response.getEntity(type);
    }
    
    private static  Boolean deletePersonById(Client client, int Person_Id) {
        WebResource webResource = 
            client.resource("http://localhost:34215/helloservice/rest/persons/deleteperson/"+
                             Integer.toString(Person_Id));      
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<Boolean> type = new GenericType<Boolean>() {};
        return response.getEntity(type);
    }
    private static  Person returnPersonById(Client client, int Person_Id) {
        WebResource webResource = 
            client.resource("http://localhost:34215/helloservice/rest/persons/returnperson/"+
                             Integer.toString(Person_Id));      
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<Person> type = new GenericType<Person>() {};
        return response.getEntity(type);
    }
    
    private static  Boolean updatePerson(Client client,  String FirstName, 
                                String LastName, int Age, long Person_Id) {
        WebResource webResource = 
            client.resource("http://localhost:34215/helloservice/rest/persons/updateperson/"+
                             FirstName+"/"+LastName+"/"+
                             Integer.toString(Age) + "/"+Long.toString(Person_Id));      
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<Boolean> type = new GenericType<Boolean>() {};
        return response.getEntity(type);
    }
    
    private static void printList(List<Person> persons) {
        for (Person person : persons) {
            System.out.println(person);
        }
    }

}
