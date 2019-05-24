package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class HomeController {
    @Autowired
    MsgRepository msgRepository;

    @RequestMapping("/")
    public String listMessages(Model model) {
        model.addAttribute("messages", msgRepository.findAll());
        return "list";


    }
    @GetMapping("/add")
    public String messageForm(Model model) {
        model.addAttribute("message", new Message());
        return "messageForm";
    }
    @PostMapping("/process")

    public String processForm(@Valid Message message,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "messageform";
        }
        msgRepository.save(message);
        return "redirect:/";

    }
    @RequestMapping("/detail/{id}")
    public String showMessage(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("message", msgRepository.findById(id).get());
        return "show";

    }

    @RequestMapping("/update/{id}")
    public String updateMessages(@PathVariable("id") long id,
                                Model model) {
        model.addAttribute("message", msgRepository.findById(id).get());
        return "messageform";
    }
    @RequestMapping("/delete/{id}")
    public String delMessage(@PathVariable("id") long id) {
        msgRepository.deleteById(id);
        return "redirect:/";
    }
}

