package org.unclecodes.mysalonsystem.mycontroller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.unclecodes.mysalonsystem.client.Client;
import org.unclecodes.mysalonsystem.main_service.MainService;
import org.unclecodes.mysalonsystem.salon_exception.User_NotFound_Exception;
import org.unclecodes.mysalonsystem.stylist.Stylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MyController {

    private  final MainService mainService;

    public MyController(MainService mainService) {
        this.mainService = mainService;
    }

    //welcome page
    @GetMapping("/index")
    public String welcomePage(){
        return "index";
    }

    //logging
    @PostMapping("/client-menu")
    public String logging(@ModelAttribute Client client, HttpSession httpSession){
        Client newClient = mainService.getClient(client.getEmail()).get();
        if(!client.getPassword().equals(newClient.getPassword())){
            throw new User_NotFound_Exception("Invalid password.");
        }
        return "clientMenu";
    }

    //login
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //registering
    @PostMapping("/add-client")
    public String register(@ModelAttribute Client client, HttpSession httpSession){
         int sizeBeforAdded = mainService.allClients().size();
         mainService.addClient(client);
         int sizeAfterAdded = mainService.allClients().size();

         if(sizeBeforAdded < sizeAfterAdded){
             System.out.println("save success");
           //  httpSession.setAttribute("msg", "Client added successfully");
         }else{
             System.out.println("something wrong with the server.");
           //  httpSession.setAttribute("msg", "Something went wrong when adding the client.");
         }

        return "addClient";
    }

    //register
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    //all clients
    @GetMapping("/clients")
    public String clients(Model model){
        List<Client> clients = new ArrayList<>();
        clients = mainService.allClients();
        model.addAttribute("clients", clients);
        return "clients";
    }

    //client
    @GetMapping("/client")
    public String client(Model model){
        Client client = Optional.ofNullable(mainService.getClient(Long.valueOf("2")))
                .get()
                .orElseThrow(() -> new User_NotFound_Exception("User does not found."));
        model.addAttribute("client", client);
        return "client";
    }

    //all stylists
    @GetMapping("/stylists")
    public String stylists(Model model){
        List<Stylist> stylists = new ArrayList<>();
        stylists = mainService.allStylist();
        model.addAttribute("stylists", stylists);
        return "stylists";
    }

    //stylist
    @GetMapping("/stylist")
    public String stylist(Model model){
        Stylist stylist = Optional.ofNullable(mainService.getStylist(2))
                .get()
                .orElseThrow(() -> new User_NotFound_Exception("User does not found."));
        model.addAttribute("stylist", stylist);
        return "stylist";
    }
}
