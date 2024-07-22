package com.joseleandrovilela.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joseleandrovilela.todosimple.models.Task;
import com.joseleandrovilela.todosimple.models.User;
import com.joseleandrovilela.todosimple.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);

        return task.orElseThrow(() -> new RuntimeException(
            "Tarefa não encontrada!" +id + ", Tipo: " + Task.class.getName()
        ));

    }


    @Transactional
    public Task create(Task task) {
        User user = this.userService.FindUserById(task.getUser().getId());
        
        task.setId(null);
        task.setUser(user);
        task = this.taskRepository.save(task);

        return task;

    }

    @Transactional
    public Task update(Task task) {
        Task newTask = this.findById(task.getId());
        newTask.setDescription(task.getDescription());      
        return this.taskRepository.save(newTask);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar a tarefa: " + id);
        }
    }



}
