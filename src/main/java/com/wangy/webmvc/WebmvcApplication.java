package com.wangy.webmvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

/**
 * @author wangy325
 * @see java.sql.Connection
 */
@SpringBootApplication
@Slf4j
public class WebmvcApplication /*implements CommandLineRunner*/ {
    /*@Autowired
    private DataSource dataSource;*/

    public static void main(String[] args) {
        SpringApplication.run(WebmvcApplication.class, args);
    }




	/*@Override
	public void run(String... args) throws Exception {
		showDataSourceInfo();
	}

	private void showDataSourceInfo() throws SQLException {
		log.info(dataSource.toString());
		Connection connection = dataSource.getConnection();
		log.info(connection.toString());
		connection.close();
	}*/
}
