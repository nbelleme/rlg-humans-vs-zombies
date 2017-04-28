package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.models.SupplyZone;
import io.resourcepool.hvsz.service.HumanService;
import io.resourcepool.hvsz.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResourceController {

    @Autowired
    private HumanService humanService;

    @Autowired
    private ResourceService resourceService;

    private static final int ID_SUPPLY_ZONE = 1;

    /**
     * Drop a resource.
     * @param safeZone SupplyZone id
     * @param lifeId life id
     * @param model Model
     * @return String (human vue)
     */
    @PostMapping("/resource/drop")
    public String dropResource(
            @RequestParam(value = "safeZone") String safeZone,
            @RequestParam(value = "lifeId") String lifeId,
            Model model) {

        //TODO get safezone by id
        resourceService.dropById(Integer.parseInt(safeZone), 1, Integer.parseInt(lifeId));
        int resource = resourceService.getCurrentResource(Integer.parseInt(safeZone));
        model.addAttribute("nbResources",   "1 resource has been dropped : supply zone n°" + ID_SUPPLY_ZONE + " contains :" + resource + "resources");
        return "human";
    }

    /**
     * Show the supply-zone view.
     * @return String (supply-zone vue)
     */
    @GetMapping("/resource")
    public String resourceGet() {
        return "supply-zone";
    }

    /**
     * Take a resource.
     * @param supplyZone Supply zone id
     * @param lifeId life id
     * @param model Model
     * @return String (supply-zone)
     */
    @PostMapping("/resource/get")
    public String resourceGet(
            @RequestParam(value = "supplyZone") String supplyZone,
            @RequestParam(value = "lifeId") String lifeId,
            Model model) {

        //TODO: get zone by id
        SupplyZone s = resourceService.getSupplyZone(Integer.parseInt(supplyZone));

        int gotRes = humanService.getResources(s, 1, Integer.parseInt(lifeId));

        model.addAttribute("nbSupplyResources", gotRes + " resource has been taken : remaining resources :" + s.getResource());
        return "supply-zone";
    }
}