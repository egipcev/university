package ua.com.foxminded.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.entity.GroupEntity;
import ua.com.foxminded.service.GroupService;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private GroupService groupService;
    private ModelMapper modelMapper;

    @GetMapping()
    public String getGroups(Model model) {
        try {
            model.addAttribute("groups", groupService.getAllGroups());
        } catch (ServiceException e) {
            log.error("error reading groups", e);
        }
        return "groups/index";
    }

    @GetMapping("/{id}")
    public String getGroup(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("group", groupService.getGroupById(id));
        } catch (ServiceException e) {
            log.error("error reading group", e);
        }
        return "groups/group";
    }

    @GetMapping("/new")
    public String newGroup(Model model) {
        model.addAttribute("group", new GroupDto());
        return "groups/new";
    }

    @PostMapping
    public String create(@ModelAttribute("group") GroupDto group) {
        try {
            groupService.createGroup(modelMapper.map(group, GroupEntity.class));
        } catch (ServiceException e) {
            log.error("error creating group", e);
        }
        return "redirect:/groups";
    }

}
