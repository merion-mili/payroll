package com.gardenline.spring.web.controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.Kantier;
import com.gardenline.spring.web.dao.KantierEditor;
import com.gardenline.spring.web.dao.Muajt;
import com.gardenline.spring.web.dao.Paga;
import com.gardenline.spring.web.dao.Recupero;
import com.gardenline.spring.web.dao.RecuperoEditor;
import com.gardenline.spring.web.dao.Year;
import com.gardenline.spring.web.service.KantierService;
import com.gardenline.spring.web.service.PagaService;
import com.gardenline.spring.web.service.RecuperoService;
import com.gardenline.spring.web.service.YearService;

@Controller
public class PagaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;

	@Autowired
	private RecuperoService recuperoService;

	@Autowired
	private KantierService kantierService;

	@Autowired
	private YearService yearService;

	@Autowired
	private PagaService pagaService;

	@Autowired
	private RecuperoEditor recuperoEditor;

	@Autowired
	private KantierEditor kantierEditor;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));

		binder.registerCustomEditor(Recupero.class, this.recuperoEditor);
		binder.registerCustomEditor(Kantier.class, this.kantierEditor);

	}

	@RequestMapping(value = "/searchRaport", method = RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView model = new ModelAndView("searchpaga");
		List<Muajt> enums = Arrays.asList(Muajt.values());
		List<Year> years = yearService.getCurrent();
		model.addObject("enums", enums);
		System.out.println(enums.size());
		model.addObject("years", years);
		model.addObject("paga", new Paga());
		return model;
	}

	@RequestMapping(value = "/pagat", method = RequestMethod.GET)
	public ModelAndView pagaList(@ModelAttribute("paga") Paga paga,
			@RequestParam int year, @RequestParam int month) {
		ModelAndView model = new ModelAndView("pagat");
		List<Paga> pagat = pagaService.getPagaMontYear(year, month);
		model.addObject("pagat", pagat);
		return model;
	}

	@RequestMapping(value = "/allpagat", method = RequestMethod.GET)
	public ModelAndView allPagat() {
		ModelAndView model = new ModelAndView("pagat");
		List<Paga> pagat = pagaService.getCurrent();
		model.addObject("pagat", pagat);
		return model;
	}

	@RequestMapping(value = "/searchRaportEmployee", method = RequestMethod.GET)
	public ModelAndView searchPerEmployee() {
		ModelAndView model = new ModelAndView("searchpagaemployee");
		List<Recupero> recuperot = recuperoService.getCurrent();
		List<Muajt> enums = Arrays.asList(Muajt.values());
		List<Year> years = yearService.getCurrent();
		model.addObject("recuperot", recuperot);
		model.addObject("enums", enums);
		System.out.println(enums.size());
		model.addObject("years", years);
		model.addObject("paga", new Paga());
		return model;
	}

	@RequestMapping(value = "/getRaportEmployee", method = RequestMethod.GET)
	public ModelAndView raportPerEmployee(@ModelAttribute("paga") Paga paga,
			@RequestParam int year, @RequestParam int month) {
		ModelAndView model = new ModelAndView("pagat");
		List<Paga> pagat = pagaService.getPagaMontYearPerEmployee(year, month,
				paga.getRecuperoEmployee());
		model.addObject("pagat", pagat);
		return model;
	}

	@RequestMapping("/addpage")
	public String addTimePerday(Model model) {
		model.addAttribute("page", new Paga());
		List<Recupero> recuperot = recuperoService.getCurrent();
		List<Kantier> kantiers = kantierService.getCurrent();
		model.addAttribute("recuperot", recuperot);
		model.addAttribute("kantiers", kantiers);
		return "addpage";

	}

	@RequestMapping(value = "/createpage", method = RequestMethod.POST)
	public String createpagen(@ModelAttribute("page") Paga page,

	BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addpage";
		}
		double gjendjerecupero = page.getRecuperoEmployee()
				.getGjendjerecupero();
		Date date = page.getDate();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		double totalRecupero = gjendjerecupero + page.getRecupero();
		page.setMonth(month);
		page.setYear(year);
		page.setTotalirecupero(totalRecupero);
		page.getRecuperoEmployee().setGjendjerecupero(totalRecupero);
		pagaService.createPage(page);

		return "redirect:/allpagat";
	}

	@RequestMapping(value = "/editPagen/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") int id, ModelMap model,
			HttpServletRequest request) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date now = new Date();
		Date d1 = dateFormat.parse(dateFormat.format(now));
		Date d2 = pagaService.getPaga(id).getCreateDateTime();
		long diff = d1.getTime() - d2.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);

		if (request.isUserInRole("ROLE_DBA") && diffDays > 35) {

			return "deneid";

		}

		model.addAttribute("kantiers", kantierService.getCurrent());

		model.addAttribute("page", pagaService.getPaga(id));
		model.addAttribute("recupero",
				recuperoService.getEmployeeForRecupero(id));
		model.addAttribute("recuperot", pagaService.getRecuperotForPagId(id));

		return "editPagen";

	}

	@RequestMapping(value = "/editPagen/{id}", method = RequestMethod.POST)
	public String postEditPagen(@ModelAttribute("page") Paga page,
			@PathVariable("id") int id, BindingResult result, ModelMap model)
			throws ParseException {

		if (result.hasErrors()) {
			return "editPagen";
		}

		pagaService.saveOrUpdate(id, page);

		return "redirect:/allpagat";
	}

	@RequestMapping("/deletePagen/{id}")
	public String deletePagen(@PathVariable("id") int id, Model model) {
		pagaService.deletePaga(id);
		return "redirect:/allpagat";
	}

}
