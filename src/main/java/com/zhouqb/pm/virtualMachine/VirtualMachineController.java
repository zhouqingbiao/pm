package com.zhouqb.pm.virtualMachine;

import com.zhouqb.pm.inspection.InspectionController;
import com.zhouqb.pm.physicalMachine.PhysicalMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class VirtualMachineController {
    @Autowired
    private VirtualMachineRepository virtualMachineRepository;
    @Autowired
    private PhysicalMachineRepository physicalMachineRepository;
    @Autowired
    private InspectionController inspectionController;

    /**
     * 查找虚拟机
     *
     * @param model
     * @param virtualMachine
     * @return
     */
    @GetMapping("/VirtualMachine")
    public String find(Model model, VirtualMachine virtualMachine) {
        String mac = virtualMachine.getMac();
        String ip = virtualMachine.getIp();
        Long physicalMachineId = virtualMachine.getPhysicalMachineId();
        if (StringUtils.isEmpty(physicalMachineId)) {
            if (StringUtils.isEmpty(mac) && StringUtils.isEmpty(ip)) {
                Sort sort = new Sort(Sort.Direction.ASC, "id");
                model.addAttribute("virtualMachine", virtualMachineRepository.findAll(sort));
            } else if (StringUtils.isEmpty(mac)) {
                model.addAttribute("virtualMachine",
                        virtualMachineRepository.findByIpLikeOrderById("%" + ip + "%"));
            } else if (StringUtils.isEmpty(ip)) {
                model.addAttribute("virtualMachine",
                        virtualMachineRepository.findByMacLikeOrderById("%" + mac + "%"));
            } else {
                model.addAttribute("virtualMachine",
                        virtualMachineRepository.findByMacLikeOrIpLikeOrderById("%" + mac + "%", "%" + ip + "%"));
            }
        } else {
            if (StringUtils.isEmpty(mac) && StringUtils.isEmpty(ip)) {
                model.addAttribute("virtualMachine", virtualMachineRepository.findByPhysicalMachineIdOrderById(physicalMachineId));
            } else if (StringUtils.isEmpty(mac)) {
                model.addAttribute("virtualMachine",
                        virtualMachineRepository.findByIpLikeAndPhysicalMachineIdOrderById("%" + ip + "%", physicalMachineId));
            } else if (StringUtils.isEmpty(ip)) {
                model.addAttribute("virtualMachine",
                        virtualMachineRepository.findByMacLikeAndPhysicalMachineIdOrderById("%" + mac + "%", physicalMachineId));
            } else {
                model.addAttribute("virtualMachine",
                        virtualMachineRepository.findByMacLikeOrIpLikeAndPhysicalMachineIdOrderById("%" + mac + "%", "%" + ip + "%", physicalMachineId));
            }
        }
        model.addAttribute("mac", mac);
        model.addAttribute("ip", ip);
        model.addAttribute("physicalMachineId", physicalMachineId);
        return "VirtualMachine";
    }

    /**
     * 新增虚拟机
     *
     * @param model
     * @param virtualMachine
     * @return
     */
    @PostMapping("/VirtualMachine/Save")
    public String insert(Model model, VirtualMachine virtualMachine) {
        virtualMachine.setMtime(LocalDate.now());
        virtualMachineRepository.save(virtualMachine);
        Long physicalMachineId = virtualMachine.getPhysicalMachineId();
        model.addAttribute("virtualMachine", virtualMachineRepository.findByPhysicalMachineIdOrderById(physicalMachineId));
        model.addAttribute("physicalMachineId", physicalMachineId);
        return "VirtualMachine";
    }

    /**
     * 以ID删除虚拟机
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/VirtualMachine/Delete/{id}")
    public String deleteById(Model model, @PathVariable Long id) {
        Long physicalMachineId = virtualMachineRepository.findById(id).get().getPhysicalMachineId();
        virtualMachineRepository.deleteById(id);
        model.addAttribute("virtualMachine", virtualMachineRepository.findByPhysicalMachineIdOrderById(physicalMachineId));
        model.addAttribute("physicalMachineId", physicalMachineId);
        model.addAttribute("message", "该虚拟机已删除！");
        return "VirtualMachine";
    }

    /**
     * 以ID更新虚拟机
     *
     * @param model
     * @param id
     * @param virtualMachine
     * @return
     */
    @PostMapping("/VirtualMachine/Save/{id}")
    public String saveById(Model model, @PathVariable Long id, VirtualMachine virtualMachine) {
        virtualMachine.setMtime(LocalDate.now());
        Long physicalMachineId = virtualMachine.getPhysicalMachineId();
        model.addAttribute("virtualMachine", virtualMachine = virtualMachineRepository.save(virtualMachine));
        if (!StringUtils.isEmpty(physicalMachineId)) {
            model.addAttribute("physicalMachine", physicalMachineRepository.findById(virtualMachine.getPhysicalMachineId()).get());
        }
        model.addAttribute("inspection", inspectionController.find(null, virtualMachine.getId()));
        return "VirtualMachineUpdate";
    }

    /**
     * 以ID查询虚拟机
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/VirtualMachine/Find/{id}")
    public String findById(Model model, @PathVariable Long id) {
        VirtualMachine virtualMachine = virtualMachineRepository.findById(id).get();
        model.addAttribute("virtualMachine", virtualMachine);
        Long physicalMachineId = virtualMachine.getPhysicalMachineId();
        if (!StringUtils.isEmpty(physicalMachineId)) {
            model.addAttribute("physicalMachine", physicalMachineRepository.findById(virtualMachine.getPhysicalMachineId()).get());
        }
        model.addAttribute("inspection", inspectionController.find(null, virtualMachine.getId()));
        return "VirtualMachineUpdate";
    }

    /**
     * 以物理机ID查询虚拟机
     *
     * @param model
     * @param physicalMachineId
     * @return
     */
    @GetMapping("/VirtualMachine/PhysicalMachineId/{physicalMachineId}")
    public String findByPhysicalMachineId(Model model, @PathVariable Long physicalMachineId) {
        if (physicalMachineId == -1) {
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            model.addAttribute("virtualMachine", virtualMachineRepository.findAll(sort));
        } else {
            model.addAttribute("virtualMachine", virtualMachineRepository.findByPhysicalMachineIdOrderById(physicalMachineId));
            model.addAttribute("physicalMachineId", physicalMachineId);
        }
        return "VirtualMachine";
    }

}
