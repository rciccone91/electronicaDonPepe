package utn.edu.avanzada2.ElectronicaDonPepe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import utn.edu.avanzada2.ElectronicaDonPepe.datalayer.DataLayerImple;
import utn.edu.avanzada2.ElectronicaDonPepe.servicelayer.ServiceLayer;

@Configuration
public class AppConfig {
	
	@Bean
	public <T> DataLayerImple<T> getDataLayerImple(){
		return new DataLayerImple<T>();
	}
	
	@Bean
	public ServiceLayer getServiceLayer(){
		return new ServiceLayer();
	}
	
}
