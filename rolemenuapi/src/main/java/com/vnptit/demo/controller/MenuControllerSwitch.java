package com.vnptit.demo.controller;

import com.vnptit.demo.dto.MenuDto;
import com.vnptit.demo.entity.Menu;
import com.vnptit.demo.exception.DuplicateException;
import com.vnptit.demo.response.MessageResponse;
import com.vnptit.demo.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import javax.xml.ws.Service;
import java.util.List;

@Controller
public class MenuControllerSwitch {
    @Autowired
    private IMenuService service;

    @RequestMapping("/")
    public String getHome() {
        return "home";
    }

    @RequestMapping("/setting")
    public String getSetting() {
        return "setting";
    }

    @RequestMapping("/add")
    public String getAdd() {
//        MenuDto menu = new MenuDto();
//        model.addAttribute("menu", menu);
        return "add";
    }
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String saveMenu(Menu menu) throws DuplicateException {
//        service.createMenu(menu);
//        return "redirect:/setting";
//    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editMenu(@PathVariable(name = "id") Long id){
    ModelAndView mav = new ModelAndView("edit");
        // Đẩy thằng con vào view
        MessageResponse<MenuDto> response = (MessageResponse<MenuDto>) service.getMenuById(id);
        MenuDto menu = response.getData();
        mav.addObject("menu", menu);
         // đẩy nốt thằng cha vào view :))))

        return mav;
    }

}