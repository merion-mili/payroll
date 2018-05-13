package com.gardenline.spring.web.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
@Component("pagaDao")
public class PagaDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7569611324476013291L;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RecuperoDao recuperoDao;

	@Autowired
	private KantierDao kaniterDao;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Paga> getPagat() {
		Query query = session().createQuery("FROM Paga p ORDER BY p.date DESC");
		query.setMaxResults(300);
		return query.list();

	}

	public void saveOrUpdate(Paga paga) {
		session().saveOrUpdate(paga);
	}

	public boolean deleteEntry(int id) {
		Query query = session().createQuery("delete from Paga where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Paga getPaga(int id) {
		Criteria crit = session().createCriteria(Paga.class);
		crit.add(Restrictions.idEq(id));
		return (Paga) crit.uniqueResult();
	}

	public Employee getEmployeeForPagId(int id) {
		Query query = session().createQuery(
				"select employee from Paga p where p.id=:id");
		query.setParameter("id", id);
		Employee employee = (Employee) query.uniqueResult();
		return employee;
	}

	public Recupero getRecuperoPerEmployee(String employee) {
		Query query = session().createQuery(
				"from Recupero rc where rc.employee.securityNumber=:sn");
		query.setParameter("sn", employee);
		return (Recupero) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Recupero> getRecuperotPerPageId(int id) {
		Query query = session().createQuery(
				"select p.recuperoEmployee from Paga p where p.id=:id");
		query.setParameter("id", id);
		return query.list();
	}

	public void updatePaga(int id, Paga paga) {
		Paga paga2 = getPaga(id);
		double gjendjerecupero = paga.getRecuperoEmployee()
				.getGjendjerecupero();
		double totalRecupero = gjendjerecupero
				+ (paga.getRecupero() - paga2.getRecupero());
		String employee = paga.getRecuperoEmployee().getEmployee()
				.getSecurityNumber();
		paga2.setRecuperoEmployee(getRecuperoPerEmployee(employee));
		// paga2.setRecuperoEmployee(paga.getRecuperoEmployee());
		paga2.setDate(paga.getDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(paga.getDate());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		paga2.setMonth(month);
		paga2.setYear(year);
		paga2.setOretepunuara(paga.getOretepunuara());
		paga2.setOretshtese(paga.getOretshtese());
		paga2.setLejeDitore(paga.getLejeDitore());
		paga2.setLejeVjetore(paga.getLejeVjetore());
		paga2.setFesta(paga.getFesta());
		paga2.setBonous(paga.getBonous());
		paga2.setRaportet(paga.getRaportet());
		paga2.setRecupero(paga.getRecupero());
		paga2.setTotalirecupero(totalRecupero);
		paga2.setComment(paga.getComment());
		paga2.setKantier(paga.getKantier());
		paga2.getRecuperoEmployee().setGjendjerecupero(totalRecupero);
		session().save(paga2);
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

	@SuppressWarnings("unchecked")
	public List<Paga> getPagatPermonthEmployee(int year, int month, int empId) {
		Query query = session()
				.createQuery(
						"from Paga pg where pg.year=:year and pg.month=:month and pg.recuperoEmployee.employee.empId=:empId");
		query.setParameter("year", year);
		query.setParameter("month", month);
		query.setParameter("empId", empId);

		return query.list();

	}

	public void process(List<String> filesPath) throws Exception {
		List<String[]> readCsv1 = new ArrayList<String[]>();
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

	public void importData(List<String[]> readCsv) throws Exception {

		List<Recupero> recuperos = recuperoDao.getRecuperot();
		List<Kantier> kantiere = kaniterDao.getKantiere();
		Kantier kantier = null;
		Recupero recuperoEmployee = null;
		int i = 0;

		String viti = "";
		String muaji = "";

		// Map<String, String> mapOfPuna = new HashMap<>();
		Map<String, HashMap<String, String>> datasEntry = new HashMap<>();
		for (String[] rows : readCsv) {

			i = i + 1;
			if (i == 1) {
				muaji = rows[5];

			}

			else if (i == 2) {
				viti = rows[1];

			} else if (i > 7) {
				String name = rows[1];
				recuperoEmployee = getSnByRecupero(name, recuperos);
				if (recuperoEmployee == null) {
					throw new Exception("We couldn't find the Id with number "
							+ name);
				}
				for (int j = 1; j <= 31; j++) {

					String puna = rows[j + 1];
					if (!puna.isEmpty()) {
						String substring = puna.substring(0, 1);
						if (!"r".equalsIgnoreCase(substring)
								&& !"f".equalsIgnoreCase(substring)
								&& !"j".equalsIgnoreCase(substring)) {
							kantier = getKantierByname(substring, kantiere);
							if (kantier == null) {
								throw new Exception("Vlera e punes e " + name
										+ " nuk korrespondon me simbolin "
										+ substring + " ne daten " + j + "/"
										+ muaji + "/" + viti);
							}
						}

					}

					if (datasEntry.isEmpty() || !datasEntry.containsKey(name)) {
						Map<String, String> keyValue = new HashMap<String, String>();
						keyValue.put(j + "/" + muaji + "/" + viti, puna);
						removeNulls(keyValue);
						datasEntry
								.put(name, (HashMap<String, String>) keyValue);
					} else if (datasEntry.containsKey(name)) {
						Map<String, String> keyValue = (HashMap<String, String>) datasEntry
								.get(name);
						keyValue.put(j + "/" + muaji + "/" + viti, puna);

						removeNulls(keyValue);
						keyValue.entrySet()
								.stream()
								.sorted(Map.Entry.comparingByKey())
								.collect(
										Collectors.toMap(Map.Entry::getKey,
												Map.Entry::getValue, (oldValue,
														newValue) -> oldValue,
												LinkedHashMap::new));
						datasEntry
								.put(name, (HashMap<String, String>) keyValue);
						datasEntry
								.entrySet()
								.stream()
								.sorted(Map.Entry.comparingByKey())
								.collect(
										Collectors.toMap(Map.Entry::getKey,
												Map.Entry::getValue, (oldValue,
														newValue) -> oldValue,
												LinkedHashMap::new));
					}

				}

			}

		}

		for (Entry<String, HashMap<String, String>> dateEntry : datasEntry
				.entrySet()) {

			for (Map.Entry<String, String> nameEntry : dateEntry.getValue()
					.entrySet()) {
				String name = dateEntry.getKey();

				recuperoEmployee = getSnByRecupero(name, recuperos);
				String date = nameEntry.getKey();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date result = df.parse(date);
				Calendar cal = Calendar.getInstance();
				cal.setTime(result);
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1;
				String vleraEFushes = nameEntry.getValue();
				// System.out.println(name + "...." + date + "....." +
				// vleraEFushes);
				String pune = "";
				String shtese = "";
				String comment = "Asgje";
				double lejeditore = 0;
				double bonous = 0;
				double oretepunuara = 0;
				double oretShtese = 0;
				double recupero = 0;
				double lejevjetore = 0;
				double festa = 0;
				double raporte = 0;
				if (vleraEFushes.isEmpty() || vleraEFushes == null) {
					oretepunuara = 0;
				} else if (vleraEFushes
						.matches("[a-zA-Z][?\\-[0-9]\\?\\.[0-9]]+")) {
					pune = vleraEFushes.substring(0, 1);
					shtese = vleraEFushes.substring(1, vleraEFushes.length());

					kantier = getKantierByname(pune, kantiere);

					if (pune.matches("r")) {
						recupero = 8 + Double.parseDouble(shtese);

					} else {
						oretepunuara = 8;
						oretShtese = Double.parseDouble(shtese);
					}

				} else if (vleraEFushes.matches("j")) {
					oretepunuara = 0;
				} else if (vleraEFushes.matches("f")) {
					lejevjetore = 8;
				} else if (vleraEFushes.matches("[a-zA-Z]")
						&& vleraEFushes.length() == 1) {
					kantier = getKantierByname(vleraEFushes, kantiere);

					oretepunuara = 8;

				} else if (vleraEFushes.matches("r")) {
					recupero = 8;
				} else {
					throw new Exception("Vlera e fushes te " + name
							+ " nuk korrespondon me simbolet ne daten " + date);
				}

				double totalRecupero = recupero
						+ recuperoEmployee.getGjendjerecupero();

				Paga page = new Paga();
				page.setDate(result);
				page.setMonth(month);
				page.setYear(year);
				page.setOretepunuara(oretepunuara);
				page.setOretshtese(oretShtese);
				page.setLejeDitore(lejeditore);
				page.setRaportet(raporte);
				page.setRecupero(recupero);
				page.setTotalirecupero(totalRecupero);
				page.setLejeVjetore(lejevjetore);
				page.setFesta(festa);
				page.setBonous(bonous);
				page.setKantier(kantier);
				page.setComment(comment);
				page.setRecuperoEmployee(recuperoEmployee);
				page.getRecuperoEmployee().setGjendjerecupero(totalRecupero);
				session().save(page);

			}

		}

	}

	private void removeNulls(Map<String, String> keyValue) {
		Iterator<Map.Entry<String, String>> itr = keyValue.entrySet()
				.iterator();

		while (itr.hasNext()) {
			Map.Entry<String, String> curr = itr.next();
			if (curr.getValue().isEmpty())
				itr.remove();

		}
	}

	private Kantier getKantierByname(String kantiersimbol,
			List<Kantier> kantiere) {
		for (Kantier kantier : kantiere) {
			if (kantier.getSimbol().equalsIgnoreCase(kantiersimbol)) {
				return kantier;
			}
		}
		return null;
	}

	private Recupero getSnByRecupero(String sn, List<Recupero> recuperos) {
		for (Recupero recupero : recuperos) {
			if (recupero.getEmployee().getSecurityNumber().equalsIgnoreCase(sn)) {
				return recupero;
			}

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Paga> getPagatMonthYear(int year, int month) {
		Query query = session().createQuery(
				"from Paga pg where pg.year=:year and pg.month=:month");
		query.setParameter("year", year);
		query.setParameter("month", month);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<Paga> getPagatMonthYearPerEmployee(int year, int month,
			Recupero recuperoEmployee) {
		Query query = session()
				.createQuery(
						"from Paga pg where pg.year=:year and pg.month=:month and pg.recuperoEmployee =:recuperoEmployee");
		query.setParameter("year", year);
		query.setParameter("month", month);
		query.setParameter("recuperoEmployee", recuperoEmployee);
		return query.list();
	}

}
