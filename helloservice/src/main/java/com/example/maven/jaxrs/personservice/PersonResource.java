package  com.example.maven.jaxrs.personservice; 

import com.example.maven.jaxrs.dao.PostgresDAO;
import com.example.maven.jaxrs.exept.IllegalNameException;
import com.example.maven.jaxrs.personservice.mapping.Person;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource  {
    @Path("addperson/{FirstName}/{LastName}/{Age}")
    @GET
    public long addPerson(@PathParam("FirstName") final String FirstName, 
                                @PathParam("LastName") final String LastName,
                                @PathParam("Age") final int Age) {
        long personId;
        try {
            PostgresDAO dao = new  PostgresDAO();
            return dao.addPerson(FirstName, LastName, Age);
       }
       catch (Exception e){
           return 0;
       }
    }
    
   /**
    * Данная функция обновляет пользователя по его идентификатору
    */
    @Path("updateperson/{FirstName}/{LastName}/{Age}/{Person_Id}")
    @GET
    public Boolean updatePerson(@PathParam("FirstName") final String FirstName, 
                                @PathParam("LastName") final String LastName,
                                @PathParam("Age") final int Age, 
                                @PathParam("Person_Id") final long Person_Id) throws IllegalNameException {
           if (Person_Id==0)
            throw IllegalNameException.DEFAULT_INSTANCE;
           
            PostgresDAO dao = new  PostgresDAO();
            return dao.updatePerson(FirstName, LastName, Age, Person_Id);
    }
   /**
    * Данная функция возвращает пользователя по его идентификатору
    */
    @Path("returnperson/{Person_Id}")
    @GET
    
    public Person returnPersonById(@PathParam("Person_Id") final long Person_Id) { 
       try {
           PostgresDAO dao = new  PostgresDAO();
           return  dao.returnPersonById(Person_Id);
       }
       catch (Exception e){
           return null;
       }
    }        
   
   /**
    * Данная функция удаляет пользователя по его идентификатору
    */
    @Path("deleteperson/{Person_Id}")
    @GET
    public Boolean deletePersonById(@PathParam("Person_Id") final long Person_Id) {
        try {
            PostgresDAO dao = new  PostgresDAO();
            return dao.deletePersonById(Person_Id);       
       }
       catch (Exception e){
           return Boolean.FALSE;
       }
    }
    
   /**
    * Данная функция возвращает всех пользователей
    */    
    @Path("getallpersons")
    @GET
    public List<Person> getAllPersons() {
        try {
            PostgresDAO dao = new  PostgresDAO();
            List<Person> person =dao.getAllPerson();
            return person;       
        }
        catch (Exception e){
            return null;
        }
    }
}