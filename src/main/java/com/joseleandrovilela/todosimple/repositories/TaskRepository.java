package com.joseleandrovilela.todosimple.repositories;


import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


import com.joseleandrovilela.todosimple.models.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {



    List<Task>  findByUser_Id(Long id);


    /*
     *  metodo que retorna uma lista de tarefas de um usuario
     *  @param id
     *  @return List<Task>
     *  @Query("SELECT t FROM Task t WHERE t.user.id = :id")
     *  List<Task> findByUserId(@Param("id") Long id);
     *  Sempre que chamado o metodo findByUserId, o spring data jpa ira executar a query
     *  o metodo pode ter qualquer nome, o que importa é a anotação @Query
     * 
     */

    // @Query("SELECT t FROM Task t WHERE t.user.id = :id")
    // List<Task> findByUserId(@Param("id") Long id);



    /*
     * metodo que retorna uma lista de tarefas de um usuario
     * @param id
     * @return List<Task>
     * @Query(nativeQuery = true, value = "SELECT * FROM task WHERE user_id = :id")
     * List<Task> findByUser_Id(@Param("id") Long id);
     * Sempre que chamado o metodo findByUser_Id, o spring data jpa ira executar a query
     * o metodo pode ter qualquer nome, o que importa é a anotação @Query
     * porem este metodo utiliza apenas sql puro
     */

    // @Query(nativeQuery = true, value = "SELECT * FROM task t WHERE t.user_id = :id")
    // List<Task> findByUser_Id(@Param("id") Long id);
    
}
