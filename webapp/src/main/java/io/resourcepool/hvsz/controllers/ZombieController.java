package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.service.StatusService;
import io.resourcepool.hvsz.service.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ZombieController {
    @Autowired
    ZombieService zombieService;

    @Autowired
    StatusService gameStatus;

    /**
     * Get the zombie page.
     *
     * @return String (zombie vue)
     */
    @GetMapping("/zombie")
    public String get() {
        return "zombie";
    }

    /**
     * Kill an human by life id
     * @param lifeId .
     * @param model .
     * @return
     */
    @PostMapping("/zombie/kill")
    public String kill(@RequestParam(value = "lifeId") String lifeId, Model model) {
        if (zombieService.kill(lifeId)) {
            model.addAttribute("message", "one human have been killed. There are " + gameStatus.get(1L).getNbHumanAlive() + " humans alive.");
        } else {
            model.addAttribute("message", "There are no more human alive ... Zombies won !");
        }
        return "redirect:/zombie";
    }
}