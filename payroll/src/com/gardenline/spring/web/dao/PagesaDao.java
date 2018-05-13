package com.gardenline.spring.web.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gardenline.spring.web.utils.CommonUtils;

@Repository
@Transactional
@Component("pagesaDao")
public class PagesaDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7569611324476013291L;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private AktivPasivDao aktivPasivDao;
	
	@Autowired
	private EmployeeBankDao employeeBankDao;
	
	

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Pagesat> getPagesat() {
		Query query = session().createQuery("FROM Pagesat pag ORDER BY pag.date DESC");
		query.setMaxResults(300);
		return query.list();
		
	}

	@SuppressWarnings("unchecked")
	public List<Pagesat> getPagesatAktivPasiv(int id) {
		Query query = session().createQuery("FROM Pagesat pa where pa.aktivPasiv.id=:id");
		query.setParameter("id", id);
		return query.list();
		
	}

	
	public void saveOrUpdate(Pagesat pagesat) {
		session().saveOrUpdate(pagesat);
	}

	public boolean deletePagesen(int id) {
	
		Pagesat pagesat = getPagesat(id);
		AktivPasiv aktivPasiv = pagesat.getAktivPasiv();
		List<Pagesat> pagesatAktivPasiv = getPagesatAktivPasiv(aktivPasiv.getId());
		if(pagesatAktivPasiv.size()>1){
			AktivPasiv aktivPasiv2 = pagesatAktivPasiv.get(pagesatAktivPasiv.size()-1).getAktivPasiv();
			double gjendjeCash = pagesat.getAktivPasiv().getGjendjeCash();
			double gjendjeBank = pagesat.getAktivPasiv().getGjendjeBank();
			double bankAmount = pagesat.getBankAmount();
			double cashAmount = pagesat.getCashAmount();
			double pagesaCash = pagesat.getPagesaCash();
			double pagesaBank = pagesat.getPagesaBank();
			double paradhenie = pagesat.getParadhenie();
			double oldGjendjeCash  = gjendjeCash+pagesaCash+paradhenie-cashAmount;
			double oldGjendjeAmount  = gjendjeBank+pagesaBank-bankAmount;
			aktivPasiv2.setGjendjeCash(oldGjendjeCash);
			aktivPasiv2.setGjendjeBank(oldGjendjeAmount);
			Query query = session().createQuery("delete from Pagesat where id=:id");
			query.setLong("id", id);
			return query.executeUpdate() == 1;
		}
		double gjendjeCash = pagesat.getAktivPasiv().getGjendjeCash();
		double gjendjeBank = pagesat.getAktivPasiv().getGjendjeBank();
		double bankAmount = pagesat.getBankAmount();
		double cashAmount = pagesat.getCashAmount();
		double pagesaCash = pagesat.getPagesaCash();
		double pagesaBank = pagesat.getPagesaBank();
		double paradhenie = pagesat.getParadhenie();
		double oldGjendjeCash  = gjendjeCash+pagesaCash+paradhenie-cashAmount;
		double oldGjendjeAmount  = gjendjeBank+pagesaBank-bankAmount;
		aktivPasiv.setGjendjeBank(oldGjendjeAmount);
		aktivPasiv.setGjendjeCash(oldGjendjeCash);
		Query query = session().createQuery("delete from Pagesat where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Pagesat getPagesat(int id) {
		
		Criteria crit = session().createCriteria(Pagesat.class);
		crit.add(Restrictions.idEq(id));
		return (Pagesat) crit.uniqueResult();
	}


	public AktivPasiv getAktivPasiv(int id){
				Query query = session().createQuery(
				"select pa.aktivPasiv from Pagesat pa where pa.id=:id");
		query.setParameter("id", id);
		return (AktivPasiv) query.uniqueResult();
	}



	@SuppressWarnings("unchecked")
	public List<Paga> getPagatPermonth(int year, int month) {
		Query query = session().createQuery(
				"from Paga pg where pg.year=:year and pg.month=:month");
		query.setParameter("year", year);
		query.setParameter("month", month);
		// query.setParameter("employee", employee);

		return query.list();

	}
	
	
	public void process(List<String> filesPath) throws ParseException {
		List<String[]> readCsv1 =new ArrayList<String[]>();
		// read data
		for (String filePath : filesPath) {
			if (CommonUtils.getFileExtension(filePath).equals("csv")) {
				// read csv file
				List<String[]> readCsv = CommonUtils.readCsv(filePath);
				readCsv1.addAll(readCsv);
			}
		
		}
		// import
		importData(readCsv1);
	}

	public void importData(List<String[]> readCsv) throws ParseException  {
		List<AktivPasiv> aktivPasivet = aktivPasivDao.getAllAktivPasivSituacionet();
		//List<EmployeeBank> empBanks = employeeBankDao.getAllBankEmp();

		for (String[] rows : readCsv) {
			String date = rows[0];
			String sn = rows[1];
			String bankAmountReaded = rows[2];
			String cashAmountReaded = rows[3];
			String pagesaBankReaded = rows[4];
			String pagesaCashReaded = rows[5];
			String paradhenieReaded = rows[6];
			String bankName = rows[7];
			

			AktivPasiv aktivPasivi = getSnAktivPasiv(sn, aktivPasivet);
			List<EmployeeBank> employeeBanks = employeeBankDao.getEmployeeBanks(sn);
			EmployeeBank employeeBank = getEmployeeBankByBank(bankName, employeeBanks);
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date result =df.parse(date);

			Calendar cal = Calendar.getInstance();
			cal.setTime(result);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			
			double bankAmount = Double.parseDouble(bankAmountReaded);
			double cashAmount = Double.parseDouble(cashAmountReaded);
			double pagesaBank = Double.parseDouble(pagesaBankReaded);
			double pagesaCash = Double.parseDouble(pagesaCashReaded);
			double paradhenie = Double.parseDouble(paradhenieReaded);
			double gjendjeBank = aktivPasivi.getGjendjeBank();
			double gjendjeCash =aktivPasivi.getGjendjeCash();
			double totalBank = gjendjeBank+bankAmount-pagesaBank;
			double totalCash  = gjendjeCash +cashAmount-pagesaCash-paradhenie;
			
			Pagesat pagesa = new Pagesat();
			pagesa.setDate(result);
			pagesa.setMonth(month);
			pagesa.setYear(year);
			pagesa.setCashAmount(cashAmount);
			pagesa.setBankAmount(bankAmount);
			pagesa.setPagesaBank(pagesaBank);
			pagesa.setPagesaCash(pagesaCash);
			pagesa.setParadhenie(paradhenie);
			pagesa.setTotalCash(totalCash);
			pagesa.setTotalBank(totalBank);
			pagesa.setEmployeeBank(employeeBank);
			pagesa.setAktivPasiv(aktivPasivi);
			pagesa.getAktivPasiv().setGjendjeBank(totalBank);
			pagesa.getAktivPasiv().setGjendjeCash(totalCash);
			session().save(pagesa);

		}
	}



	
	private EmployeeBank getEmployeeBankByBank(String bankName, List<EmployeeBank> empBanks) {
	for (EmployeeBank empBank : empBanks) {
		if(empBank.getBank().getBankName().equalsIgnoreCase(bankName)){
			return empBank;
		}
	}
		return null;
	}

	private AktivPasiv getSnAktivPasiv(String sn, List<AktivPasiv> aktivPasivet) {
		for (AktivPasiv aktivPasivi : aktivPasivet) {
			if (aktivPasivi.getEmployee().getSecurityNumber().equalsIgnoreCase(sn)) {
				return aktivPasivi;
			}

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Paga> getPagatMonthYear(int year, int month) {
		Query query  = session().createQuery("from Paga pg where pg.year=:year and pg.month=:month");
		query.setParameter("year", year);
		query.setParameter("month", month);
		return query.list();
		
	}

	@SuppressWarnings("unchecked")
	public  List<Paga>  getPagatMonthYearPerEmployee(int year, int month, Recupero recuperoEmployee) {
		Query query  = session().createQuery("from Paga pg where pg.year=:year and pg.month=:month and pg.recuperoEmployee =:recuperoEmployee");
		query.setParameter("year", year);
		query.setParameter("month", month);
		query.setParameter("recuperoEmployee", recuperoEmployee);
		return query.list();
	}

	public void updatePagesen(int id, Pagesat pagesat) {
		
		Pagesat pagesat2 = getPagesat(id);
		double pagesaCash = pagesat2.getPagesaCash();
		double pagesaBank = pagesat2.getPagesaBank();
		double paradhenie = pagesat2.getParadhenie();
		double gjendjeCash = pagesat2.getAktivPasiv().getGjendjeCash();
		double gjendjeBank = pagesat2.getAktivPasiv().getGjendjeBank();
		double bankAmount = pagesat.getBankAmount();
		double cashAmount = pagesat.getCashAmount();
		double oldGjendjeCash  = gjendjeCash+pagesaCash+paradhenie-cashAmount;
		double oldGjendjeAmount  = gjendjeBank+pagesaBank-bankAmount;
		double pagesaBank2 = pagesat.getPagesaBank();
		double pagesaCash2 = pagesat.getPagesaCash();
		double paradhenie2 = pagesat.getParadhenie();
		
		double newTotalGjendjeBank = bankAmount+ oldGjendjeAmount-pagesaBank2;
		double newTotalGjendjeCash = cashAmount +oldGjendjeCash-pagesaCash2-paradhenie2;
		
		pagesat2.setDate(pagesat.getDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(pagesat.getDate());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		pagesat2.setYear(year);
		pagesat2.setMonth(month);
		pagesat2.setBankAmount(pagesat.getBankAmount());
		pagesat2.setCashAmount(pagesat.getCashAmount());
		pagesat2.setPagesaBank(pagesat.getPagesaBank());
		pagesat2.setPagesaCash(pagesat.getPagesaCash());
		pagesat2.setParadhenie(pagesat.getParadhenie());
		pagesat2.setTotalBank(newTotalGjendjeBank);
		pagesat2.setTotalCash(newTotalGjendjeCash);
		
		pagesat2.getAktivPasiv().setGjendjeCash(newTotalGjendjeCash);
		pagesat2.getAktivPasiv().setGjendjeBank(newTotalGjendjeBank);
		pagesat2.setAktivPasiv(pagesat2.getAktivPasiv());
		pagesat2.setEmployeeBank(pagesat.getEmployeeBank());
		
		session().save(pagesat2);
		
		
	}

}
