package com.project.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.entities.Provider;
import com.project.repository.ProviderRepository;

@Controller
@RequestMapping("/provider/")
public class ProviderController {

	private final ProviderRepository providerRepository;

	@Autowired
	public ProviderController(ProviderRepository providerRepository) {
		this.providerRepository = providerRepository;
	}

	@GetMapping("list")
	public String listProviders(Model model) {
		model.addAttribute("providers", providerRepository.findAll());
		return "provider/listProviders";

	}

	@GetMapping("add")
	public String showAddProviderForm(Model model) {
		Provider provider = new Provider();// object dont la valeur des attributs par defaut
		model.addAttribute("provider", provider);
		return "provider/addProvider";
	}

	@PostMapping("add")
	public String addProvider(@Valid Provider provider, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "provider/addProvider";
		}
		providerRepository.save(provider);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showProviderFormToUpdate(@PathVariable("id") long id, Model model) {
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
		model.addAttribute("provider", provider);
		return "provider/updateProvider";
	}

	@PostMapping("update")
	public String updateProvider(@Valid Provider provider, BindingResult result, Model model) {
		providerRepository.save(provider);
		return "redirect:list";
	}

}
