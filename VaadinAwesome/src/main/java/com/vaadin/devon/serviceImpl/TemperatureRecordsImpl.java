package com.vaadin.devon.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.vaadin.devon.entity.TemperatureRecords;
import com.vaadin.devon.serviceinterface.TemperatureRecordsDAO;

@Transactional
@Component("TemperatureRecordsImpl")
public class TemperatureRecordsImpl implements TemperatureRecordsDAO {

	private static Logger log = LoggerFactory.getLogger(TemperatureRecordsImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public TemperatureRecordsImpl() {
		log.info("-----------------------TemperatureRecordsImpl crated");
	}

	public void setJdbcTemplate(DataSource jdbcTemplate) {
		log.info("------------------TemperatureRecordsImpl setJdbcTemplate created");
		this.jdbcTemplate = (JdbcTemplate) jdbcTemplate;
	}

	@Override
	public List<TemperatureRecords> findAll() {
		String selectAll = "Select * from temperaturerecords";

		List<TemperatureRecords> allTemp = jdbcTemplate.query(selectAll, new RowMapper<TemperatureRecords>() {

			public TemperatureRecords mapRow(ResultSet rs, int rowNum) throws SQLException {
				TemperatureRecords temp = new TemperatureRecords(rs.getInt("ID"), rs.getString("EMAIL"),
						rs.getDouble("TEMPERATURE"), rs.getString("DEVICE"), rs.getTimestamp("TIMESTAMP"));

				log.info(temp.toString());
				return temp;
			}
		});

		return allTemp;
	}

	@Override
	public void createTable() {
		String drop = "DROP TABLE db2admin.temperaturerecords";
		String create = "create table db2admin.temperaturerecords (id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),email varchar(255),"
				+ "temperature double ,device VARCHAR(255) ,timeStamp TIMESTAMP,PRIMARY KEY (id))";

		jdbcTemplate.execute(drop);
		jdbcTemplate.execute(create);

	}

	@Override
	public void insert(TemperatureRecords t) {

		String sql = "INSERT INTO temperaturerecords(EMAIL,TEMPERATURE,DEVICE,TIMESTAMP) VALUES(?,?,?,? )";
		jdbcTemplate.update(sql, t.getEmail(), t.getTemperature(), t.getDevice(), t.getTimeStamp());

	}

}
