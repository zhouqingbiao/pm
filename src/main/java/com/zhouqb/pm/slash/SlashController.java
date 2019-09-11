package com.zhouqb.pm.slash;

import com.zhouqb.pm.physicalMachine.PhysicalMachineRepository;
import com.zhouqb.pm.virtualMachine.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SlashController {
    @Autowired
    private PhysicalMachineRepository physicalMachineRepository;
    @Autowired
    private VirtualMachineRepository virtualMachineRepository;

    @GetMapping("/")
    public String slash(Model model, HttpServletRequest httpServletRequest) {
        // IP
        model.addAttribute("RA", httpServletRequest.getRemoteAddr());
        return "Slash";
    }

    @GetMapping("/Select")
    public String select(Model model, String mac, String ip) {
        if (StringUtils.isEmpty(mac) && StringUtils.isEmpty(ip)) {
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            model.addAttribute("physicalMachine", physicalMachineRepository.findAll(sort));
            model.addAttribute("virtualMachine", virtualMachineRepository.findAll(sort));
        } else if (StringUtils.isEmpty(ip)) {
            model.addAttribute("physicalMachine", physicalMachineRepository.findByMacLikeOrderById("%" + mac + "%"));
            model.addAttribute("virtualMachine", virtualMachineRepository.findByMacLikeOrderById("%" + mac + "%"));
        } else if (StringUtils.isEmpty(mac)) {
            model.addAttribute("physicalMachine", physicalMachineRepository.findByIpLikeOrderById("%" + ip + "%"));
            model.addAttribute("virtualMachine", virtualMachineRepository.findByIpLikeOrderById("%" + ip + "%"));
        } else {
            model.addAttribute("physicalMachine",
                    physicalMachineRepository.findByMacLikeOrIpLikeOrderById("%" + mac + "%", "%" + ip + "%"));
            model.addAttribute("virtualMachine",
                    virtualMachineRepository.findByMacLikeOrIpLikeOrderById("%" + mac + "%", "%" + ip + "%"));
        }
        model.addAttribute("mac", mac);
        model.addAttribute("ip", ip);
        return "Slash";
    }
}
