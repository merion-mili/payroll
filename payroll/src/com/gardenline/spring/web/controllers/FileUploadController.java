package com.gardenline.spring.web.controllers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gardenline.spring.web.dao.FileValidator;
import com.gardenline.spring.web.service.EmployeeBankService;
import com.gardenline.spring.web.service.EmployeeEnabledService;
import com.gardenline.spring.web.service.EmployeeJobService;
import com.gardenline.spring.web.service.EmployeeService;
import com.gardenline.spring.web.service.FileUpload;
import com.gardenline.spring.web.service.PagaService;
import com.gardenline.spring.web.service.PagesaService;
import com.gardenline.spring.web.service.SalaryService;

@Controller
public class FileUploadController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8544417595555287169L;

	@Autowired
	private FileValidator fileValidator;

	@Autowired
	private PagaService pagaService;

	@Autowired
	private PagesaService pagesaService;

	@Autowired
	private EmployeeService employeeSerivce;

	@Autowired
	private EmployeeJobService employeeJobSerivce;

	@Autowired
	private SalaryService salaryService;

	@Autowired
	private EmployeeEnabledService employeeEnabledSerivce;

	@Autowired
	private EmployeeBankService employeeBankSerivce;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));

	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
	public ModelAndView uploadFile() {
		ModelAndView model = new ModelAndView("uploadfile");
		FileUpload fileUpload = new FileUpload();
		model.addObject("fileUpload", fileUpload);
		return model;

	}

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String doUpload(@ModelAttribute("fileUpload") FileUpload fileUpload,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws Exception {
		// validate
		fileValidator.validate(fileUpload, result);

		if (result.hasErrors()) {
			return "uploadfile";

		} else {
			// doupload
			redirectAttributes.addFlashAttribute("fileNames",
					uploadAndImportDb(fileUpload));
			return "redirect:/employees";
		}

	}

	private List<String> uploadAndImportDb(FileUpload fileUpload)
			throws Exception {
		List<String> fileNames = new ArrayList<String>();

		List<String> paths = new ArrayList<String>();

		CommonsMultipartFile[] commonsMultipartFiles = fileUpload.getFiles();

		String filePath = null;

		for (CommonsMultipartFile multipartFile : commonsMultipartFiles) {
			filePath = "E:\\myupload\\" + multipartFile.getOriginalFilename();

			File file = new File(filePath);

			// copy files
			FileCopyUtils.copy(multipartFile.getBytes(), file);
			fileNames.add(multipartFile.getOriginalFilename());

			paths.add(filePath);
		}

		// process parse and import data
		pagaService.process(paths);
		return fileNames;

	}

	@RequestMapping(value = "/uploadpagesen", method = RequestMethod.GET)
	public ModelAndView uploadPagesen() {
		ModelAndView model = new ModelAndView("uploadpagesen");
		FileUpload pagesenUpload = new FileUpload();
		model.addObject("pagesenUpload", pagesenUpload);
		return model;

	}

	@RequestMapping(value = "/doUploadPagesen", method = RequestMethod.POST)
	public String doUploadPagesen(
			@ModelAttribute("pagesenUpload") FileUpload fileUpload,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws IOException, ParseException {
		// validate
		fileValidator.validate(fileUpload, result);

		if (result.hasErrors()) {
			return "uploadpagesen";

		} else {

			redirectAttributes.addFlashAttribute("fileNames",
					uploadAndImportPagesen(fileUpload));
			return "redirect:/pagesat";
		}

	}

	private List<String> uploadAndImportPagesen(FileUpload fileUpload)
			throws IOException, ParseException {
		List<String> fileNames = new ArrayList<String>();

		List<String> paths = new ArrayList<String>();

		CommonsMultipartFile[] commonsMultipartFiles = fileUpload.getFiles();

		String filePath = null;

		for (CommonsMultipartFile multipartFile : commonsMultipartFiles) {
			filePath = "E:\\myupload\\" + multipartFile.getOriginalFilename();

			File file = new File(filePath);

			// copy files
			FileCopyUtils.copy(multipartFile.getBytes(), file);
			fileNames.add(multipartFile.getOriginalFilename());

			paths.add(filePath);
		}

		// process parse and import data
		pagesaService.process(paths);
		return fileNames;

	}

	@RequestMapping(value = "/uploadEmployeeJob", method = RequestMethod.GET)
	public ModelAndView uploadEmployeeJob() {
		ModelAndView model = new ModelAndView("uploadempjob");
		FileUpload fileUpload = new FileUpload();
		model.addObject("fileUpload", fileUpload);
		return model;

	}

	@RequestMapping(value = "/doUploadEmployeeJob", method = RequestMethod.POST)
	public String doUploadEMployeeJob(
			@ModelAttribute("fileUpload") FileUpload fileUpload,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws IOException, ParseException {
		// validate
		fileValidator.validate(fileUpload, result);

		if (result.hasErrors()) {
			return "uploadempjob";

		} else {
			// doupload
			redirectAttributes.addFlashAttribute("fileNames",
					uploadAndImportDbEmployeeJob(fileUpload));
			return "redirect:/employees";
		}

	}

	private List<String> uploadAndImportDbEmployeeJob(FileUpload fileUpload)
			throws IOException, ParseException {
		List<String> fileNames = new ArrayList<String>();

		List<String> paths = new ArrayList<String>();

		CommonsMultipartFile[] commonsMultipartFiles = fileUpload.getFiles();

		String filePath = null;

		for (CommonsMultipartFile multipartFile : commonsMultipartFiles) {
			filePath = "E:\\myupload\\" + multipartFile.getOriginalFilename();

			File file = new File(filePath);

			// copy files
			FileCopyUtils.copy(multipartFile.getBytes(), file);
			fileNames.add(multipartFile.getOriginalFilename());

			paths.add(filePath);
		}

		// process parse and import data
		employeeJobSerivce.process(paths);
		return fileNames;

	}

	@RequestMapping(value = "/uploadSalary", method = RequestMethod.GET)
	public ModelAndView uploadSalary() {
		ModelAndView model = new ModelAndView("uploadsalary");
		FileUpload fileUpload = new FileUpload();
		model.addObject("fileUpload", fileUpload);
		return model;

	}

	@RequestMapping(value = "/doUploadSalary", method = RequestMethod.POST)
	public String doUploadSalary(
			@ModelAttribute("fileUpload") FileUpload fileUpload,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws IOException, ParseException {
		// validate
		fileValidator.validate(fileUpload, result);

		if (result.hasErrors()) {
			return "uploadsalary";

		} else {
			// doupload
			redirectAttributes.addFlashAttribute("fileNames",
					uploadAndImportDbSalary(fileUpload));
			return "redirect:/employees";
		}

	}

	private List<String> uploadAndImportDbSalary(FileUpload fileUpload)
			throws IOException, ParseException {
		List<String> fileNames = new ArrayList<String>();

		List<String> paths = new ArrayList<String>();

		CommonsMultipartFile[] commonsMultipartFiles = fileUpload.getFiles();

		String filePath = null;

		for (CommonsMultipartFile multipartFile : commonsMultipartFiles) {
			filePath = "E:\\myupload\\" + multipartFile.getOriginalFilename();

			File file = new File(filePath);

			// copy files
			FileCopyUtils.copy(multipartFile.getBytes(), file);
			fileNames.add(multipartFile.getOriginalFilename());

			paths.add(filePath);
		}

		// process parse and import data
		salaryService.process(paths);
		return fileNames;

	}

	@RequestMapping(value = "/uploadEmployeeEnabled", method = RequestMethod.GET)
	public ModelAndView uploadEmployeeEnabled() {
		ModelAndView model = new ModelAndView("uploadempenabled");
		FileUpload fileUpload = new FileUpload();
		model.addObject("fileUpload", fileUpload);
		return model;

	}

	@RequestMapping(value = "/doUploadEmployeeEnabled", method = RequestMethod.POST)
	public String doUploadEmployeeEnabled(
			@ModelAttribute("fileUpload") FileUpload fileUpload,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws IOException, ParseException {
		// validate
		fileValidator.validate(fileUpload, result);

		if (result.hasErrors()) {
			return "uploadempenabled";

		} else {
			// doupload
			redirectAttributes.addFlashAttribute("fileNames",
					uploadAndImportDbEmployeeEnabled(fileUpload));
			return "redirect:/employees";
		}

	}

	private List<String> uploadAndImportDbEmployeeEnabled(FileUpload fileUpload)
			throws IOException, ParseException {
		List<String> fileNames = new ArrayList<String>();

		List<String> paths = new ArrayList<String>();

		CommonsMultipartFile[] commonsMultipartFiles = fileUpload.getFiles();

		String filePath = null;

		for (CommonsMultipartFile multipartFile : commonsMultipartFiles) {
			filePath = "E:\\myupload\\" + multipartFile.getOriginalFilename();

			File file = new File(filePath);

			// copy files
			FileCopyUtils.copy(multipartFile.getBytes(), file);
			fileNames.add(multipartFile.getOriginalFilename());

			paths.add(filePath);
		}

		// process parse and import data
		employeeEnabledSerivce.process(paths);
		return fileNames;

	}

	@RequestMapping(value = "/uploadEmployeeBank", method = RequestMethod.GET)
	public ModelAndView uploadEmployeeBank() {
		ModelAndView model = new ModelAndView("uploadempbank");
		FileUpload fileUpload = new FileUpload();
		model.addObject("fileUpload", fileUpload);
		return model;

	}

	@RequestMapping(value = "/doUploadEmployeeBank", method = RequestMethod.POST)
	public String doUploadEMployeeBank(
			@ModelAttribute("fileUpload") FileUpload fileUpload,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws IOException, ParseException {
		// validate
		fileValidator.validate(fileUpload, result);

		if (result.hasErrors()) {
			return "uploadempbank";

		} else {
			// doupload
			redirectAttributes.addFlashAttribute("fileNames",
					uploadAndImportDbEmployeeBank(fileUpload));
			return "redirect:/employees";
		}

	}

	private List<String> uploadAndImportDbEmployeeBank(FileUpload fileUpload)
			throws IOException, ParseException {
		List<String> fileNames = new ArrayList<String>();

		List<String> paths = new ArrayList<String>();

		CommonsMultipartFile[] commonsMultipartFiles = fileUpload.getFiles();

		String filePath = null;

		for (CommonsMultipartFile multipartFile : commonsMultipartFiles) {
			filePath = "E:\\myupload\\" + multipartFile.getOriginalFilename();

			File file = new File(filePath);

			// copy files
			FileCopyUtils.copy(multipartFile.getBytes(), file);
			fileNames.add(multipartFile.getOriginalFilename());

			paths.add(filePath);
		}

		// process parse and import data
		employeeBankSerivce.process(paths);
		return fileNames;

	}

	@RequestMapping(value = "/uploadEmployee", method = RequestMethod.GET)
	public ModelAndView uploadEmployee() {
		ModelAndView model = new ModelAndView("uploademployee");
		FileUpload fileUpload = new FileUpload();
		model.addObject("fileUpload", fileUpload);
		return model;

	}

	@RequestMapping(value = "/doUploadEmployee", method = RequestMethod.POST)
	public String doUploadEmployee(
			@ModelAttribute("fileUpload") FileUpload fileUpload,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws IOException, ParseException {
		// validate
		fileValidator.validate(fileUpload, result);

		if (result.hasErrors()) {
			return "uploademployee";

		} else {
			// doupload
			redirectAttributes.addFlashAttribute("fileNames",
					uploadAndImportDbEmployee(fileUpload));
			return "redirect:/employees";
		}

	}

	private List<String> uploadAndImportDbEmployee(FileUpload fileUpload)
			throws IOException, ParseException {
		List<String> fileNames = new ArrayList<String>();

		List<String> paths = new ArrayList<String>();

		CommonsMultipartFile[] commonsMultipartFiles = fileUpload.getFiles();

		String filePath = null;

		for (CommonsMultipartFile multipartFile : commonsMultipartFiles) {
			filePath = "E:\\myupload\\" + multipartFile.getOriginalFilename();

			File file = new File(filePath);

			// copy files
			FileCopyUtils.copy(multipartFile.getBytes(), file);
			fileNames.add(multipartFile.getOriginalFilename());

			paths.add(filePath);
		}

		// process parse and import data
		employeeSerivce.process(paths);
		return fileNames;

	}

}
