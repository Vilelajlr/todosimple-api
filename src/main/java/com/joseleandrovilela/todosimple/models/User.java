package com.joseleandrovilela.todosimple.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/*  
 *  @Entity: quando avisado que é uma entidade, o spring sabe que é uma tabela no banco de dados
 *  @Table: nome da tabela no banco de dados
 *  @Id: chave primária
 *  @GeneratedValue: auto incremento
 *  @Column: nome da coluna no banco de dados
 *  
 *  Esta classe é um modelo de dados, que representa a tabela user no banco de dados
 *  
 *  @Author: José Leandro Vilela
 *  @Version: 1.0
 *  @Since: 2021-04-10
 *   
 */



@Entity // quando avisado que é uma entidade, o spring sabe que é uma tabela no banco de dados
@Table(name = User.TABLE_NAME) // nome da tabela no banco de dados
public class User {

    public interface CreateUser {}
    public interface UpdateUser {}

    public static final String TABLE_NAME = "user";
    

    @Id // chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento
    @Column(name = "id", unique = true) // nome da coluna no banco de dados
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 2, max = 100)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 6, max = 60)
    private String password;
    

    //private List<Task> tasks = new ArrayList<Task>();


    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(obj == null){
            return false;
        }

        if(!(obj instanceof User)){
            return false;
        }
        
        User other = (User) obj;
        if(other.id == null){
            if(other.id != null){
                return false;
            }else if(!this.id.equals(other.id)){
                return false;
            }

        }
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username) && Objects.equals(this.password, other.password);


    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.id == null ? 0 : this.id.hashCode());

        return result;
    }


}
