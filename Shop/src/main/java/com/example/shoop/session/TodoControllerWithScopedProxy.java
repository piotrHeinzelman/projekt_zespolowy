package com.example.shoop.session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scopedproxy")
public class TodoControllerWithScopedProxy {

    private TodoList todos;

    // constructor and request mappings


    @GetMapping("/form")
    public String showForm(Model model) {
        if (!todos.isEmpty()) {
            model.addAttribute("todo", todos.peekLast());
        } else {
            model.addAttribute("todo", new TodoItem());
        }
        return "scopedproxyform";
    }
}
