package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.EmpDao;
import com.gardenline.spring.web.dao.EmployeeJob;
import com.gardenline.spring.web.dao.EmployeeJobDao;
import com.gardenline.spring.web.dao.EmployeeSalary;
import com.gardenline.spring.web.dao.EmployeeSalaryDao;
import com.gardenline.spring.web.dao.EmployeeSalaryStanding;
import com.gardenline.spring.web.dao.Muajt;
import com.gardenline.spring.web.dao.Paga;
import com.gardenline.spring.web.dao.PagaDao;
import com.gardenline.spring.web.dao.Recupero;
import com.gardenline.spring.web.dao.Siguracionet;
import com.gardenline.spring.web.dao.SiguracionetDao;
import com.gardenline.spring.web.dao.Taksat;
import com.gardenline.spring.web.dao.TaksatDao;

@Service(value = "employeeSalaryService")
public class EmployeeSalaryService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8660832514997802376L;

	@Autowired
	private PagaDao pagaDao;

	@Autowired
	private EmpDao employeeDao;

	@Autowired
	private SiguracionetDao siguracionetDao;

	@Autowired
	private TaksatDao taksatDao;

	@Autowired
	private EmployeeJobDao employeeJobDao;

	@Autowired
	private EmployeeSalaryDao employeeSalaryDao;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String value = "";

	public EmployeeSalaryStanding getStandingPerEmployee(int year, int month,
			Recupero recuperoEmployee) throws ParseException {
		List<Paga> pagat = pagaDao.getPagatMonthYearPerEmployee(year, month,
				recuperoEmployee);

		EmployeeSalaryStanding item = new EmployeeSalaryStanding();

		Siguracionet siguracioneninperYear = getSiguracioneninPerYear(year);
		Taksat taksatperYear = getTaksatperYear(year);
		for (Paga paga : pagat) {

			int empId = paga.getRecuperoEmployee().getEmployee().getEmpId();
			String securityNumber = paga.getRecuperoEmployee().getEmployee()
					.getSecurityNumber();
			String firstName = paga.getRecuperoEmployee().getEmployee()
					.getFirstName();
			String lastName = paga.getRecuperoEmployee().getEmployee()
					.getLastName();

			Date d2 = paga.getRecuperoEmployee().getEmployee()
					.getKontrateDate();
			int yearsSince = yearsSince(d2);
			double pervoja = paga.getRecuperoEmployee().getEmployee()
					.getPervoja();

			item.setSn(securityNumber);
			item.setFirstName(firstName);
			item.setLastName(lastName);

			Date kontrateDate = paga.getRecuperoEmployee().getEmployee()
					.getKontrateDate();

			// Date lastDateOfMonth = getLastDateOfMonth(kontrateDate);
			int workingDays = calculateDuration(kontrateDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(kontrateDate);
			int kontrateMonth = calendar.get(Calendar.MONTH);
			int kontrateYear = calendar.get(Calendar.YEAR);

			long numberOfWorkingsDay = pagat.stream()
					.filter(s -> s.getOretepunuara() > 0).count();
			item.setDitetEPunuara(numberOfWorkingsDay);

			long numberOfFestave = pagat.stream().filter(s -> s.getFesta() > 0)
					.count();
			item.setDitetEfestave(numberOfFestave);

			double totalPervoja = pervoja + yearsSince;

			Double totalOfOreveTePunuaraSera = pagat
					.stream()
					.filter(s -> s.getKantier().getKantierName().equals("Sera"))
					.collect(Collectors.summingDouble(Paga::getOretepunuara));
			item.setTotalhoursSera(totalOfOreveTePunuaraSera);

			Double totalOfOreveTePunuaraKaniter = pagat
					.stream()
					.filter(s -> !s.getKantier().getKantierName()
							.equals("Sera"))
					.collect(Collectors.summingDouble(Paga::getOretepunuara));
			item.setTotalhoursKantier(totalOfOreveTePunuaraKaniter);

			Double totalOfOreveShteseSera = pagat
					.stream()
					.filter(s -> s.getKantier().getKantierName().equals("Sera"))
					.collect(Collectors.summingDouble(Paga::getOretshtese));

			item.setTotaloretshteseSera(totalOfOreveShteseSera);

			Double totalOfFestaSera = pagat
					.stream()
					.filter(s -> !s.getKantier().getKantierName()
							.equals("Sera"))
					.collect(Collectors.summingDouble(Paga::getFesta));

			Double totalOfFestaKantier = pagat
					.stream()
					.filter(s -> !s.getKantier().getKantierName()
							.equals("Sera"))
					.collect(Collectors.summingDouble(Paga::getFesta));

			Double totalOfLejeveDitoreSera = pagat
					.stream()
					.filter(s -> s.getKantier().getKantierName().equals("Sera"))
					.collect(Collectors.summingDouble(Paga::getLejeDitore));

			Double totalOfLejeveDitorekantier = pagat
					.stream()
					.filter(s -> !s.getKantier().getKantierName()
							.equals("Sera"))
					.collect(Collectors.summingDouble(Paga::getLejeDitore));
			Double totalLejeDitore = totalOfLejeveDitorekantier
					+ totalOfLejeveDitoreSera;

			List<Date> collect = pagat.stream()
					.filter(s -> s.getLejeVjetore() > 0)
					.sorted(Comparator.comparing(Paga::getDate))
					.map(Paga::getDate).collect(Collectors.toList());
			if (collect.isEmpty()) {
				value = null;
			} else {
				Date date = collect.get(0);
				Date date2 = collect.get(collect.size() - 1);
				String startvacation = sdf.format(date);
				String endvacation = sdf.format(date2);
				value = value + startvacation + "-" + endvacation;
			}

			item.setIntervallejedate(value);

			item.setTotalLejeDitore(totalLejeDitore);

			Double totalOfLejesVjetore = pagat.stream().collect(
					Collectors.summingDouble(Paga::getLejeVjetore));

			Double bonous = pagat.stream().collect(
					Collectors.summingDouble(Paga::getBonous));
			item.setBonous(bonous);

			Double totalHourOfRaporteve = pagat.stream().collect(
					Collectors.summingDouble(Paga::getRaportet));

			long dayOfRaporteve = pagat.stream()
					.filter(s -> s.getRaportet() > 0).count();

			item.setTotalRaportet(dayOfRaporteve);

			long lejeVjetore = pagat.stream()
					.filter(s -> s.getLejeVjetore() > 0).count();

			item.setDitetLejeVjetore(lejeVjetore);

			Double totalOfRecuperoPermonth = pagat.stream().collect(
					Collectors.summingDouble(Paga::getRecupero));

			item.setTotalRecupero(totalOfRecuperoPermonth);

			Double gjedjeRecupero = pagat.stream()
					.map(Paga::getRecuperoEmployee)
					.mapToDouble(Recupero::getGjendjerecupero)
					.reduce((a, b) -> b).orElse((double) 0);

			item.setGjendjaRecupero(gjedjeRecupero);

			List<EmployeeSalary> emplSalaries = employeeSalaryDao
					.getEmployeesSalaries(empId);

			int day = 1;

			Calendar c2 = Calendar.getInstance();
			c2.set(year, month, day);

			Date startD = sdf.parse(sdf.format(c2.getTime()));

			EmployeeSalary salary = employeeSalaryDao.getPaga(startD, empId);

			double pagahourkontrate = salary.getPagahourkontrate();
			double pagasera = salary.getPagasera();
			double pagakantier = salary.getPagakontrate();

			double pagaraporte = 0;

			if (totalPervoja > 10) {

				pagaraporte = (totalHourOfRaporteve * pagahourkontrate) * 0.8;

			} else {

				pagaraporte = (totalHourOfRaporteve * pagahourkontrate) * 0.7;

			}

			double totalpalejeperregjister = (totalOfOreveTePunuaraSera
					+ totalOfLejeveDitoreSera + totalOfFestaSera)
					* pagahourkontrate;

			item.setPagapalejeregjister(totalpalejeperregjister);

			double totalpaLejeSera = (totalOfOreveTePunuaraSera
					+ totalOfOreveShteseSera + totalOfLejeveDitoreSera + totalOfFestaSera)
					* pagasera + pagaraporte;
			double totalpaLejeKantier = (totalOfOreveTePunuaraKaniter
					+ totalOfFestaKantier + totalOfLejeveDitorekantier)
					* pagakantier + pagaraporte;

			double pagaLejeVjetoreSera = 0;
			double pagabaze = salary.getPagakontrate();

			if (emplSalaries.size() != 1) {
				Date startDate = emplSalaries.get(emplSalaries.size() - 1)
						.getStartDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				int year2 = cal.get(Calendar.YEAR);
				if (year2 != year) {

					pagaLejeVjetoreSera = emplSalaries.get(
							emplSalaries.size() - 1).getPagasera();

				} else {
					pagaLejeVjetoreSera = emplSalaries.get(
							emplSalaries.size() - 2).getPagasera();

				}

			} else {
				pagaLejeVjetoreSera = emplSalaries.get(emplSalaries.size() - 1)
						.getPagasera();

			}

			double pagalejeSera = pagaLejeVjetoreSera * totalOfLejesVjetore;
			item.setPagalejevjetore(pagalejeSera);

			double totalBrutoSera = totalpaLejeSera + pagalejeSera;
			double totalBrutoKantier = totalpaLejeKantier + pagalejeSera;

			double totalBruto = totalBrutoSera + totalBrutoKantier;

			item.setBrutoSalary(totalBruto);
			double bankAmount = 0;

			if (totalBruto <= pagabaze) {

				bankAmount = totalBruto;

			} else {

				bankAmount = pagabaze;
			}

			double cashAmount = totalBruto - bankAmount;

			item.setPagabaze(pagabaze);
			item.setBankAmount(bankAmount);
			item.setCashAmount(cashAmount);

			double siguracione = getSiguracionet(bankAmount, kontrateYear,
					kontrateMonth, year, month, siguracioneninperYear,
					workingDays);
			double taksat = getTaksat(bankAmount, taksatperYear);

			item.setSigurimet(siguracione);
			item.setTaksat(taksat);
			double pagaNeto = bankAmount - (siguracione + taksat);
			item.setPaganeto(pagaNeto);

			double totalNetoCash = pagaNeto + cashAmount;
			item.setTotalNetoCash(totalNetoCash);

		}

		return item;

	}

	public List<EmployeeSalaryStanding> getStanding(int year, int month)
			throws ParseException {
		List<Paga> pagat = pagaDao.getPagatPermonth(year, month);

		List<EmployeeSalaryStanding> standingitems = new ArrayList<EmployeeSalaryStanding>();

		Siguracionet siguracioneninperYear = getSiguracioneninPerYear(year);
		Taksat taksatperYear = getTaksatperYear(year);

		for (Paga paga : pagat) {

			String securityNumber = paga.getRecuperoEmployee().getEmployee()
					.getSecurityNumber();
			String firstName = paga.getRecuperoEmployee().getEmployee()
					.getFirstName();
			String lastName = paga.getRecuperoEmployee().getEmployee()
					.getLastName();
			int empId = paga.getRecuperoEmployee().getEmployee().getEmpId();
			Date kontrateDate = paga.getRecuperoEmployee().getEmployee()
					.getKontrateDate();

			int workingDays = calculateDuration(kontrateDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(kontrateDate);
			int kontrateMonth = calendar.get(Calendar.MONTH);
			int kontrateYear = calendar.get(Calendar.YEAR);

			Date d2 = paga.getRecuperoEmployee().getEmployee()
					.getKontrateDate();
			int yearsSince = yearsSince(d2);
			double pervoja = paga.getRecuperoEmployee().getEmployee()
					.getPervoja();

			long count = standingitems.stream()
					.filter(s -> s.getSn().equals(securityNumber)).count();
			if (count == 0) {

				EmployeeSalaryStanding item = new EmployeeSalaryStanding();

				item.setSn(securityNumber);
				item.setFirstName(firstName);
				item.setLastName(lastName);

				long numberOfWorkingsDay = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> s.getOretepunuara() > 0).count();
				item.setDitetEPunuara(numberOfWorkingsDay);

				long numberOfFestave = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> s.getFesta() > 0).count();
				item.setDitetEfestave(numberOfFestave);

				double totalPervoja = pervoja + yearsSince;

				Double totalOfOreveTePunuaraSera = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> s.getKantier().getKantierName()
								.equals("Sera"))
						.collect(
								Collectors.summingDouble(Paga::getOretepunuara));
				item.setTotalhoursSera(totalOfOreveTePunuaraSera);

				Double totalOfOreveTePunuaraKaniter = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> !s.getKantier().getKantierName()
								.equals("Sera"))
						.collect(
								Collectors.summingDouble(Paga::getOretepunuara));
				item.setTotalhoursKantier(totalOfOreveTePunuaraKaniter);

				Double totalOfOreveShteseSera = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> s.getKantier().getKantierName()
								.equals("Sera"))
						.collect(Collectors.summingDouble(Paga::getOretshtese));

				item.setTotaloretshteseSera(totalOfOreveShteseSera);

				Double totalOfFestaSera = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> !s.getKantier().getKantierName()
								.equals("Sera"))
						.collect(Collectors.summingDouble(Paga::getFesta));

				Double totalOfFestaKantier = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> !s.getKantier().getKantierName()
								.equals("Sera"))
						.collect(Collectors.summingDouble(Paga::getFesta));

				Double totalOfLejeveDitoreSera = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> s.getKantier().getKantierName()
								.equals("Sera"))
						.collect(Collectors.summingDouble(Paga::getLejeDitore));

				Double totalOfLejeveDitorekantier = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> !s.getKantier().getKantierName()
								.equals("Sera"))
						.collect(Collectors.summingDouble(Paga::getLejeDitore));
				Double totalLejeDitore = totalOfLejeveDitorekantier
						+ totalOfLejeveDitoreSera;

				item.setTotalLejeDitore(totalLejeDitore);

				Double totalOfLejesVjetore = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.collect(Collectors.summingDouble(Paga::getLejeVjetore));

				Double bonous = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.collect(Collectors.summingDouble(Paga::getBonous));
				item.setBonous(bonous);

				Double totalHourOfRaporteve = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.collect(Collectors.summingDouble(Paga::getRaportet));

				long dayOfRaporteve = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> s.getRaportet() > 0).count();

				item.setTotalRaportet(dayOfRaporteve);

				long lejeVjetore = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.filter(s -> s.getLejeVjetore() > 0).count();

				item.setDitetLejeVjetore(lejeVjetore);

				Double totalOfRecuperoPermonth = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.collect(Collectors.summingDouble(Paga::getRecupero));

				item.setTotalRecupero(totalOfRecuperoPermonth);

				Double gjedjeRecupero = pagat
						.stream()
						.filter(s -> s.getRecuperoEmployee().getEmployee()
								.getEmpId() == empId)
						.map(Paga::getRecuperoEmployee)
						.mapToDouble(Recupero::getGjendjerecupero)
						.reduce((a, b) -> b).orElse((double) 0);

				item.setGjendjaRecupero(gjedjeRecupero);

				List<EmployeeSalary> emplSalaries = employeeSalaryDao
						.getEmployeesSalaries(empId);

				int day = 1;

				Calendar c1 = Calendar.getInstance();
				c1.set(year, month, day);

				Date startD = sdf.parse(sdf.format(c1.getTime()));

				EmployeeSalary salary = employeeSalaryDao
						.getPaga(startD, empId);

				double pagahourkontrate = salary.getPagahourkontrate();
				double pagasera = salary.getPagasera();
				double pagakantier = salary.getPagakontrate();

				double pagaraporte = 0;

				if (totalPervoja > 10) {

					pagaraporte = (totalHourOfRaporteve * pagahourkontrate) * 0.8;

				} else {

					pagaraporte = (totalHourOfRaporteve * pagahourkontrate) * 0.7;

				}

				double totalpaLejeSera = (totalOfOreveTePunuaraSera
						+ totalOfOreveShteseSera + totalOfLejeveDitoreSera + totalOfFestaSera)
						* pagasera + pagaraporte;
				double totalpaLejeKantier = (totalOfOreveTePunuaraKaniter
						+ totalOfFestaKantier + totalOfLejeveDitorekantier)
						* pagakantier + pagaraporte;

				double totalpalejeperregjister = (totalOfOreveTePunuaraSera
						+ totalOfLejeveDitoreSera + totalOfFestaSera)
						* pagahourkontrate;

				item.setPagapalejeregjister(totalpalejeperregjister);

				double pagaLejeVjetoreSera = 0;
				double pagabaze = salary.getPagakontrate();
				if (emplSalaries.size() != 1) {
					Date startDate = emplSalaries.get(emplSalaries.size() - 1)
							.getStartDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(startDate);
					int year2 = cal.get(Calendar.YEAR);
					if (year2 != year) {

						pagaLejeVjetoreSera = emplSalaries.get(
								emplSalaries.size() - 1).getPagasera();

					} else {
						pagaLejeVjetoreSera = emplSalaries.get(
								emplSalaries.size() - 2).getPagasera();

					}

				} else {
					pagaLejeVjetoreSera = emplSalaries.get(
							emplSalaries.size() - 1).getPagasera();

				}

				double pagalejeSera = pagaLejeVjetoreSera * totalOfLejesVjetore;

				item.setPagalejevjetore(pagalejeSera);

				double totalBrutoSera = totalpaLejeSera + pagalejeSera;
				double totalBrutoKantier = totalpaLejeKantier + pagalejeSera;

				double totalBruto = totalBrutoSera + totalBrutoKantier;

				item.setBrutoSalary(totalBruto);
				double bankAmount = 0;

				if (totalBruto <= pagabaze) {

					bankAmount = totalBruto;

				} else {

					bankAmount = pagabaze;
				}

				double cashAmount = totalBruto - bankAmount;

				item.setPagabaze(pagabaze);
				item.setBankAmount(bankAmount);
				item.setCashAmount(cashAmount);

				double siguracione = getSiguracionet(bankAmount, kontrateYear,
						kontrateMonth, year, month, siguracioneninperYear,
						workingDays);
				double taksat = getTaksat(bankAmount, taksatperYear);
				item.setSigurimet(siguracione);
				item.setTaksat(taksat);
				double pagaNeto = bankAmount - (siguracione + taksat);
				item.setPaganeto(pagaNeto);

				double totalNetoCash = pagaNeto + cashAmount;
				item.setTotalNetoCash(totalNetoCash);

				standingitems.add(item);
			}

		}

		return standingitems
				.stream()
				.sorted(Comparator
						.comparing(EmployeeSalaryStanding::getFirstName))
				.collect(Collectors.toList());
	}

	private double getSiguracionet(double bankAmount, int kontrateYear,
			int kontrateMonth, int year, int month,
			Siguracionet siguracioneninperYear, int workingDays) {

		double siguracione = 0;
		if (bankAmount < siguracioneninperYear.getPageminimale()
				&& kontrateMonth == month && kontrateYear == year
				&& workingDays < 26) {
			siguracione = calculateSiguracionetPagMinBankAmount(bankAmount,
					siguracioneninperYear);

		} else if (bankAmount < siguracioneninperYear.getPageminimale()) {
			siguracione = calculateSiguracionetPagMin(siguracioneninperYear);

		} else if (bankAmount > siguracioneninperYear.getPageminimale()
				&& bankAmount < siguracioneninperYear.getPagemaksimale()) {
			siguracione = calculateSiguracionetPagMinBankAmount(bankAmount,
					siguracioneninperYear);

		} else if (bankAmount > siguracioneninperYear.getPagemaksimale()) {
			siguracione = calculateSiguracionetPagMax(bankAmount,
					siguracioneninperYear);
		}

		return siguracione;
	}

	private double calculateSiguracionetPagMax(double bankAmount,
			Siguracionet siguracioneninperYear) {
		double siguracione;
		siguracione = (double) (siguracioneninperYear.getKfsigshoq()
				* siguracioneninperYear.getPagemaksimale() / 100.0f + bankAmount
				* siguracioneninperYear.getKfsigshen() / 100.0f);
		return siguracione;
	}

	private double calculateSiguracionetPagMin(
			Siguracionet siguracioneninperYear) {
		double siguracione;
		siguracione = (double) (siguracioneninperYear.getPageminimale() * (siguracioneninperYear
				.getKfsigshoq() / 100.0f + siguracioneninperYear.getKfsigshen() / 100.0f));
		return siguracione;
	}

	private double calculateSiguracionetPagMinBankAmount(double bankAmount,
			Siguracionet siguracioneninperYear) {
		double siguracione;
		siguracione = (double) (bankAmount * (siguracioneninperYear
				.getKfsigshoq() / 100.0f + siguracioneninperYear.getKfsigshen() / 100.0f));
		return siguracione;
	}

	private double getTaksat(double bankAmount, Taksat taksatperYear) {
		double taksat = 0;
		if (bankAmount <= taksatperYear.getPageminimale()) {
			taksat = 0;
		}

		else if (bankAmount > taksatperYear.getPageminimale()
				&& bankAmount <= taksatperYear.getPagemaksimale()) {
			taksat = (double) ((bankAmount - taksatperYear.getPageminimale()) * (taksatperYear
					.getKfpagmin() / 100.0f));
		} else if (bankAmount > taksatperYear.getPagemaksimale()) {

			taksat = (double) ((bankAmount - taksatperYear.getPagemaksimale()) * (taksatperYear
					.getKfpagmax() / 100.0f));
		}

		return taksat;
	}

	public static int yearsSince(Date pastDate) {
		Calendar present = Calendar.getInstance();
		Calendar past = Calendar.getInstance();
		past.setTime(pastDate);

		int years = 0;

		while (past.before(present)) {
			past.add(Calendar.YEAR, 1);
			if (past.before(present)) {
				years++;
			}
		}
		return years;
	}

	public EmployeeRegister getEmployeeRegister(int year,
			Recupero recuperoEmployee) throws ParseException {
		List<Muajt> muajt = Arrays.asList(Muajt.values());
		muajt.stream().sorted(Comparator.comparing(Muajt::getKey))
				.collect(Collectors.toList());
		Map<Muajt, MonthsEmployeeRegister> empregisterpermuaj = new HashMap<Muajt, MonthsEmployeeRegister>();
		EmployeeRegister empregister = new EmployeeRegister();
		String jobValue = "";
		String pagaK = "";
		for (Muajt muaj : muajt) {

			EmployeeSalaryStanding standingPerEmployee = getStandingPerEmployee(
					year, muaj.getValue(), recuperoEmployee);
			MonthsEmployeeRegister monthemployeeregister = new MonthsEmployeeRegister();
			double pagapalejevjetore = standingPerEmployee
					.getPagapalejeregjister();
			double pagalejevjetore = standingPerEmployee.getPagalejevjetore();
			String intervallejedate = standingPerEmployee.getIntervallejedate();
			monthemployeeregister.setPagaleje(pagalejevjetore);
			monthemployeeregister.setPagapaleje(pagapalejevjetore);
			monthemployeeregister.setIntervaldate(intervallejedate);

			empregisterpermuaj.put(muaj, monthemployeeregister);

		}
		empregisterpermuaj
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(
						Collectors.toMap(Map.Entry::getKey,
								Map.Entry::getValue,
								(oldValue, newValue) -> oldValue,
								LinkedHashMap::new));
		List<EmployeeJob> empJobs = employeeJobDao
				.getEmployeesJobs(recuperoEmployee.getEmployee().getEmpId());

		for (EmployeeJob employeeJob : empJobs) {
			jobValue = jobValue + employeeJob.getJob().getJobDescription()
					+ " ";
		}

		List<EmployeeSalary> empSalaries = employeeSalaryDao
				.getEmployeesSalaries(recuperoEmployee.getEmployee().getEmpId());

		for (EmployeeSalary employeeSalaires : empSalaries) {
			pagaK = pagaK + employeeSalaires.getPagakontrate() + " ";
		}
		empregister.setJobName(jobValue);
		empregister.setPagaKontrate(pagaK);
		empregister.setFirstName(recuperoEmployee.getEmployee().getFirstName());
		empregister.setLastName(recuperoEmployee.getEmployee().getLastName());
		empregister.setSn(recuperoEmployee.getEmployee().getSecurityNumber());
		empregister.setYear(year);
		empregister
				.setBirthDate(recuperoEmployee.getEmployee().getBirsthDate());
		empregister.setEmpregisterpermuaj(empregisterpermuaj);

		return empregister;

	}

	public static int calculateDuration(Date startDate) {

		Date endDate = getLastDateOfMonth(startDate);
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		do {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				workDays++;
			}
		} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

		return workDays;
	}

	public static Date getLastDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	private Siguracionet getSiguracioneninPerYear(int year) {
		Siguracionet siguracioninPerYear = siguracionetDao
				.getSiguracioninPerYear(year);
		return siguracioninPerYear;
	}

	private Taksat getTaksatperYear(int year) {
		Taksat taksatPerYear = taksatDao.getTaksatPerYear(year);
		return taksatPerYear;
	}

}
