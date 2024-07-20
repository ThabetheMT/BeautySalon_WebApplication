package org.unclecodes.mysalonsystem.mycontroller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.unclecodes.mysalonsystem.User.User;
import org.unclecodes.mysalonsystem.admin.Admin;
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
    @PostMapping("/user-menu")
    public String logging(@ModelAttribute User user, Model model){

        String role = user.role();
        String page = "index";

        switch (role){
            case "admin" :
                if(mainService.getAdmin(user.email()) != null){
                    page = "adminMenu";
                }
                break;
            case "client" :
                if(mainService.getClient(user.email()) != null){
                    page = "clientMenu";
                }
                break;
            case "stylist" :
                if(mainService.getStylist(user.email()) != null){
                    page = "stylistMenu";
                }
                break;
        }

        model.addAttribute("role", role);
        return page;

    }

    //login
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //adding a user
    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user, Model model){
        mainService.addUser(user);
        model.addAttribute("role", user.role());
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
