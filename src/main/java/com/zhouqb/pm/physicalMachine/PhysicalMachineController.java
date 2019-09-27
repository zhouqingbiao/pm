package com.zhouqb.pm.physicalMachine;

import com.zhouqb.pm.inspection.InspectionController;
import com.zhouqb.pm.virtualMachine.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class PhysicalMachineController {
    @Autowired
    private PhysicalMachineRepository physicalMachineRepository;
    @Autowired
    private VirtualMachineRepository virtualMachineRepository;
    @Autowired
    private InspectionController inspectionController;

    /**
     * 查找物理机
     *
     * @param model
     * @param physicalMachine
     * @return
     */
    @GetMapping("/PhysicalMachine")
    public String find(Model model, PhysicalMachine physicalMachine) {
        String mac = physicalMachine.getMac();
        String ip = physicalMachine.getIp();
        if (StringUtils.isEmpty(mac) && StringUtils.isEmpty(ip)) {
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            model.addAttribute("physicalMachine", physicalMachineRepository.findAll(sort));
        } else if (StringUtils.isEmpty(mac)) {
            model.addAttribute("physicalMachine",
                    physicalMachineRepository.findByIpLikeOrderById("%" + ip + "%"));
        } else if (StringUtils.isEmpty(ip)) {
            model.addAttribute("physicalMachine",
                    physicalMachineRepository.findByMacLikeOrderById("%" + mac + "%"));
        } else {
            model.addAttribute("physicalMachine",
                    physicalMachineRepository.findByMacLikeOrIpLikeOrderById("%" + mac + "%", "%" + ip + "%"));
        }
        model.addAttribute("mac", mac);
        model.addAttribute("ip", ip);
        return "PhysicalMachine";
    }

    /**
     * 新增物理机
     *
     * @param model
     * @param physicalMachine
     * @return
     */
    @PostMapping("/PhysicalMachine/Save")
    public String save(Model model, PhysicalMachine physicalMachine) {
        physicalMachineRepository.save(physicalMachine);
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        model.addAttribute("physicalMachine", physicalMachineRepository.findAll(sort));
        return "PhysicalMachine";
    }

    /**
     * 以ID删除物理机
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/PhysicalMachine/Delete/{id}")
    public String deleteById(Model model, @PathVariable Long id) {
        if (ObjectUtils.isEmpty(virtualMachineRepository.findByPhysicalMachineIdOrderById(id))) {
            physicalMachineRepository.deleteById(id);
            model.addAttribute("message", "该物理机下没有虚拟机，已删除！");
        } else {
            model.addAttribute("message", "该物理机下有虚拟机，不可删除！");
        }
        Sort sort = new Sort(Sort.Direction.ASC, "ip");
        model.addAttribute("physicalMachine", physicalMachineRepository.findAll(sort));
        return "PhysicalMachine";
    }

    /**
     * 以ID更新物理机
     *
     * @param model
     * @param id
     * @param physicalMachine
     * @return
     */
    @PostMapping("/PhysicalMachine/Save/{id}")
    public String saveById(Model model, @PathVariable Long id, PhysicalMachine physicalMachine) {
        physicalMachine.setMtime(LocalDate.now());
        physicalMachine = physicalMachineRepository.save(physicalMachine);
        model.addAttribute("physicalMachine", physicalMachine);
        model.addAttribute("inspection", inspectionController.find(physicalMachine.getId(), null));
        return "PhysicalMachineUpdate";
    }

    /**
     * 以ID查找物理机
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/PhysicalMachine/Find/{id}")
    public String findById(Model model, @PathVariable Long id) {
        PhysicalMachine physicalMachine = physicalMachineRepository.findById(id).get();
        model.addAttribute("physicalMachine", physicalMachine);
        model.addAttribute("inspection", inspectionController.find(physicalMachine.getId(), null));
        return "PhysicalMachineUpdate";
    }
}
