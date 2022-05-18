package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.dto.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/credential")
@RequiredArgsConstructor
public class CredentialController {

    private final CredentialService credentialService;

    @PostMapping
    public String addCredential(CredentialForm credential, RedirectAttributes redirectAttributes) {
        credentialService.addCredential(credential.url(), credential.username(), credential.password(), 1);
        redirectAttributes.addFlashAttribute("success", "Credential added successfully.");
        return "redirect:/result";
    }

    @PostMapping("/{id}")
    public String updateCredential(@PathVariable int id, CredentialForm credential, RedirectAttributes redirectAttributes) {
        credentialService.updateCredentialById(credential.url(), credential.username(), credential.password(), id);
        redirectAttributes.addFlashAttribute("success", "Credential updated successfully.");
        return "redirect:/result";
    }

    @DeleteMapping("/{id}")
    public String deleteCredential(@PathVariable int id, RedirectAttributes redirectAttributes){
        credentialService.deleteCredentialById(id);
        redirectAttributes.addFlashAttribute("success", "Credential deleted successfully.");
        return "redirect:/result";
    }
}
