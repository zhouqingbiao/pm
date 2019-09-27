package com.zhouqb.pm.interfaceInformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InterfaceInformationController {
    @Autowired
    private InterfaceInformationRepository interfaceInformationRepository;

    /**
     * 查找接口信息
     *
     * @param model
     * @param interfaceUrl
     * @return
     */
    @GetMapping("/InterfaceInformation")
    public String findByHistory(Model model, String interfaceUrl) {
        if (StringUtils.isEmpty(interfaceUrl)) {
            model.addAttribute("interfaceInformation", interfaceInformationRepository.findByHistory("否"));
        } else {
            model.addAttribute("interfaceInformation", interfaceInformationRepository.findByInterfaceUrlLikeAndHistory("%" + interfaceUrl + "%", "否"));
        }
        return "InterfaceInformation";
    }

    /**
     * 新增接口信息
     *
     * @param model
     * @param interfaceInformation
     * @return
     */
    @PostMapping("/InterfaceInformation/Save")
    public String save(Model model, InterfaceInformation interfaceInformation) {
        interfaceInformation.setHistory("否");
        interfaceInformationRepository.save(interfaceInformation);
        model.addAttribute("interfaceInformation", interfaceInformationRepository.findByHistory("否"));
        return "InterfaceInformation";
    }

    /**
     * 以ID更新接口信息
     *
     * @param model
     * @param interfaceInformation
     * @param id
     * @return
     */
    @PostMapping("/InterfaceInformation/Save/{id}")
    public String save(Model model, InterfaceInformation interfaceInformation, @PathVariable String id) {
        interfaceInformationRepository.save(interfaceInformation);
        model.addAttribute("interfaceInformation", interfaceInformationRepository.findByHistory("否"));
        return "InterfaceInformation";
    }

    /**
     * 以ID删除接口信息
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/InterfaceInformation/Delete/{id}")
    public String deleteById(Model model, @PathVariable Long id) {
        interfaceInformationRepository.deleteById(id);
        model.addAttribute("interfaceInformation", interfaceInformationRepository.findByHistory("否"));
        return "InterfaceInformation";
    }

    /**
     * 以ID查找接口信息
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/InterfaceInformation/Find/{id}")
    public String findById(Model model, @PathVariable Long id) {
        model.addAttribute("interfaceInformation", interfaceInformationRepository.findById(id).get());
        return "InterfaceInformationUpdate";
    }
}
