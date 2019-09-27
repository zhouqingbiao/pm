package com.zhouqb.pm.inspection;

import com.zhouqb.pm.physicalMachine.PhysicalMachine;
import com.zhouqb.pm.physicalMachine.PhysicalMachineRepository;
import com.zhouqb.pm.virtualMachine.VirtualMachine;
import com.zhouqb.pm.virtualMachine.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InspectionController {
    @Autowired
    private InspectionRepository inspectionRepository;
    @Autowired
    private PhysicalMachineRepository physicalMachineRepository;
    @Autowired
    private VirtualMachineRepository virtualMachineRepository;

    /**
     * 以物理机ID新增巡检记录
     *
     * @param model
     * @param inspection
     * @param physicalMachineId
     * @return
     */
    @PostMapping("/saveInspectionByPhysicalMachineId/{physicalMachineId}")
    public String saveInspectionByPhysicalMachineId(Model model, Inspection inspection, @PathVariable Long physicalMachineId) {
        inspection = inspectionRepository.save(inspection);
        model.addAttribute("inspection", inspectionRepository.findByPhysicalMachineIdOrderByXjrq(inspection.getPhysicalMachineId()));
        PhysicalMachine physicalMachine = physicalMachineRepository.findById(inspection.getPhysicalMachineId()).get();
        model.addAttribute("physicalMachine", physicalMachine);
        return "PhysicalMachineUpdate";
    }

    /**
     * 以虚拟机ID新增巡检记录
     *
     * @param model
     * @param inspection
     * @param virtualMachineId
     * @return
     */
    @PostMapping("/saveInspectionByVirtualMachineId/{virtualMachineId}")
    public String saveInspectionByVirtualMachineId(Model model, Inspection inspection, @PathVariable Long virtualMachineId) {
        inspection = inspectionRepository.save(inspection);
        model.addAttribute("inspection", inspectionRepository.findByVirtualMachineIdOrderByXjrq(inspection.getVirtualMachineId()));
        VirtualMachine virtualMachine = virtualMachineRepository.findById(inspection.getVirtualMachineId()).get();
        model.addAttribute("virtualMachine", virtualMachine);
        Long physicalMachineId = virtualMachine.getPhysicalMachineId();
        if (!StringUtils.isEmpty(physicalMachineId)) {
            model.addAttribute("physicalMachine", physicalMachineRepository.findById(virtualMachine.getPhysicalMachineId()).get());
        }
        return "PhysicalMachineUpdate";
    }

    /**
     * 以物理机ID或虚拟机ID查找巡检记录
     *
     * @param physicalMachineId
     * @param virtualMachineId
     * @return
     */
    public Iterable<Inspection> find(Long physicalMachineId, Long virtualMachineId) {
        if (!StringUtils.isEmpty(physicalMachineId) && StringUtils.isEmpty(virtualMachineId)) {
            return inspectionRepository.findByPhysicalMachineIdOrderByXjrq(physicalMachineId);
        } else if (StringUtils.isEmpty(physicalMachineId) && !StringUtils.isEmpty(virtualMachineId)) {
            return inspectionRepository.findByVirtualMachineIdOrderByXjrq(virtualMachineId);
        }
        return null;
    }

    /**
     * 以ID删除巡检记录
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/Inspection/Delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        Long physicalMachineId = inspectionRepository.findById(id).get().getPhysicalMachineId();
        inspectionRepository.deleteById(id);
        model.addAttribute("inspection", inspectionRepository.findByPhysicalMachineIdOrderByXjrq(physicalMachineId));
        PhysicalMachine physicalMachine = physicalMachineRepository.findById(physicalMachineId).get();
        model.addAttribute("physicalMachine", physicalMachine);
        return "PhysicalMachineUpdate";
    }
}
