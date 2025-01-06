package org.damp.proiect;

import org.damp.proiect.Service.implementari.BeneficiarService;
import org.damp.proiect.Service.interfete.IContractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class DatabaseConnectionTest {

	@Autowired
	private DataSource dataSource; // Injectează sursa de date (DataSource)

	@Autowired
	private JdbcTemplate jdbcTemplate; // Injectează JdbcTemplate pentru interacțiuni cu baza de date

	@Test
	public void testDatabaseConnection() {
		try (Connection connection = dataSource.getConnection()) {
			System.out.println("Conexiune reușită la baza de date!");
			System.out.println("URL conexiune: " + connection.getMetaData().getURL());
			System.out.println("Baza de date: " + connection.getMetaData().getDatabaseProductName());
			System.out.println("Versiune baza de date: " + connection.getMetaData().getDatabaseProductVersion());
		} catch (SQLException e) {
			System.out.println("Eroare la conexiunea la baza de date: " + e.getMessage());
		}
	}

	@Test
	public void testSelectAllBeneficiari() {
		// Rulează query-ul SELECT * FROM beneficiari
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM beneficiari");

		// Afișează rezultatele în consolă
		if (rows.isEmpty()) {
			System.out.println("Nu există beneficiari în baza de date.");
		} else {
			System.out.println("Beneficiari găsiți:");
			for (Map<String, Object> row : rows) {
				System.out.println(row);
			}
		}
	}
}
