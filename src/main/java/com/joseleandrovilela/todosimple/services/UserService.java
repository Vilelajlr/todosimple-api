package com.joseleandrovilela.todosimple.services;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joseleandrovilela.todosimple.models.User;
import com.joseleandrovilela.todosimple.repositories.TaskRepository;
import com.joseleandrovilela.todosimple.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired // Injeção de dependência
    private UserRepository userRepository;

    @Autowired // Injeção de dependência
    private TaskRepository taskRepository;

    
    public User FindUserById(Long id){
        
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! Id: " + id + ",Tipo: " + User.class.getName()));

    }


    @Transactional // Garante que a transação seja finalizada
    public User create(User user){
        
        user.setId(null);
        user = this.userRepository.save(user);
        this.taskRepository.saveAll(user.getTasks());
        return user;
    }


    @Transactional  // Garante que a transação seja finalizada
    public User update(User user){

        User newUser = this.FindUserById(user.getId());
        newUser.setPassword(user.getPassword());

        return this.userRepository.save(newUser);
        
    }
    

    public void delete(Long id){
        this.FindUserById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar o usuário! Id: " + id + ",Tipo: " + User.class.getName());
        }
    }

}
