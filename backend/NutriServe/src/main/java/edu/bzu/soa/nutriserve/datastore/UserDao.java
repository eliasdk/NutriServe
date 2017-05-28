
package edu.bzu.soa.nutriserve.datastore;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.StringValue;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.Transaction;

import edu.bzu.soa.nutriserve.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * A simple User List application demonstrating how to connect to Cloud Datastore, create, modify,
 * delete, and query entities.
 */
public class UserDao {

  // [START build_service]
  // Create an authorized Datastore service using Application Default Credentials.
  private final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

  // Create a Key factory to construct keys associated with this project.
  private final KeyFactory keyFactory = datastore.newKeyFactory().setKind("User");
  // [END build_service]

  // [START add_entity]
  /**
   * Adds a user entity to the Datastore.
   *
   * @param description The user description
   * @return The {@link Key} of the entity
   * @throws DatastoreException if the ID allocation or put fails
   */
  public Key addUser(User newUser) {
    Key key = datastore.allocateId(keyFactory.newKey());
    Entity user = Entity.newBuilder(key)
        .set("name", newUser.getName())
        .set("birthDate", newUser.getBirthDate().toString())
        .set("weight", newUser.getWeight())
        .set("height", newUser.getHeight())
        .set("gender", newUser.getGender())
        .set("activityStyle", newUser.getActivityStyle())
        .set("email", newUser.getEmail())
        
        .build();
     
    
    datastore.put(user);
    System.out.println("User Key : " + key);
    return key;
  }
  // [END add_entity]

  // [START update_entity]
  /**
   * Marks a user entity as done.
   *
   * @param id The ID of the user entity as given by {@link Key#id()}
   * @return true if the user was found, false if not
   * @throws DatastoreException if the transaction fails
   */
  boolean markDone(long id) {
    Transaction transaction = datastore.newTransaction();
    try {
      Entity user = transaction.get(keyFactory.newKey(id));
      if (user != null) {
        transaction.put(Entity.newBuilder(user).set("done", true).build());
      }
      transaction.commit();
      return user != null;
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }
    }
  }
  // [END update_entity]

  // [START retrieve_entities]
  /**
   * Returns a list of all user entities in ascending order of creation time.
   *
   * @throws DatastoreException if the query fails
   */
  Iterator<Entity> listUsers() {
    Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("User").setOrderBy(OrderBy.asc("created")).build();
    return datastore.run(query);
  }
  // [END retrieve_entities]

  // [START delete_entity]
  /**
   * Deletes a user entity.
   *
   * @param id The ID of the user entity as given by {@link Key#id()}
   * @throws DatastoreException if the delete fails
   */
  void deleteUser(long id) {
    datastore.delete(keyFactory.newKey(id));
  }
  // [END delete_entity]

 
 
}