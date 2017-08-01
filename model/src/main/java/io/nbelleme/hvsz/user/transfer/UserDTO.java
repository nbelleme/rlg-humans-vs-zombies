package io.nbelleme.hvsz.user.transfer;

import io.nbelleme.hvsz.api.DPO;
import org.mongojack.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Document(collection = "user")
public class UserDTO implements Serializable, DPO {

  private static final long serialVersionUID = 8910219810353397902L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
  private String username;
  private String password;

  /**
   * Constructor.
   */
  private UserDTO() {
    //TODO delete
    username = "username";
    password = "password";
  }

  /**
   * Create default {@link UserDTO}.
   *
   * @return new UserDTO
   */
  public static UserDTO build() {
    return new UserDTO();
  }

  //CHECKSTYLE_OFF
  public String getId() {
    return id;
  }

  public UserDTO setId(String id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserDTO setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserDTO setPassword(String password) {
    this.password = password;
    return this;
  }
  //CHECKSTYLE_ONE
}
